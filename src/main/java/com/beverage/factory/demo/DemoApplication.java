package com.beverage.factory.demo;

import com.beverage.factory.demo.entity.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Map<String, Item> createMenu() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Item>> typeReference = new TypeReference<List<Item>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/inputFile.json");
        List<Item> items;
        try {
            items = mapper.readValue(inputStream, typeReference);
            final HashMap<String, Item> itemMap = new HashMap<>(items.size());
            items.stream().forEach(item -> {
                itemMap.put(item.getName(), item);
            });
            log.info("ItemMap : {}", itemMap);
            return itemMap;
        } catch (IOException e) {
            System.out.println("Unable to load menu items: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
