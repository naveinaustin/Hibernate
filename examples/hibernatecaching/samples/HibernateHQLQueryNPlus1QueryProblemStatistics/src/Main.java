
import java.util.*;
import org.hibernate.*;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;

public class Main {

    public static void main(String[] args) {

        // Set up database tables
        HibernateUtil.droptable("drop table Supplier");
        HibernateUtil.droptable("drop table Product");
        HibernateUtil.setup("create table Supplier ( id int, name VARCHAR(20))");
        HibernateUtil.setup("create table Product ( id int, name VARCHAR(20), description VARCHAR(40), price double,supplierId int)");

        prepareTestData();

        // Display tables with prepopulate data
        HibernateUtil.checkData("select * from Supplier");
        HibernateUtil.checkData("select * from Product");

        // Starting persistence operation
        Session session = HibernateUtil.currentSession();

        // Perform HQL query with delayed N+1 query problem, lazy="true" by default
        {
            System.out.println("\n---Performing HQL query with delayed N+1 query problem since Supplier is not being accessed for now...");
            Query query = session.createQuery("from Product");

            List results = query.list();

            // Supplier is not being accessed
            displayProductsListWithoutSupplierName(results);

        }

        // Perform HQL query with N+1 query problem
        {
            System.out.println("\n---Performing HQL query - get all products - observe N+1 query problem...");

            // Get all products
            Query query = session.createQuery("from Product");
            List results = query.list();

            // Supplier is being accessed - you will see here 2 select query statements
            displayProductsList(results);

        }

        // Perform HQL query with N+1 query problem, but you will not see the N selects since Supplier
        // data is now cached.
        {
            System.out.println("\n---Performing HQL query - get all products - observe N+1 query problem...");

            // Get all products
            Query query = session.createQuery("from Product");
            List results = query.list();

            // Supplier is being accessed - you will see here 2 select query statements
            displayProductsList(results);
        }

        // Display Statistics
        displayStatistics(session.getSessionFactory());

    }

    // Below are utility methods
    public static void displayProductsList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No products to display.");
            return;
        }
        while (iter.hasNext()) {
            Product product = (Product) iter.next();
            String msg = product.getSupplier().getName() + "\t";
            msg += product.getName() + "\t";
            msg += product.getPrice() + "\t";
            msg += product.getDescription();
            System.out.println(msg);
        }
    }

    public static void displayProductsListWithoutSupplierName(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No products to display.");
            return;
        }
        while (iter.hasNext()) {
            Product product = (Product) iter.next();
            //String msg = product.getSupplier().getName() + "\t";
            String msg = "\t";
            msg += product.getName() + "\t";
            msg += product.getPrice() + "\t";
            msg += product.getDescription();
            System.out.println(msg);
        }
    }

    static public void displaySupplierList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No suppliers to display.");
            return;
        }
        while (iter.hasNext()) {
            Supplier supplier = (Supplier) iter.next();
            String msg = supplier.getName();
            System.out.println(msg);
        }
    }

    static public void displayObjectList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No objects to display.");
            return;
        }
        while (iter.hasNext()) {
            System.out.println("New object");
            Object obj = (Object) iter.next();
            System.out.println(obj);


        }
    }

    static public void displayStatistics(SessionFactory sf) {

        System.out.println("\n---Displaying Statistics.......");

        // Retrieve statistics for this particular Session Factory
        Statistics stats = sf.getStatistics();

        // Start time
        System.out.println("Current time: " + new Date());
        System.out.println("Start time: " + new Date(stats.getStartTime()));

        // Collections
        System.out.println("Global number of collections fetched: " + stats.getCollectionFetchCount());

        // Cache hits
        double queryCacheHitCount = stats.getQueryCacheHitCount();
        double queryCacheMissCount = stats.getQueryCacheMissCount();
        double queryCacheHitRatio =
                queryCacheHitCount / (queryCacheHitCount + queryCacheMissCount);

        System.out.println("Query Cache Hit Count:" + queryCacheHitCount);
        System.out.println("Query Cache Miss Count:" + queryCacheMissCount);
        System.out.println("Query Hit ratio:" + queryCacheHitRatio);
        System.out.println("Transaction count " + stats.getTransactionCount());
        
        String entityName1 = stats.getEntityNames()[0];
        System.out.println("Entity name 1 = " + entityName1);
        String entityName2 = stats.getEntityNames()[1];
        System.out.println("Entity name 2 = " + entityName2);

        // Entity Statistics
        EntityStatistics entityStats =
                stats.getEntityStatistics(entityName2);
        long changes =
                entityStats.getInsertCount() + entityStats.getUpdateCount() + entityStats.getDeleteCount();
        System.out.println(Product.class.getName() + " changed " + changes + " times");

        long retrievals =
                entityStats.getLoadCount() + entityStats.getFetchCount();
        System.out.println(Product.class.getName() + " retrieved " + retrievals + " times");

        
    }

    private static void prepareTestData() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier1 = new Supplier();
        supplier1.setName("Supplier Name 1");
        session.save(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Supplier Name 2");
        session.save(supplier2);

        Supplier supplier3 = new Supplier();
        supplier3.setName("Supplier Name 3");
        session.save(supplier3);

        Product product1 = new Product("Product 1", "Name for Product 1", 2.0);
        product1.setSupplier(supplier1);
        supplier1.getProducts().add(product1);
        session.save(product1);

        Product product12 = new Product("Product 2", "Name for Product 2", 22.0);
        product12.setSupplier(supplier1);
        supplier1.getProducts().add(product12);
        session.save(product12);

        Product product2 = new Product("Product 3", "Name for Product 3", 30.0);
        product2.setSupplier(supplier2);
        supplier2.getProducts().add(product2);
        session.save(product2);

        tx.commit();
        HibernateUtil.closeSession();
    }
}
