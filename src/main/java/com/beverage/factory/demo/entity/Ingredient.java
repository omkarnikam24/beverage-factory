package com.beverage.factory.demo.entity;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private String name;
    private double price;
    private boolean mandatory;

    public Ingredient() {
    }

    public Ingredient(String name, double price, boolean mandatory) {
        this.name = name;
        this.price = price;
        this.mandatory = mandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", mandatory=" + mandatory +
                '}';
    }
}
