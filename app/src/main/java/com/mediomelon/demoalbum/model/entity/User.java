package com.mediomelon.demoalbum.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    @PrimaryKey
    @NotNull
    private String id;
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_username")
    private String username;
    @ColumnInfo(name = "user_website")
    private String website;
    @Embedded
    private Address address;
    @Embedded
    private Company company;
    @ColumnInfo(name = "user_email")
    private String email;
    @ColumnInfo(name = "user_phone")
    private String phone;
    @ColumnInfo(name = "user_photo")
    private int photo;
    @ColumnInfo(name = "user_status")
    private String status;
    @ColumnInfo(name = "user_creation_date")
    private String date;

    public User(String id, String name, String username, String website, Address address, Company company, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.website = website;
        this.address = address;
        this.company = company;
        this.email = email;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Entity
    public static class Address implements Serializable {
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "address_street")
        private String street;
        @ColumnInfo(name = "address_suite")
        private String suite;
        @ColumnInfo(name = "address_city")
        private String city;
        @ColumnInfo(name = "address_zipcode")
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

    @Entity
    public static class Company implements Serializable {
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "company_name")
        private String name;

        public Company(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(@NotNull String name) {
            this.name = name;
        }
    }
}
