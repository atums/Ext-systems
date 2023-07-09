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
    // Запрос на добавление в DB
    public Long addPerson(Person person) {
        //Это транзакция без применения TransactionManager
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        try {
//            entityManager.persist(person);
//            entityManager.flush();
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//            throw new RuntimeException(e);
//        }

        //А это с использованием TransactionManager
        entityManager.persist(person);
        entityManager.flush();
        return person.getPersonId();
    }
}
