package com.apys.learning.ext.register_offece.manager;

import com.apys.learning.ext.register_offece.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Serializable;
import java.util.List;


public class PersonManager {
    public static void main(String[] args) {

        SessionFactory sf = buildSessionFactory();

        Session session = sf.openSession(); //Открытие сессии

        session.getTransaction().begin(); // Позволяет внести изменения открыть транзакцию

        Person person = new Person();
        person.setFirstName("Alex");
        person.setLastName("Tums");

        Long id = (Long) session.save(person); // Хотим сохранить в DB - возвращиет id primary key

       session.getTransaction().commit(); // Внести изменения в DB
//        session.getTransaction().rollback(); // Откатить из менения

        session.close();
        System.out.println("Id = " + id);

        session = sf.openSession();

        Person person1 = session.get(Person.class, id);// Получить запись из DB по id
        System.out.println("Person #" + id + " " + person1.toString());

        session.close();

        session = sf.openSession();

        List<Person> personList = session.createQuery("FROM Person", Person.class).list(); // Запрос на языке HQL (вызвать все записи)

        for(Person p : personList) {
            System.out.println(p.toString());
        }

        session.close();
    }
    private static SessionFactory buildSessionFactory() {

        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
