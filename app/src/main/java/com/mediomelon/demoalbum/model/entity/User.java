package com.mediomelon.demoalbum.model.entity;

public class User {
    private String id;
    private String name;
    private String username;
    private String website;
    private Address address;
    private Company company;
    private String email;
    private String phone;
    private int photo;

    public User(String id, String name, String username, String website, Address address, Company company, String email, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.website = website;
        this.address = address;
        this.company = company;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getWebsite() {
        return website;
    }

    public Address getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;

        public Address(String street, String suite, String city, String zipcode) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipcode = zipcode;
        }

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }
    }

    public class Company {
        private String name;
        private String catchPhrase;
        private String bs;

        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }

        public String getName() {
            return name;
        }
    }
}
