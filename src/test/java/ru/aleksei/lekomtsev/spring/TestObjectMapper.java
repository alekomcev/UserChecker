package ru.aleksei.lekomtsev.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestObjectMapper {
    public static void main(String[] args) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String user1JsonString
                = "{\"name\":\"aleksei\",\"phone\":\"8921887507\",\"mail\":\"alexey.lekomcev@yandex.ru\"}";
        final File file1 = new File("file1.txt");
        try {
//            final User user1 = objectMapper.readValue(user1JsonString, User.class);
            final User user1 = objectMapper.readValue(file1, User.class);
            System.out.println(user1.getName());
            System.out.println(user1.getPhone());
            System.out.println(user1.getMail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
