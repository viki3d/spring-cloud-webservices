package com.viki3d.spring.cloud.webservices.front.controllers.rest;

import com.viki3d.spring.cloud.webservices.front.CarsServiceFeignClient;
import com.viki3d.spring.cloud.webservices.front.api.CarsApi;
import com.viki3d.spring.cloud.webservices.front.api.model.Car;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Serving the {@code CarsApi}.
 */
@RestController
@RequestMapping("/consume/cars")
public class CarsRestController implements CarsApi {

  @Autowired
  private CarsServiceFeignClient carsServiceFeignClient;

  @Override
  @GetMapping(path = "", produces = "application/json")
  public List<Car> list() {
    return carsServiceFeignClient.getAllCars();
  }

  @Override
  @GetMapping(path = "/{id}", produces = "application/json")
  public Optional<Car> get(@PathVariable long id) {
    return Optional.ofNullable(carsServiceFeignClient.getCarById(id));
  }

}
