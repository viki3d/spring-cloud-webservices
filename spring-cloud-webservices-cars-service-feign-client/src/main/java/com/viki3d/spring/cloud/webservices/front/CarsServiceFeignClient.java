package com.viki3d.spring.cloud.webservices.front;

import com.viki3d.spring.cloud.webservices.front.api.model.Car;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The consumer of microservice "CARS-CLOUD-SERVICE".
 * Performs:
 * <ul>
 *  <li>Service discovery (you don't need service discovery code)</li>
 *  <li>Load balance (if balancer dependency is present)</li>
 *  <li>Consume restful service (you don't need RestTemplate code)</li>
 * </ul>
 *
 * @author Victor Kirov
 */
//@FeignClient(value = "cars-cloud-service", url = "http://localhost:8081/")
@FeignClient(value = "cars-cloud-service", configuration = FeignConfig.class)
public interface CarsServiceFeignClient {

  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/cars")
  public List<Car> getAllCars();

  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/cars/{carId}")
  public Car getCarById(@PathVariable("carId") long id);

}
