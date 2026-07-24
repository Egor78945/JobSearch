package com.example.vacancy_manager_service.model.web.head_hunter;

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
        private String published_at;
        private WorkFormat[] work_format;
        private Experience experience;
        private EmploymentForm employment_form;
        private boolean internship;
        private String url;

//        public Item(String name, Area area, Salary salary, Address address, String published_at, WorkFormat[] work_format, Experience experience, EmploymentForm employment_form, boolean internship, String alternate_url) {
//            this.name = name;
//            this.area = area;
//            this.salary = salary;
//            this.address = address;
//            this.published_at = published_at;
//            this.work_format = work_format;
//            this.experience = experience;
//            this.employment_form = employment_form;
//            this.internship = internship;
//            this.url = alternate_url;
//        }

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

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public WorkFormat[] getWork_format() {
            return work_format;
        }

        public void setWork_format(WorkFormat[] work_format) {
            this.work_format = work_format;
        }

        public Experience getExperience() {
            return experience;
        }

        public void setExperience(Experience experience) {
            this.experience = experience;
        }

        public EmploymentForm getEmployment_form() {
            return employment_form;
        }

        public void setEmployment_form(EmploymentForm employment_form) {
            this.employment_form = employment_form;
        }

        public boolean isInternship() {
            return internship;
        }

        public void setInternship(boolean internship) {
            this.internship = internship;
        }

        public String getUrl() {
            return url;
        }

        public void setAlternate_url(String alternate_url) {
            this.url = alternate_url;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", area=" + area +
                    ", salary=" + salary +
                    ", address=" + address +
                    ", published_at='" + published_at + '\'' +
                    ", work_format=" + Arrays.toString(work_format) +
                    ", experience=" + experience +
                    ", employment_form=" + employment_form +
                    ", internship=" + internship +
                    ", url='" + url + '\'' +
                    '}';
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
            private Metro metro;

            public Address(String city, String street, String building, Metro metro) {
                this.city = city;
                this.street = street;
                this.building = building;
                this.metro = metro;
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

            public Metro getMetro() {
                return metro;
            }

            public void setMetro(Metro metro) {
                this.metro = metro;
            }

            @Override
            public String toString() {
                return "Address{" +
                        "city='" + city + '\'' +
                        ", street='" + street + '\'' +
                        ", building='" + building + '\'' +
                        ", metro=" + metro +
                        '}';
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Metro {
                private String station_name;
                private String line_name;

                public Metro(String station_name, String line_name) {
                    this.station_name = station_name;
                    this.line_name = line_name;
                }

                public Metro() {
                }

                public String getStation_name() {
                    return station_name;
                }

                public void setStation_name(String station_name) {
                    this.station_name = station_name;
                }

                public String getLine_name() {
                    return line_name;
                }

                public void setLine_name(String line_name) {
                    this.line_name = line_name;
                }

                @Override
                public String toString() {
                    return "Metro{" +
                            "station_name='" + station_name + '\'' +
                            ", line_name='" + line_name + '\'' +
                            '}';
                }
            }

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class WorkFormat {
            private String name;

            public WorkFormat(String name) {
                this.name = name;
            }

            public WorkFormat() {
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "WorkFormat{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Experience {
            private String name;

            public Experience(String name) {
                this.name = name;
            }

            public Experience() {
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Experience{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class EmploymentForm {
            private String name;

            public EmploymentForm(String name) {
                this.name = name;
            }

            public EmploymentForm() {
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "EmploymentForm{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
    }
}
