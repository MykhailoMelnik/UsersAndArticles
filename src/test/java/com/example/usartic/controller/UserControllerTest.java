package com.example.usartic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.usartic.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import com.tutorialspoint.demo.model.Product;

public class UserControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getProductsList() throws Exception {

        String uri = "/users/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] productlist = super.mapFromJson(content, User[].class);
        assertTrue(productlist.length > 0);
    }
}
