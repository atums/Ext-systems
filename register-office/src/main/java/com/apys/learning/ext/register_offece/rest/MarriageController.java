package com.apys.learning.ext.register_offece.rest;

import com.apys.learning.ext.register_offece.businnes.MarriageManager;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import com.apys.learning.ext.register_offece.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Помечает как Бин, но говорит, что в этом Бине только функционал (при использовании похож на @Component).
// Value задает имя Бина - удобно если у нас есть классы которые реализуют один интерфейс (можем четко пометить
// какой класс какой
@Service(value = "controller")
public class MarriageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);

    // Автоматическое внедрение зависимостей под управлением Spring
    // Используя эту аннотацию на поле - можно избавиться от сеттеров
    @Autowired
    private MarriageManager marriageManager;

//    public void setMarriageManager(MarriageManager marriageManager) {
//        this.marriageManager = marriageManager;
//    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        MarriageResponse marriageCertificate = marriageManager.findMarriageCertificate(request);

        return marriageCertificate;
    }
}
