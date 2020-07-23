package com.beverage.factory.demo.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Item implements Serializable {

    private String name;
    private Map<String, Ingredient> ingredientMap;
    private double price;

    public Item() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Item(String name, Map<String, Ingredient> ingredientMap, double price) {
        this.name = name;
        this.ingredientMap = ingredientMap;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

    public void setIngredientMap(Map<String, Ingredient> ingredientMap) {
        this.ingredientMap = ingredientMap;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", ingredientMap=" + ingredientMap +
                ", price=" + price +
                '}';
    }
}
