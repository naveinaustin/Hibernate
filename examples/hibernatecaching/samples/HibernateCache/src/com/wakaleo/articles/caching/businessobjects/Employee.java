package com.wakaleo.articles.caching.businessobjects;

import java.util.Set;

public class Employee {
private long id;
private String surname;
private String firstname;
private Country country;
private Set languages;

public Country getCountry() {
return country;
}
public void setCountry(Country country) {
this.country = country;
}
public String getFirstname() {
return firstname;
}
public void setFirstname(String firstname) {
this.firstname = firstname;
}
public long getId() {
return id;
}
public void setId(long id) {
this.id = id;
}
public String getSurname() {
return surname;
}
public void setSurname(String surname) {
this.surname = surname;
}

public Set getLanguages() {
return languages;
}
public void setLanguages(Set languages) {
this.languages = languages;
}

}
  