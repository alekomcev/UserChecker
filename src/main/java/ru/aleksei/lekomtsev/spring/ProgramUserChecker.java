package ru.aleksei.lekomtsev.spring;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

// точка входа спринговая, можно сделать поля как бины
// можно было сделать через конфиг класс как бин, но через анн компонент проще
// маппер делаем через конфиг, так как из third party lib, над ним нельзя поставить компонент аннотацию
@Component
public class ProgramUserChecker {
    private static final String CLASS_NAME = ProgramUserChecker.class.getName();

    private final Config         config;
    private final UserFileParser userFileParser;
    private final UserValidator  userValidator;
    private final Logger         logger;

    public ProgramUserChecker(Config config, UserFileParser userFileParser, UserValidator userValidator)
            throws IOException {
        this.config = config;
        this.userFileParser = userFileParser;
        this.userValidator = userValidator;
        this.logger = initializeLogger();
    }

    // верхнеуровневая уровневая логика
    // прочитать файлы, распарсить
    // проверить юзеров и вывести валидных
    public void run() {
        final List<String> filesPathsList = config.getFilesPathsList();
        final List<User>   users          = parseFiles(filesPathsList);
        for (User user : users) {
            if (userValidator.isValid(user)) {
                logger.log(Level.FINE, "The user is valid:" + user);
            }
        }
//        validateUsers(users);
    }

    private void validateUsers(List<User> users) {
        for (User user : users) {
            if (userValidator.isValid(user)) {
                logger.log(Level.FINE, "The user is valid:" + user);
            }
        }
    }

    private List<User> parseFiles(List<String> filesPathsList) {
        final List<User> result = new ArrayList<>();
        for (String filePath : filesPathsList) {
            final File fileToParse = new File(filePath);
            try {
                final User user = userFileParser.parse(fileToParse);
                result.add(user);
            } catch (UserFileParserException e) {
                logger.log(Level.WARNING, "Couldn't parse file:" + fileToParse, e);
            }
        }
        return result;
    }

    private Logger initializeLogger() throws IOException {
        final String loggerConfigString =
                "handlers=java.util.logging.ConsoleHandler\n"
                        + "java.util.logging.ConsoleHandler.level=FINE\n"
                        + "ru.aleksei.lekomtsev.spring.ProgramUserChecker.level=FINE\n";

        LogManager
                .getLogManager()
                .readConfiguration
                        (
                                new ByteArrayInputStream
                                        (
                                                loggerConfigString.getBytes()
                                        )
                        );

        return Logger.getLogger(CLASS_NAME);
    }
}
