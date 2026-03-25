package com.example.vacancy_manager_service.model;

public class Vacancy {
    private String title;
    private int salary;
    private String currency;
    private String area;

    public Vacancy(String title, int salary, String currency, String area) {
        this.title = title;
        this.salary = salary;
        this.currency = currency;
        this.area = area;
    }

    public Vacancy() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
