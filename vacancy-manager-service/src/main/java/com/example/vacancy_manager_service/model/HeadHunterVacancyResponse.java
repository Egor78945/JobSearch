package com.example.vacancy_manager_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadHunterVacancyResponse implements Serializable {
    private Item[] items;

    public HeadHunterVacancyResponse(Item[] items) {
        this.items = items;
    }

    public HeadHunterVacancyResponse() {
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "HeadHunterVacancyResponse{" +
                "items=" + Arrays.toString(items) +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String name;
        private Area area;
        private Salary salary;
        private Address address;

        public Item(String name, Area area, Salary salary, Address address) {
            this.name = name;
            this.area = area;
            this.salary = salary;
            this.address = address;
        }

        public Item() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }

        public Salary getSalary() {
            return salary;
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", area=" + area +
                    ", salary=" + salary +
                    ", address=" + address +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Area {
        private String name;

        public Area(String name) {
            this.name = name;
        }

        public Area() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Salary {
        private Integer from;
        private Integer to;
        private String currency;
        private boolean gross;

        public Salary(Integer from, Integer to, String currency, boolean gross) {
            this.from = from;
            this.to = to;
            this.currency = currency;
            this.gross = gross;
        }

        public Salary() {
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public boolean isGross() {
            return gross;
        }

        public void setGross(boolean gross) {
            this.gross = gross;
        }

        @Override
        public String toString() {
            return "Salary{" +
                    "from=" + from +
                    ", to=" + to +
                    ", currency='" + currency + '\'' +
                    ", gross=" + gross +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        private String city;
        private String street;
        private String building;

        public Address(String city, String street, String building) {
            this.city = city;
            this.street = street;
            this.building = building;
        }

        public Address() {
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "city='" + city + '\'' +
                    ", street='" + street + '\'' +
                    ", building='" + building + '\'' +
                    '}';
        }
    }
}
