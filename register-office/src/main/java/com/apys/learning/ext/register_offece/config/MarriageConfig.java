package com.apys.learning.ext.register_offece.config;

import com.apys.learning.ext.register_offece.dao.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


// Можно создать Бин из стороннего класса не прописывая в нем аннотации, а воспользоваться кодом из
// класса помеченного аннотацией @Configuration
// Все аннотации кода остаются как и были, но вместо  XML появляется Java класс
@Configuration
//Указывает путь к внешнему properties файлу (значениями которого можно пользоваться через @Value("${...}")
@PropertySource(value = "classpath:register.properties")
public class MarriageConfig {
    // Cоздаем бин по типу как в .xml согфигурации (иногда это надо)
    @Bean
    public PersonDao buildPersonDao() {
        System.out.println("PersonDao is create!");
        return new PersonDao();
    }
}
