package com.apys.learning.city_register.dao;

import com.apys.learning.city_register.domain.PersonRequest;
import com.apys.learning.city_register.domain.PersonResponse;
import com.apys.learning.city_register.exception.PersonCheckException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonCheckDAOTest {

    @Test
    public void checkPerson() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Tums");
        pr.setGivenName("Aleksandr");
        pr.setPatronymic(" ");
        pr.setDateOfBirth(LocalDate.of(1978, 01, 27));
        pr.setStreetCode(2);
        pr.setBuilding("1313");
        pr.setExtension("");
        pr.setApartment("13");

        PersonCheckDAO dao = new PersonCheckDAO();
        dao.setConnectionBuilder(new DirectConnectionBuilder());
        PersonResponse ps = dao.checkPerson(pr);
        Assert.assertTrue(ps.isRegistered());
        Assert.assertFalse(ps.isTemporal());

    }
    @Test
    public void checkPerson2() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Tums");
        pr.setGivenName("Marina");
        pr.setPatronymic(" ");
        pr.setDateOfBirth(LocalDate.of(1976, 04, 24));
        pr.setStreetCode(2);
        pr.setBuilding("1313");
        pr.setExtension("");
        pr.setApartment("13");

        PersonCheckDAO dao = new PersonCheckDAO();
        dao.setConnectionBuilder(new DirectConnectionBuilder());
        PersonResponse ps = dao.checkPerson(pr);
        Assert.assertTrue(ps.isRegistered());
        Assert.assertFalse(ps.isTemporal());

    }
}