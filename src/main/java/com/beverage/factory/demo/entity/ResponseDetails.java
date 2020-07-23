package com.beverage.factory.demo.entity;

import java.util.Objects;

public class ResponseDetails {

    private String orderNumber;
    private double price;

    public ResponseDetails(String orderNumber, double price) {
        this.orderNumber = orderNumber;
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDetails that = (ResponseDetails) o;
        return orderNumber.equals(that.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ResponseDetails{" +
                "orderNumber='" + orderNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
