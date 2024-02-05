package com.datbv.di.service;

import com.datbv.di.annotation.Dependency;
import com.datbv.di.annotation.Instance;
import com.datbv.di.annotation.PostInitiate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Instance
public class OrderService {

  @Dependency
  private PaymentService paymentService;

  @Dependency
  private RestaurantService restaurantService;

  @PostInitiate
  void postInitiate() {
    log.info("Do something after creating orderService instance");
  }

  public void makeOrder() {
    paymentService.doSomething();
    restaurantService.doSomething();
  }
}
