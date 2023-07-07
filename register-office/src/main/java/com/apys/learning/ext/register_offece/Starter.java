package com.apys.learning.ext.register_offece;

import com.apys.learning.ext.register_offece.rest.MarriageController;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
    public static void main(String[] args) {
        //Самое главное в Spring - мы ни где в коде не создавали (new) объектов наших классов

        // Создаем новый мир из связанных бинов
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"springContext.xml"}
        );
        //Запрашиваем из этого мира нужный бин
        MarriageController controller = context.getBean("controller", MarriageController.class);
        //Вызваем нужный метод
        controller.findMarriageCertificate(new MarriageRequest());
    }
}
