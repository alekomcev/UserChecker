package ru.aleksei.lekomtsev.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

// запускает основную логику, делает конфиг спринга
// первичный запуск, инициализирует спринг
public class ProgramUserCheckerInitializer {
    public static void main(String[] args) throws IOException {
        final String             packageName        = ProgramUserCheckerInitializer.class.getPackage().getName();
        final Class<ObjectMapper> objectMapperClass = ObjectMapper.class;
        // читает аннотации классов из пакета или из массива классов чтобы сконфигурировать апп контекст
        final GenericApplicationContext applicationContext = new AnnotationConfigApplicationContext(packageName);
//        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(packageName);

        final ProgramUserChecker programUserChecker = applicationContext.getBean(ProgramUserChecker.class);
        programUserChecker.run();
        applicationContext.close();

    }
}
