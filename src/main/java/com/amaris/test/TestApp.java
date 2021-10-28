/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@SpringBootApplication
@EnableCaching //enables Spring Caching functionality
public class TestApp {

  @Value("${configuration.properties.time-zone}")
  private String timeZone;

  public static void main(
      String[] args) {
    SpringApplication.run(TestApp.class, args);
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
  }

}
