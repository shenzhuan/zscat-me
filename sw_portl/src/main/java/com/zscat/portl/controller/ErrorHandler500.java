package com.zscat.cms.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ErrorHandler500 implements HandlerExceptionResolver {
 @Override
 public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
  // 异常处理逻辑 goes here
  return new ModelAndView("error/500");
 }
}