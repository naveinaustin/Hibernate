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
import com.wakaleo.articles.caching.businessobjects.Country;

import junit.framework.TestCase;

public class CountryDAOTest extends TestCase {

	public void testCreate()
	{
		String countries [][] = new String[][] { { "ad","Andorra, Principality of"},
			 {  "ae","United Arab Emirates"},
			 {  "af","Afghanistan, Islamic State of"},
			 {  "ai","Anguilla"},
			 {  "al","Albania"},
			 {  "am","Armenia"},
			 {  "ao","Angola"},
			 {  "aq","Antarctica"},
			 {  "ar","Argentina"},
			 {  "at","Austria"},
			 {  "au","Australia"},
			 {  "aw","Aruba"},
			 {  "az","Azerbaidjan"},
			 {  "ba","Bosnia-Herzegovina"},
			 {  "bb","Barbados"},
			 {  "bd","Bangladesh"},
			 {  "be","Belgium"},
			 {  "bf","Burkina Faso"},
			 {  "bg","Bulgaria"},
			 {  "bh","Bahrain"},
			 {  "bi","Burundi"},
			 {  "bj","Benin"},
			 {  "bm","Bermuda"},
			 {  "bn","Brunei Darussalam"},
			 {  "bo","Bolivia"},
			 {  "br","Brazil"},
			 {  "bs","Bahamas"},
			 {  "bt","Bhutan"},
			 {  "bv","Bouvet Island"},
			 {  "bw","Botswana"},
			 {  "by","Belarus"},
			 {  "bz","Belize"},
			 {  "ca","Canada"},
			 {  "cf","Central African Republic"},
			 {  "cg","Congo"},
			 {  "ch","Switzerland"},
			 {  "ci","Ivory Coast"},
			 {  "ck","Cook Islands"},
			 {  "cl","Chile"},
			 {  "cm","Cameroon"},
			 {  "cn","China"},
			 {  "co","Colombia"},
			 {  "cr","Costa Rica"},
			 {  "cu","Cuba"},
			 {  "cv","Cape Verde"},
			 {  "cx","Christmas Island"},
			 {  "cy","Cyprus"},
			 {  "cz","Czech Republic"},
			 {  "de","Germany"},
			 {  "dj","Djibouti"},
			 {  "dk","Denmark"},
			 {  "dm","Dominica"},
			 {  "do","Dominican Republic"},
			 {  "dz","Algeria"},
			 {  "ec","Ecuador"},
			 {  "ee","Estonia"},
			 {  "eg","Egypt"},
			 {  "eh","Western Sahara"},
			 {  "er","Eritrea"},
			 {  "es","Spain"},
			 {  "et","Ethiopia"},
			 {  "fi","Finland"},
			 {  "fj","Fiji"},
			 {  "fk","Falkland Islands"},
			 {  "fm","Micronesia"},
			 {  "fo","Faroe Islands"},
			 {  "fr","France"},
			 {  "ga","Gabon"},
			 {  "gb","Great Britain"},
			 {  "gd","Grenada"},
			 {  "ge","Georgia"},
			 {  "gf","French Guyana"},
			 {  "gh","Ghana"},
			 {  "gi","Gibraltar"},
			 {  "gl","Greenland"},
			 {  "gm","Gambia"},
			 {  "gn","Guinea"},
			 {  "gp","Guadeloupe {French}"},
			 {  "gq","Equatorial Guinea"},
			 {  "gr","Greece"},
			 {  "gs","S. Georgia & S. Sandwich Isls."},
			 {  "gt","Guatemala"},
			 {  "gu","Guam {USA}"},
			 {  "gw","Guinea Bissau"},
			 {  "gy","Guyana"},
			 {  "hk","Hong Kong"},
			 {  "hm","Heard and McDonald Islands"},
			 {  "hn","Honduras"},
			 {  "hr","Croatia"},
			 {  "ht","Haiti"},
			 {  "hu","Hungary"},
			 {  "id","Indonesia"},
			 {  "ie","Ireland"},
			 {  "il","Israel"},
			 {  "in","India"},
			 {  "iq","Iraq"},
			 {  "ir","Iran"},
			 {  "is","Iceland"},
			 {  "it","Italy"},
			 {  "jm","Jamaica"},
			 {  "jo","Jordan"},
			 {  "jp","Japan"},
			 {  "ke","Kenya"},
			 {  "kg","Kyrgyz Republic {Kyrgyzstan}"},
			 {  "kh","Cambodia, Kingdom of"},
			 {  "ki","Kiribati"},
			 {  "km","Comoros"},
			 {  "kn","Saint Kitts & Nevis Anguilla"},
			 {  "kp","North Korea"},
			 {  "kr","South Korea"},
			 {  "kw","Kuwait"},
			 {  "ky","Cayman Islands"},
			 {  "kz","Kazakhstan"},
			 {  "la","Laos"},
			 {  "lb","Lebanon"},
			 {  "lc","Saint Lucia"},
			 {  "li","Liechtenstein"},
			 {  "lk","Sri Lanka"},
			 {  "lr","Liberia"},
			 {  "ls","Lesotho"},
			 {  "lt","Lithuania"},
			 {  "lu","Luxembourg"},
			 {  "lv","Latvia"},
			 {  "ly","Libya"},
			 {  "ma","Morocco"},
			 {  "mc","Monaco"},
			 {  "md","Moldavia"},
			 {  "mg","Madagascar"},
			 {  "mh","Marshall Islands"},
			 {  "mk","Macedonia"},
			 {  "ml","Mali"},
			 {  "mm","Myanmar"},
			 {  "mn","Mongolia"},
			 {  "mo","Macau"},
			 {  "mp","Northern Mariana Islands"},
			 {  "mr","Mauritania"},
			 {  "ms","Montserrat"},
			 {  "mt","Malta"},
			 {  "mu","Mauritius"},
			 {  "mv","Maldives"},
			 {  "mw","Malawi"},
			 {  "mx","Mexico"},
			 {  "my","Malaysia"},
			 {  "mz","Mozambique"},
			 {  "na","Namibia"},
			 {  "ne","Niger"},
			 {  "nf","Norfolk Island"},
			 {  "ng","Nigeria"},
			 {  "ni","Nicaragua"},
			 {  "nl","Netherlands"},
			 {  "no","Norway"},
			 {  "np","Nepal"},
			 {  "nr","Nauru"},
			 {  "nt","Neutral Zone"},
			 {  "nu","Niue"},
			 {  "nz","New Zealand"},
			 {  "om","Oman"},
			 {  "pa","Panama"},
			 {  "pe","Peru"},
			 {  "pg","Papua New Guinea"},
			 {  "ph","Philippines"},
			 {  "pk","Pakistan"},
			 {  "pl","Poland"},
			 {  "pm","Saint Pierre and Miquelon"},
			 {  "pn","Pitcairn Island"},
			 {  "pr","Puerto Rico"},
			 {  "pt","Portugal"},
			 {  "pw","Palau"},
			 {  "py","Paraguay"},
			 {  "qa","Qatar"},
			 {  "ro","Romania"},
			 {  "ru","Russian Federation"},
			 {  "rw","Rwanda"},
			 {  "sa","Saudi Arabia"},
			 {  "sb","Solomon Islands"},
			 {  "sc","Seychelles"},
			 {  "sd","Sudan"},
			 {  "se","Sweden"},
			 {  "sg","Singapore"},
			 {  "sh","Saint Helena"},
			 {  "si","Slovenia"},
			 {  "sj","Svalbard and Jan Mayen Islands"},
			 {  "sk","Slovak Republic"},
			 {  "sl","Sierra Leone"},
			 {  "sm","San Marino"},
			 {  "sn","Senegal"},
			 {  "so","Somalia"},
			 {  "sr","Suriname"},
			 {  "st","Saint Tome {Sao Tome} and Principe"},
			 {  "sv","El Salvador"},
			 {  "sy","Syria"},
			 {  "sz","Swaziland"},
			 {  "tc","Turks and Caicos Islands"},
			 {  "td","Chad"},
			 {  "tf","French Southern Territories"},
			 {  "tg","Togo"},
			 {  "th","Thailand"},
			 {  "tj","Tadjikistan"},
			 {  "tk","Tokelau"},
			 {  "tm","Turkmenistan"},
			 {  "tn","Tunisia"},
			 {  "to","Tonga"},
			 {  "tp","East Timor"},
			 {  "tr","Turkey"},
			 {  "tt","Trinidad and Tobago"},
			 {  "tv","Tuvalu"},
			 {  "tw","Taiwan"},
			 {  "tz","Tanzania"},
			 {  "ua","Ukraine"},
			 {  "ug","Uganda"},
			 {  "uk","United Kingdom"},
			 {  "um","USA Minor Outlying Islands"},
			 {  "us","United States"},
			 {  "uy","Uruguay"},
			 {  "uz","Uzbekistan"},
			 {  "va","Holy See {Vatican City State}"},
			 {  "vc","Saint Vincent & Grenadines"},
			 {  "ve","Venezuela"},
			 {  "vn","Vietnam"},
			 {  "vu","Vanuatu"},
			 {  "wf","Wallis and Futuna Islands"},
			 {  "ws","Samoa"},
			 {  "ye","Yemen"},
			 {  "yt","Mayotte"},
			 {  "yu","Yugoslavia"},
			 {  "za","South Africa"},
			 {  "zm","Zambia"},
			 {  "zr","Zaire"},
			 {  "zw","Zimbabwe"} };
		
		/*int numOfCountries = countries.length;
		CountryDAO dao = new CountryDAO();
		
		for(int i = 1; i < numOfCountries; i++)
		{
			Country country = new Country();
			country.setCode(countries[i][0]);
			country.setName(countries[i][1]);
			dao.create(country);
		}*/
	}
	
    /*
     * Test method for 'com.wakaleo.articles.caching.dao.CountryDAO.getCountries()'
     */
    public void testGetCountries() {
        CountryDAO dao = new CountryDAO();
        for (int i = 1; i <= 10; i++) {
            Transaction tx = SessionManager.getSession().beginTransaction();
            TestTimer timer = new TestTimer("testGetCountries");
            List countries = dao.getCountries();
            //System.out.println("Number of countries: " + countries.size());
            tx.commit();
            timer.done();
            SessionManager.closeSession();
            assertNotNull(countries);
        }
    }
}
