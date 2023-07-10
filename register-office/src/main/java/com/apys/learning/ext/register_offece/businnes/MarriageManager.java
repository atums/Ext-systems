package com.apys.learning.ext.register_offece.businnes;

import com.apys.learning.ext.register_offece.dao.MarriageDao;
import com.apys.learning.ext.register_offece.dao.PersonDao;
import com.apys.learning.ext.register_offece.domain.MarriageCertificate;
import com.apys.learning.ext.register_offece.domain.Person;
import com.apys.learning.ext.register_offece.domain.PersonFemale;
import com.apys.learning.ext.register_offece.domain.PersonMale;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import com.apys.learning.ext.register_offece.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

// Помечает как Бин, но говорит, что в этом Бине только функционал
//(при использовании похож на @Component)
@Service("marriageService")
// Позволяет установить использование бина, либо в единственном экземпляре либо создавать Бин
// При каждом обращении
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MarriageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);
    // Автоматическое внедрение зависимостей под управлением Spring
    //@Autowired - можно применять к конструкторам и сеттерам
    @Autowired
    private MarriageDao marriageDao;
    // Используя эту аннотацию на поле - можно избавиться от сеттеров
    @Autowired
    private PersonDao personDao;
    //Указываем что метод транзакционный
    // propagation - управление транзакциями (управляет транзакциями в вызываемых методах из этого метода)
    // readOnly - выполняет ограничение на чтение/запись
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public MarriageResponse findMarriageCertificate (MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
//        MarriageCertificate marriageCertificate = marriageDao.findMarriageCertificate(request);

        personDao.findPersons();

        personDao.addPerson(getPerson(1));
        personDao.addPerson(getPerson(2));

        MarriageCertificate mc = getMarriageCertificate();

        // вызываем saveAndFlush и передаем туда наш сертификат. Сам метод saveAndFlush предоставляется
        // Spring Data (там очень много всяких медотов для работы с DB)
        marriageDao.saveAndFlush(mc);

        // Тут вызываем автоматически генерируемый Spring Data SELECT (описан в MarriageDao)
        List<MarriageCertificate> byNumber = marriageDao.findByNumber("123456");

        byNumber.forEach(bN -> {
            LOGGER.info("Marriage Certificate from Number :{}", bN.getMarriageCertificateId());
        });
        LOGGER.info("----->>>>>");
        // Тут вызываем автоматически генерируемый Spring Data SELECT (описан в MarriageDao) который ищет
        // подстроку в Number
        List<MarriageCertificate> byNumber2 = marriageDao.findByNumberContaining("76");

        byNumber2.forEach(bN -> {
            LOGGER.info("Marriage Certificate NumberContaining :{}", bN.getMarriageCertificateId());
        });
        LOGGER.info("----->>>>>");
        // Тут вызываем заранее написанный NamedQuery  SELECT (описан в MarriageCertificate) который ищет
        // по Number
        List<MarriageCertificate> byNumber3 = marriageDao.findByNum("98765");

        byNumber3.forEach(bN -> {
            LOGGER.info("Marriage Certificate NamedQuery :{}", bN.getMarriageCertificateId());
        });

        LOGGER.info("----->>>>>");
        // Тут вызываем заранее написанный  SELECT прямо из Аннотации (описан в MarriageDao) который ищет
        // по Number
        List<MarriageCertificate> byNumber4 = marriageDao.findBySomething("01928");

        byNumber4.forEach(bN -> {
            LOGGER.info("Marriage Certificate Annotation :{}", bN.getMarriageCertificateId());
        });

        // Этотb методs тоже предоставляется Spring Data т.е. Spring Data на основе наших классов
        // MarriageCertificate (и входящих в него Person и потомков) сам создает DAO для работы
        // с этими классами в DB
//        marriageDao.findAll();
//        marriageDao.findById(1L);

        return new MarriageResponse();
    }

    private MarriageCertificate getMarriageCertificate() {
        MarriageCertificate mc = new MarriageCertificate();

        mc.setIssueDate(LocalDate.now());
        mc.setNumber("01928");
        mc.setActive(true);

        List<Person> persons = personDao.findPersons();

        for(Person person : persons) {
            if(person instanceof PersonMale) {
                mc.setHusband((PersonMale) person);
            } else {
                mc.setWife((PersonFemale) person);
            }
        }
        return mc;
    }
    private Person getPerson(int sex) {

        Person m = sex == 1 ? new PersonMale() : new PersonFemale();

        m.setFirstName("1_" + sex);
        m.setLastName("2_" + sex);
        m.setPatronymic("3_" + sex);
        m.setDateOfBirth(LocalDate.of(1993, 3, 3));
        return m;
    }
}
