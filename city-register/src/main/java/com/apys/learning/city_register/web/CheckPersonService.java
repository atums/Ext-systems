package com.apys.learning.city_register.web;

import com.apys.learning.city_register.dao.PersonCheckDAO;
import com.apys.learning.city_register.dao.PoolConnectionBuilder;
import com.apys.learning.city_register.domain.PersonRequest;
import com.apys.learning.city_register.domain.PersonResponse;
import com.apys.learning.city_register.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/check") // Указываем URL на который будет реагировать апплет
@Singleton
public class CheckPersonService {
    public static final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

    private PersonCheckDAO dao;

//    @GET // Метод реагирующий на GET запрос
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public PersonResponse checkPerson(@PathParam("id") int simpleId,
//                                      @QueryParam("name") String simpleName) {
//        return new PersonResponse();
//    }
    @PostConstruct
    public void init() {
        logger.info("SERVLET is created, Service STARTUP!!!");
        dao = new PersonCheckDAO();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

//    @PreDestroy
//    public void destroy() {
//        logger.info("Service DESTROYED!!!");
//    }

    @POST // Метод реагирующий на POST запрос
    @Consumes(MediaType.APPLICATION_JSON) // В каком формате данные принимаем
    @Produces(MediaType.APPLICATION_JSON) // В каком формате данные отдаем
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        logger.info(request.toString());
        return dao.checkPerson(request);
    }
}
