package ru.aleksei.lekomtsev.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

//@Component
// Annotation providing a convenient and declarative mechanism for adding a PropertySource to Spring's Environment.
// To be used in conjunction with @Configuration classes.
// java конфигурация для аннотейшен конфиг контекст
@Configuration
@PropertySource(value = "classpath:/meta.properties")
public class Config {
//    @Value(value = "${files.paths}")
    @Value(value = "#{'${files.paths}'.split(',')}")
    private List<String> filesPathsList;

    public List<String> getFilesPathsList() {
        return filesPathsList;
    }

    // название метода = название бина
    // этот метод будет вызываться спрингом когда будет создавать апп контекст. В итоге этот объект(бин)
    // добавится в апп контекст
    // можно засетапить конфиги для одного эксемпляра маппера. Он будет использоваться во многих местах. Поэтому класс называет
    // конфиг(в том числе)
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
