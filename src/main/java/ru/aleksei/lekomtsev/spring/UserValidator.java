package ru.aleksei.lekomtsev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

// сделали отдельный класс, чтобы был код в ооп стиле(а не отдельный метод для проверки)
// если изменится способ проверки юзера, надо будет код проверки исправить только в одном месте
// юзер валидатор может использоваться в раззных классах
// это называется single responsibility(s в solid)
@Component
public class UserValidator {

    @Autowired
    private Validator validator;

    public boolean isValid(User user) {
        return user.name != null;
    }

    public Set<ConstraintViolation<User>> validateUser(User user) {
        return validator.validate(user);
    }
}
