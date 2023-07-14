package com.apys.learning.ext.student.dao;

import com.apys.learning.ext.student.domain.Faculty;
import com.apys.learning.ext.student.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty, Long> {
}
