package com.viki3d.spring.cloud.webservices.front;

import static org.assertj.core.api.Assertions.assertThat;

import com.viki3d.spring.cloud.webservices.front.api.model.Car;
import java.util.List;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for the FeignClient. 
 */
@SpringBootTest
public class CarsServiceFeignClientIT {

  @Autowired
  private CarsServiceFeignClient carsServiceFeignClient;

  @Test
  public void testGetCars() throws Exception {
    boolean carsServiceIsRunning = true;
    List<Car> cars = null;
    try {
      cars = carsServiceFeignClient.getAllCars();
    } catch (Exception ex) {
      carsServiceIsRunning = false;
    }

    Assumptions.assumeTrue(carsServiceIsRunning);
    assertThat(cars != null);
    assertThat(cars.size() == 4);
  }

  @Test
  public void testGetCar1() throws Exception {
    boolean carsServiceIsRunning = true;
    Car car = null;
    try {
      car = carsServiceFeignClient.getCarById(1);
    } catch (Exception ex) {
      carsServiceIsRunning = false;
    }

    Assumptions.assumeTrue(carsServiceIsRunning);
    assertThat(car.toString().contains("Mazda"));
  }  

}
