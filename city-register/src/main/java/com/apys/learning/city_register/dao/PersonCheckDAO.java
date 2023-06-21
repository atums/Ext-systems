package com.apys.learning.city_register.dao;

import com.apys.learning.city_register.domain.PersonRequest;
import com.apys.learning.city_register.domain.PersonResponse;
import com.apys.learning.city_register.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDAO {
    private static final String SQL_REQUEST;

    public PersonResponse checkPerson(PersonRequest personRequest) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_REQUEST)){

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                response.setRegistered(true);
                response.setTemporal(resultSet.getBoolean("temporal"));
            }

        } catch (SQLException e) {
            throw new PersonCheckException(e);
        }
        return response;
    }

    private Connection getConnection() {

    }
}
