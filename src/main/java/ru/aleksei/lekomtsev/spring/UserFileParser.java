package ru.aleksei.lekomtsev.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

// поставили аннотацию чтобы спринг аннотейшен контекст создал бин для этого класса. Этот класс нахоится в том же пакете,
// к
// который передаем в конструктор аннотейшен конфига
// спринг заинжектит object mapper через конструктор(считается больше плюсов так инжектить)

@Component
public class UserFileParser {
    private final ObjectMapper objectMapper;

    public UserFileParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public User parse(File fileToParse) throws UserFileParserException {
        User result;
        try {
            result = objectMapper.readValue(fileToParse, User.class);
        } catch (IOException e) {
            throw new UserFileParserException();
        }
        return result;
    }

    @Override
    public String toString() {
        return "UserFileParser{" +
                "objectMapper=" + objectMapper +
                '}';
    }
}
