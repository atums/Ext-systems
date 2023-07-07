package com.apys.learning.ext.register_offece.dao;

import com.apys.learning.ext.register_offece.businnes.MarriageManager;
import com.apys.learning.ext.register_offece.domain.MarriageCertificate;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;

public class MarriageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageDao.class);

    private EntityManager em;
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    public MarriageDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        em = factory.createEntityManager();
    }

    public MarriageCertificate findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called :{}", test);

//        Query query = em.createNamedQuery("Person.findPerson");
//        query.setParameter("personId", 1L);
//        return query.getResultList();
    return null;
    }
}
