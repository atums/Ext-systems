package com.apys.learning.ext.register_offece.dao;

import com.apys.learning.ext.register_offece.domain.Person;

import javax.persistence.*;
import java.util.List;

public class PersonDao {
    // Все настройки для подключения к DB и формирование фабрики для подключения производится
    // в файле springContext.xml (при встрече с такой нотацией Spring будет пытаться найти настройки
    //в springContext.xml
    @PersistenceContext
    private EntityManager entityManager;
    public List<Person> findPersons() {
        Query query = entityManager.createNamedQuery("Person.findPerson");
        query.setParameter("personId", 1L);
        return query.getResultList();
    }
}
