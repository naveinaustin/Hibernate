

package com.wakaleo.articles.caching.dao;

import java.util.List;


import com.wakaleo.articles.caching.businessobjects.Country;

public class EmployeeDAO {

public List getEmployeesByCountry(Country country) {
        return SessionManager.currentSession().createQuery("from Employee as e where e.country = :country " + " order by e.surname, e.firstname").setParameter("country", country).list();
    }
}
 

