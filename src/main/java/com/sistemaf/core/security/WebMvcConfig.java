package com.sistemaf.core.security;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebMvcConfig implements WebMvcConfigurer {


  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("pages/login");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }
}
