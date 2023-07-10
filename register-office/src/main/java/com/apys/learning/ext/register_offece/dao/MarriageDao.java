package com.apys.learning.ext.register_offece.dao;

import com.apys.learning.ext.register_offece.domain.MarriageCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
// Первый аргумент дженерика это тот класс скоторым мы будем работать, а второй это тип Id этого объекта
// Фактически Spring Data JPA сгенерирует готовые классы. Все необходимые методы есть у родительских классов
public interface MarriageDao extends JpaRepository<MarriageCertificate, Long> {

    //findBy... - Spring Data сгенерирует (по названию метода) SELECT по интересующему параметру
    //В этом случае по полю MarriageCertificate.number
    List<MarriageCertificate> findByNumber(String number);

    // Тут в SELECT будет указано, что номер должен содержать подстроку
    List<MarriageCertificate> findByNumberContaining(String number);

    // Этот findBy... уже обращается к NamedQuery (описан MarriageCertificate)
    List<MarriageCertificate> findByNum(@Param("number") String number);

    //Этот findBy... SELECT будет создан посто по аннотации @Query
    @Query("SELECT mc FROM MarriageCertificate mc WHERE mc.number = :number")
    List<MarriageCertificate> findBySomething(@Param("number") String number);
}
