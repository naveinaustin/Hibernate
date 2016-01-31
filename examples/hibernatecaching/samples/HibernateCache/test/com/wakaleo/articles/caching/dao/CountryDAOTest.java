////////////////////////////////////////////////////////////////////////////////
// CountryDAOTest: $Source$
// TODO Class summary goes here
//
// Created : 26 oct. 2005 by jfsmart
// Last modified $Date$ by $Author$
// Revision: $Revision$
// Version : $ID$
// Copyright (c) 2005
////////////////////////////////////////////////////////////////////////////////

package com.wakaleo.articles.caching.dao;

import java.util.List;

import org.hibernate.Transaction;

import com.wakaleo.articles.TestTimer;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;

public class CountryDAOTest extends TestCase {
    
        /*
         * Test method for 'com.wakaleo.articles.caching.dao.CountryDAO.getCountries()'
         */
    public void testGetCountries() {
        CountryDAO dao = new CountryDAO();
        for(int i = 1; i <= 5; i++) {
            Session s = SessionManager.getSession();
            Transaction tx =s.beginTransaction();
            TestTimer timer = new TestTimer("testGetCountries");
            List countries = dao.getCountries();
            tx.commit();
            timer.done();
            SessionManager.closeSession();
            assertNotNull(countries);
        }
        
        displayStatistics();
    }
    
    static public void displayStatistics() {
        System.out.println("\n---Displaying Statistics.......");
        Statistics stats = HibernateUtil.sessionFactory.getStatistics();
        
        double queryCacheHitCount  = stats.getQueryCacheHitCount();
        double queryCacheMissCount = stats.getQueryCacheMissCount();
        double queryCacheHitRatio =
                queryCacheHitCount / (queryCacheHitCount + queryCacheMissCount);
        
        System.out.println("Query Cache Hit Count:" + queryCacheHitCount);
        System.out.println("Query Cache Miss Count:" + queryCacheMissCount);
        System.out.println("Query Hit ratio:" + queryCacheHitRatio);
        
    }
}
