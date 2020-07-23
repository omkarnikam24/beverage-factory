package com.beverage.factory.demo.service;

import com.beverage.factory.demo.entity.Ingredient;
import com.beverage.factory.demo.entity.Item;
import com.beverage.factory.demo.entity.ResponseDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BeverageService {

    private static final Logger log = LoggerFactory.getLogger(BeverageService.class);

    @Autowired
    private Map<String, Item> itemMap;

    public List<ResponseDetails> processOrder(List<String> orders) {
        log.info("Processing order");
        int orderNumber = 0;
        List<ResponseDetails> result = new ArrayList<>();
        for (String order : orders) {
            double total = 0;
            if(StringUtils.isEmpty(order))
                throw new RuntimeException("Order cannot be empty");
            String[] itemsArr = order.split(",");
            Item item = itemMap.get(itemsArr[0].toLowerCase());
            if (null != item)
                total = item.getPrice();
            else
                throw new RuntimeException(itemsArr[0] + " is not in the menu");

            /*
            Check if there are any exclusion item.
            If present then subtract their prices from the total price of an item.
            Else return the item price as it is.
            */
            if (itemsArr.length > 1) {
                for (int i = 1; i < itemsArr.length; i++) {
                    String key = itemsArr[i].trim().substring(1).toLowerCase();
                    Ingredient ingr = item.getIngredientMap().get(key);
                    if (null != ingr && !ingr.isMandatory())
                        total -= item.getIngredientMap().get(key).getPrice();
                }
            }
            result.add(new ResponseDetails(String.valueOf(++orderNumber), total));
        }
        log.info("Total Orders processed : {}", result.size());
        return result;
    }
}
