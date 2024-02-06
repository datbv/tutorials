package com.datbv.di.service;

import com.datbv.di.annotation.Autowire;
import com.datbv.di.annotation.Component;
import com.datbv.di.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderService {

  @Autowire
  private PaymentService paymentService;

  @Autowire
  private RestaurantService restaurantService;

  @PostConstruct
  void postInitiate() {
    log.info("Do something after creating orderService instance");
  }

  public void makeOrder() {
    paymentService.doSomething();
    restaurantService.doSomething();
  }
}
