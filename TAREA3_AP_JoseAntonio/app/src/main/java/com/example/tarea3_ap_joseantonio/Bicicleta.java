package com.example.tarea3_ap_joseantonio;

public class Bicicleta {
    String owner;
    String email;
    String city;
    String description ;
    String country;
    String location;
    String image;

    public Bicicleta(String owner, String email, String city, String description, String country, String location, String image) {
        this.owner = owner;
        this.email = email;
        this.city = city;
        this.description = description;
        this.country = country;
        this.location = location;
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
