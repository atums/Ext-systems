package com.apys.learning.ext.register_offece.manager;

import com.apys.learning.ext.register_offece.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class PersonManager {
    public static void main(String[] args) {

        sessionExample();

        jpaExample();
    }

    private static void jpaExample() {
        // Аналог SessionFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

        // Аналог Session
        EntityManager em = emf.createEntityManager();

        // Начать транзакцию
        em.getTransaction().begin();

        // Создаем объекты для помещения в DB
        Person person = new Person();
        person.setFirstName("Marina");
        person.setLastName("Tums");

        // Аналог save и saveOfUpdate
        em.persist(person);

        // Аналог возвращаемого значения
        System.out.println("JPA Id = " + person.getPersonId());

        // Аналог Коммита
        em.getTransaction().commit();

        // Закрываем сесиию
        em.close();

        // Снова открываем на тойже фабрике
        em = emf.createEntityManager();

        // Аналог запроса только пишем на JPAQL
        List<Person> from_person = em.createQuery("FROM Person", Person.class).getResultList();
        from_person.forEach(p -> System.out.println(p));

        // Закрываем сесиию
        em.close();
    }

    private static void sessionExample() {
        // Создаем Фактори
        SessionFactory sf = buildSessionFactory();

        //Открытие сессии
        Session session = sf.openSession();

        // Позволяет внести изменения открыть транзакцию
        session.getTransaction().begin();

        // Создаем объекты для помещения в DB
        Person person = new Person();
        person.setFirstName("Alex");
        person.setLastName("Tums");

        // Хотим сохранить в DB - возвращиет id primary key
        Long id = (Long) session.save(person);

        // Внести изменения в DB
        session.getTransaction().commit();
//        session.getTransaction().rollback(); // Откатить из менения
        System.out.println("Id = " + id);

        // Закрываем сессию
        session.close();

        // Снова открываем на тойже фабрике
        session = sf.openSession();

        // Получить запись из DB по id
        Person person1 = session.get(Person.class, id);
        System.out.println("Person #" + id + " " + person1.toString());

        // Закрываем сессию
        session.close();

        // Снова открываем на тойже фабрике
        session = sf.openSession();

        // Запрос на языке HQL (вызвать все записи)
        List<Person> personList = session.createQuery("FROM Person", Person.class).list();

        for(Person p : personList) {
            System.out.println(p.toString());
        }

        // Закрываем сессию
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
