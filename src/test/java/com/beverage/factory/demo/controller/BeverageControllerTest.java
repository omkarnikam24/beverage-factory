package com.beverage.factory.demo.controller;

import com.beverage.factory.demo.entity.ResponseDetails;
import com.beverage.factory.demo.service.BeverageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BeverageControllerTest {

    @Mock
    private BeverageService service;

    @InjectMocks
    private BeverageController controller;


    @Test
    public void validStringShouldReturnCorrectPrice() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<String> input = Arrays.asList("coffee, -milk");

        List<ResponseDetails> result = new ArrayList<>();
        result.add(new ResponseDetails("1", 4.00));

        Mockito.when(service.processOrder(input)).thenReturn(result);
        ResponseEntity<?> responseEntity = controller.calculateTotal(input);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(result, responseEntity.getBody());
    }

    @Test
    public void emptyInputShouldReturn400() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<String> input = new ArrayList<>();
        ResponseEntity<?> responseEntity = controller.calculateTotal(input);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("Invalid Order, input array cannot be empty", responseEntity.getBody());
    }

    @Test
    public void absentMenuItemShouldReturn406() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        List<String> input = Arrays.asList("abcd, -milk");
        Mockito.doThrow(RuntimeException.class).when(service).processOrder(input);
        ResponseEntity<?> responseEntity = controller.calculateTotal(input);
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
    }
}
