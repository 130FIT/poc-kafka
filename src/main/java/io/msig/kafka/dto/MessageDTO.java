package io.msig.kafka.dto;


public class MessageDTO {
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return """
                {
                "message": "%s"
                }
                """.formatted(message);
    }
}