package com.apys.learning.ext.register_offece.dao;

import com.apys.learning.ext.register_offece.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PersonDao
{
    private EntityManager entityManager;

    public PersonDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        entityManager = factory.createEntityManager();
    }

//    public List<Person> findPersons() {
//        return entityManager.createNamedQuery("Person.findPerson")
//                .getResultList();
//    }
    public List<Person> findPersons() {
        Query query = entityManager.createNamedQuery("Person.findPerson");
        query.setParameter("personId", 1L);
        return query.getResultList();
    }
}
