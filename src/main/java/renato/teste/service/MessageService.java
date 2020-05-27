package renato.teste.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageService {

    private final static Locale DEFAULT_LOCALE = new Locale("pt", "BR");

    private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String msgProperty, Object... args) {
        return messageSource.getMessage(msgProperty, args, DEFAULT_LOCALE);
    }

}
