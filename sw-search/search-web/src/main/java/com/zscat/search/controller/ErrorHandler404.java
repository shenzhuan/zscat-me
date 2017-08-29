package com.zscat.search.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler404 implements ErrorController {
 private static final String ERROR_PATH = "/error";
 @RequestMapping(value=ERROR_PATH)
 public String handleError(){
  return "error/404";
 }
 @Override
 public String getErrorPath() {
  return ERROR_PATH;
 }
}