package com.apys.learning.ext.register_offece.dao;

import com.apys.learning.ext.register_offece.domain.MarriageCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
// Первый аргумент дженерика это тот класс скоторым мы будем работать, а второй это тип Id этого объекта
// Фактически Spring Data JPA сгенерирует готовые классы. Все необходимые методы есть у родительских классов
public interface MarriageDao extends JpaRepository<MarriageCertificate, Long> {
}
