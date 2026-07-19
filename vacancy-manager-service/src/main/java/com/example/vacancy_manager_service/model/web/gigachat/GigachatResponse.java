package com.example.vacancy_manager_service.model.web.gigachat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GigachatResponse {
    private Choice[] choices;

    public GigachatResponse(Choice[] choices) {
        this.choices = choices;
    }

    public GigachatResponse() {
    }

    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "GigachatResponse{" +
                "choices=" + Arrays.toString(choices) +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private Message message;

        public Choice(Message message) {
            this.message = message;
        }

        public Choice() {
        }

        public Message getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "Choice{" +
                    "message=" + message +
                    '}';
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Message {
            private String content;

            public Message(String content) {
                this.content = content;
            }

            public Message() {
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
                        "content='" + content + '\'' +
                        '}';
            }
        }
    }
}
