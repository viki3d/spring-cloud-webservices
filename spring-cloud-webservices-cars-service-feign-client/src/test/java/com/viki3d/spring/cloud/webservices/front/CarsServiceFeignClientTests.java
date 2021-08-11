package com.viki3d.spring.cloud.webservices.front;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testing FeignClient properly initialized in the application context.
 */
@SpringBootTest
public class CarsServiceFeignClientTests {

  @Autowired
  private CarsServiceFeignClient carsServiceFeignClient;

  @Test
  public void testContextLoads() {
    assertThat(carsServiceFeignClient).isNotNull();
  }
    
}
