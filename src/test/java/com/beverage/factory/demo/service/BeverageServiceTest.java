package com.beverage.factory.demo.service;

import com.beverage.factory.demo.entity.Ingredient;
import com.beverage.factory.demo.entity.Item;
import com.beverage.factory.demo.entity.ResponseDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BeverageServiceTest {

    @Mock
    private Map<String, Item> itemMap;

    @InjectMocks
    private BeverageService service;

    private static Map<String, Item> inputMap = new HashMap<>();

    @BeforeAll
    public static void setup() {
        Map<String, Ingredient> ingredientMap = new HashMap<>();
        ingredientMap.put("coffee", new Ingredient("coffee", 3.00, true));
        ingredientMap.put("milk", new Ingredient("milk", 1.00, false));
        ingredientMap.put("sugar", new Ingredient("sugar", 0.50, false));
        ingredientMap.put("water", new Ingredient("water", 0.50, true));
        inputMap.put("coffee", new Item("coffee", ingredientMap, 5.00));
    }

    @Test
    public void validStringShouldReturnCorrectPrice() {
        List<String> input = Arrays.asList("coffee, -milk");
        Mockito.when(itemMap.get("coffee")).thenReturn(inputMap.get("coffee"));
        List<ResponseDetails> result = new ArrayList<>();
        result.add(new ResponseDetails("1", 4.00));
        service.processOrder(input);
        Assertions.assertEquals(result, service.processOrder(input));
    }

    @Test
    public void absentMenuItemShouldThrowException() {
        List<String> input = Arrays.asList("banana smoothie, -milk, -sugar");
        Mockito.when(itemMap.get("banana smoothie")).thenReturn(null);
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> service.processOrder(input));
        Assertions.assertEquals("banana smoothie is not in the menu", exception.getMessage());
    }

    @Test
    public void emptyStringShouldThrowException() {
        List<String> input = Arrays.asList("");
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> service.processOrder(input));
        Assertions.assertEquals("Order cannot be empty", exception.getMessage());
    }
}
