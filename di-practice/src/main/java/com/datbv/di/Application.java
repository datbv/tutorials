package com.datbv.di;

import com.datbv.di.annotation.Autowire;
import com.datbv.di.annotation.Component;
import com.datbv.di.loader.ContextLoader;
import com.datbv.di.loader.Runner;
import com.datbv.di.service.OrderService;
import com.datbv.di.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Component
public class Application implements Runner {

  @Autowire
  private OrderService orderService;

  public static void main(String[] args) {
    ContextLoader.getInstance().load("com.datbv.di");
  }

  @Override
  public void run() {
    log.info("Application ready to start");

    orderService.makeOrder();

    val restaurantService = ContextLoader.getInstance()
        .getBean(RestaurantService.class);
    restaurantService.logToday();
  }
}
