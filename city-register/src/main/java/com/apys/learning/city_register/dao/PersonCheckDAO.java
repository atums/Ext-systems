package com.apys.learning.city_register.dao;

import com.apys.learning.city_register.domain.PersonRequest;
import com.apys.learning.city_register.domain.PersonResponse;
import com.apys.learning.city_register.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDAO {
    private static final String SQL_REQUEST =
            "SELECT temporal " +
                    "FROM cr_address_person ap " +
                    "JOIN cr_person p ON ap.person_id = p.person_id " +
                    "JOIN cr_address a ON ap.address_id = a.address_id " +
                    "WHERE " +
                    "CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <= ap.end_date OR ap.end_date IS NULL) " +
                    "AND UPPER(p.sur_name) = UPPER(?) " +
                    "AND UPPER(p.given_name) = UPPER(?) " +
                    "AND UPPER(p.patronymic) = UPPER(?) " +
                    "AND p.date_of_birth = ? " +
                    "AND a.street_code = ? " +
                    "AND UPPER(a.building) = UPPER(?) ";

        private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }
//    public PersonCheckDAO() {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
//        return DriverManager.getConnection(
//                "jdbc:postgresql://localhost/city_register",
//                "postgres", "zero01z");
    }

    public PersonResponse checkPerson(PersonRequest personRequest) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;

        if(personRequest.getExtension() != null) {
            sql += "AND UPPER(a.extension) = UPPER(?) ";
        } else {
            sql += "AND a.extension IS NULL ";
        }

        if(personRequest.getApartment() != null) {
            sql += "AND UPPER(a.apartment) = UPPER(?);";
        } else {
            sql += "AND a.apartment IS NULL";
        }

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            int count = 1;

            statement.setString(count++, personRequest.getSurName());
            statement.setString(count++, personRequest.getGivenName());
            statement.setString(count++, personRequest.getPatronymic());
            statement.setDate(count++, Date.valueOf(personRequest.getDateOfBirth()));
            statement.setInt(count++, personRequest.getStreetCode());
            statement.setString(count++, personRequest.getBuilding());
            if(personRequest.getExtension() != null) {
                statement.setString(count++, personRequest.getExtension());
            }
            if(personRequest.getApartment() != null) {
                statement.setString(count++, personRequest.getApartment());
            }

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
}
