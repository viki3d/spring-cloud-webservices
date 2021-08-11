package com.viki3d.spring.cloud.service.registry;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Eureka server tests here.
 *
 * @author Victor Kirov
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RegistryServerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testServerIsUp() throws Exception {
    // http://localhost:8761
    this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
  }

}
