package com.apys.learning.ext.student.service;

import com.apys.learning.ext.student.dao.FacultyRepository;
import com.apys.learning.ext.student.dao.UniversityRepository;
import com.apys.learning.ext.student.domain.Faculty;
import com.apys.learning.ext.student.domain.University;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService
{
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Transactional(readOnly = true)
    public List<University> findUniversities() {
        return universityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<University> findFullUniversities() {
        return universityRepository.findFullList();
    }

    @Transactional(readOnly = true)
    public List<Faculty> findFaculties() {
        return facultyRepository.findAll();
    }

    @Transactional
    public University getUniversity(Long universityId) {
        University u = universityRepository.findById(universityId).get();
        //Насильная инициализация связанного Прокси объекта фактически мы получаем 2 Селекта
        // Фактически аналог FetchType.EAGER
        Hibernate.initialize(u.getFaculties());
        return u;
    }
    @Transactional(readOnly = true)
    public Faculty getFaculty(Long facultyId) {
        Optional<Faculty> fop = facultyRepository.findById(facultyId);
        Faculty fc = fop.get();
        //Насильная инициализация связанного Прокси объекта фактически мы получаем 2 Селекта
        // Фактически аналог FetchType.EAGER
        Hibernate.initialize(fc.getUniversity());
        return fc;
    }
}
