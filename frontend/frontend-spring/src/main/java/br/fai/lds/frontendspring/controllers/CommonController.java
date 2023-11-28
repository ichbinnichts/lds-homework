package br.fai.lds.frontendspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {
    @GetMapping
    public String getHomePage(){
        return "/common/index";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "/common/login";
    }
}
