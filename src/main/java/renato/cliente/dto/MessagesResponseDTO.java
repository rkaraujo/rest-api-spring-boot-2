package renato.cliente.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class MessagesResponseDTO {

    private final List<String> messages;

    public MessagesResponseDTO(String message) {
        this.messages = Collections.singletonList(message);
    }

    public MessagesResponseDTO(List<String> messages) {
        this.messages = messages;
    }
}
