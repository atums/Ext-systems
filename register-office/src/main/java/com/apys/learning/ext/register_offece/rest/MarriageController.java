package com.apys.learning.ext.register_offece.rest;

import com.apys.learning.ext.register_offece.businnes.MarriageManager;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import com.apys.learning.ext.register_offece.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Помечает как Бин, но говорит, что в этом Бине только функционал (при использовании похож на @Component).
// Value задает имя Бина - удобно если у нас есть классы которые реализуют один интерфейс (можем четко пометить
// какой класс какой
@Service(value = "controller")
@Path("/mc")
public class MarriageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);

    // Автоматическое внедрение зависимостей под управлением Spring
    // Используя эту аннотацию на поле - можно избавиться от сеттеров
    @Autowired
    // Позволяет задать Бину кокретное имя - например для уточнения Бина если он например от чего-то
    // наследуется или имплементируется. Решение проблемы не одназначности
    @Qualifier("marriageService")
    private MarriageManager marriageManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MarriageResponse findMarriageCertificate() {
        LOGGER.info("findMarriageCertificate called");
        MarriageResponse marriageCertificate = marriageManager.findMarriageCertificate(null);

        return marriageCertificate;
    }
}
