package com.example.vacancy_manager_service.model.web.head_hunter;

import com.example.vacancy_manager_service.exception.ValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadHunterVacancyRequest {
    private String text = "";
    private int salary = 30000;
    private String currency = "RUR";
    private int area = 113;
    private int per_page = 5;

    public HeadHunterVacancyRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(text.length() >= 300){
            throw new ValidationException("text is too long", Map.of("text", "text length is longer that 300 symbols"));
        }
        this.text = text;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary <= 0){
            throw new ValidationException("invalid salary", Map.of("salary", "salary is less or equal to zero"));
        }
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        if(currency.length() != 3){
            throw new ValidationException("unknown currency", Map.of("currency", String.format("%s is unknown currency", currency)));
        }
        this.currency = currency;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        if(per_page <= 0 || per_page > 100){
            throw new ValidationException("invalid vacancy count", Map.of("per_page", "vacancy count per page is too low or too high"));
        }
        this.per_page = per_page;
    }

    @Override
    public String toString() {
        return "HeadHunterVacancyRequest{" +
                "text='" + text + '\'' +
                ", salary=" + salary +
                ", currency='" + currency + '\'' +
                ", area=" + area +
                ", per_page=" + per_page +
                '}';
    }
}
