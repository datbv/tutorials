package com.datbv.di.service;

import com.datbv.di.annotation.Instance;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Instance
public class RestaurantService {

  public void doSomething() {
    log.info("Restaurant service does something");
  }

  public void logToday() {
    log.info("Today: {}", Instant.now());
  }

}
