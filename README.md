# Java, Spring Cloud, FeignClient, Microservices, Service Registration and Discovery, Load Balancing

## spring-cloud-webservices-registry-server
This is a Netflix Eureka Registry for our microservices.

## spring-cloud-webservices-cars-service
This is the project of the "cars-cloud-service" microservice. It servers the _Cars API._   
We can run multiple instances of this microservice with:  
>  mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=808X"

## spring-cloud-webservices-cars-service-feign-client

### Feign Client as registry discovery.

> @FeignClient(value = "<span style='color:red'>cars-cloud-service</span>")  
  public interface CarsServiceFeignClient {  
>  
  &nbsp;&nbsp; @RequestMapping(method = RequestMethod.GET, value = "/api/v1/cars")  
  &nbsp;&nbsp; public List<Car> getAllCars();  
>  &nbsp;&nbsp; ...  
}  

The microservice and all of it's instances are located from the Registry server (Netflix Eureka).

### Feign Client as loadbalancer.

The FeignClient is working as load balancer. Here are 2 sequental calls to the microservice "cars-cloud-service". We manually inserted the HTTP header "port" in the "cars-cloud-service" responsed, so we can clearly see which microservice instance is responding to the FeignClient.

>DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] ---> GET http://cars-cloud-service/api/v1/cars HTTP/1.1  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] ---> END HTTP (0-byte body)  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] <--- HTTP/1.1 200 (191ms)  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] connection: keep-alive  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] content-type: application/json  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] keep-alive: timeout=60  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] port: <span style='color:red'>8082</span>  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] [{"id":1,"brand":"Mazda","model":"6","color":"red"},{"id":2,"brand":"Mazda","model":"3","color":"green"},{"id":3,"brand":"Infinity","model":"Q50","color":"gray"},{"id":4,"brand":"Infinity","model":"Q60","color":"black"}]  
DEBUG [nio-exec-2] : [CarsServiceFeignClient#getAllCars] <--- END HTTP (220-byte body)  
>  
>DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] ---> GET http://cars-cloud-service/api/v1/cars HTTP/1.1  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] ---> END HTTP (0-byte body)  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] <--- HTTP/1.1 200 (21ms)  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] connection: keep-alive  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] content-type: application/json  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] keep-alive: timeout=60  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] port: <span style='color:red'>8081</span>  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] [{"id":1,"brand":"Mazda","model":"6","color":"red"},{"id":2,"brand":"Mazda","model":"3","color":"green"},{"id":3,"brand":"Infinity","model":"Q50","color":"gray"},{"id":4,"brand":"Infinity","model":"Q60","color":"black"}]  
DEBUG [nio-exec-3] : [CarsServiceFeignClient#getAllCars] <--- END HTTP (220-byte body)  
> ...

As we can see: the load-balance is working, since each call is served by different instance of the "cars-cloud-service" microservice.

### Feign Client as RestTemplate

We don't need to write RestTemplate code, when using FeignClient:
> @RestController  
  @RequestMapping("/consume/cars")  
  public class CarsRestController implements CarsApi {  
>  
  &nbsp;&nbsp; @Autowired  
  &nbsp;&nbsp; private CarsServiceFeignClient <span style='color:red'>carsServiceFeignClient</span>;  
>  
  &nbsp;&nbsp; @Override  
  &nbsp;&nbsp; @GetMapping(path = "", produces = "application/json")  
  &nbsp;&nbsp; public List<Car> list() {  
  &nbsp;&nbsp;&nbsp;&nbsp;   return <span style='color:red'>carsServiceFeignClient.getAllCars()</span><span>;</span>  
  &nbsp;&nbsp; }  
> ...  
  }
