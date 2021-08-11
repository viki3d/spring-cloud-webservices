package com.viki3d.spring.cloud.webservices.front.controllers.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Tests against CarsWebService logic. 
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CarsRestControllerTests {
	
  @Autowired
  private MockMvc mockMvc;
  
  // Since this is not a static field, it is not named like BASE_PATH
  private final String basePath = "/api/v1/cars";
  
  @Test
  void smokeTestServiceIsOk() throws Exception {
    this.mockMvc.perform(get(basePath)).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void sanityTestServiceResponseType() throws Exception {
    MvcResult result;
    
    result = this.mockMvc.perform(get(basePath)).andReturn();
    // Handle "application/json", "application/json;charset=UTF-8"
    assertTrue(result.getResponse().getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE));
    
    result = this.mockMvc.perform(get(basePath + "/1")).andReturn();
    // Handle "application/json", "application/json;charset=UTF-8"
    assertTrue(result.getResponse().getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE));
  }
  
  @Test
  void testGetCars() throws Exception {
    MvcResult result = this.mockMvc.perform(get(basePath))
        .andExpect(status().isOk())
        .andReturn();
    String jsonResponse = result.getResponse().getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonResponse);
    assertEquals(jsonArray.length(), 4);
  }
  
  
  @Test
  void testGetCar1() throws Exception {
    this.mockMvc.perform(get(basePath + "/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.brand", is("Mazda")))
        .andExpect(jsonPath("$.model", is("6")))
        .andExpect(jsonPath("$.color", is("red")))
        ;
  }
  
  @Test
  void testGetCar2() throws Exception {
    this.mockMvc.perform(get(basePath + "/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.brand", is("Mazda")))
        .andExpect(jsonPath("$.model", is("3")))
        .andExpect(jsonPath("$.color", is("green")))
        ;
  }

  
}
