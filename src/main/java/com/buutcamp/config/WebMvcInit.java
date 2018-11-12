package com.buutcamp.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class WebMvcInit implements WebApplicationInitializer {

    //implementing method so that the WebApplicationInitializer requirement is fulfilled
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();

        //combining this class with the annotated configuration class we created earlier
        rootContext.register(WebMvcConfig.class);

        //initializing the servlet
        ServletRegistration.Dynamic registration =
                servletContext.addServlet("the_project",
                        new DispatcherServlet(rootContext));
        registration.addMapping("/");
        registration.setLoadOnStartup(1);
    }
}
