package com.apys.learning.ext.student.servlet;

import com.apys.learning.ext.student.domain.University;
import com.apys.learning.ext.student.service.UniversityService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "UniversityListServlet", urlPatterns = {"/universityList"})
public class UniversityListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Некое описание контекста сервлета: какие переменные, где находится, сервера...
        ServletContext servletContext = getServletContext();
        // Позволяет подружить Spring с Servlet (передачей в сервлет контекста Spring: Бинов и т.п.)
        WebApplicationContext webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);

        // Получаем наш Бин UniversityService из контекста Spring
        UniversityService service = webApplicationContext.getBean(UniversityService.class);

        //Получаем данные из автоматического метода репозитария
        List<University> list = service.findUniversities();

        // Тут задаем, то что хотим передать в JSP страницу по типу ключ-значение
        req.setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        req.setAttribute("universities", list);

        // Передаем управление странице JSP (на этой странице пример scriptlet)
//        getServletContext().getRequestDispatcher("/universityList.jsp").forward(req, resp);
        // Передаем управление странице JSP (на этой странице пример с тегами JSTL)
        getServletContext().getRequestDispatcher("/universityList_jstl.jsp").forward(req, resp);
    }
}
