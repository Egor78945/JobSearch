package com.example.vacancy_manager_service.model.web.gigachat;

import java.util.Arrays;

public class GigachatRequest {
    private String model;
    private Message[] messages;

    public GigachatRequest(String model, Message[] messages) {
        this.model = model;
        this.messages = messages;
    }

    public GigachatRequest() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "GigachatRequest{" +
                "model='" + model + '\'' +
                ", messages=" + Arrays.toString(messages) +
                '}';
    }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public Message() {
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "role='" + role + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

}
