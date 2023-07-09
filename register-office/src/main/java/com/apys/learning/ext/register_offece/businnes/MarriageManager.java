package com.apys.learning.ext.register_offece.businnes;

import com.apys.learning.ext.register_offece.dao.MarriageDao;
import com.apys.learning.ext.register_offece.dao.PersonDao;
import com.apys.learning.ext.register_offece.domain.MarriageCertificate;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import com.apys.learning.ext.register_offece.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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

    public MarriageResponse findMarriageCertificate (MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        MarriageCertificate marriageCertificate = marriageDao.findMarriageCertificate(request);

        personDao.findPersons();

        return new MarriageResponse();
    }
}
