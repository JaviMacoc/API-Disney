package com.alkemy.controllers;

import com.alkemy.DataBaseMocking;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    DataBaseMocking dataBaseMocking;
    
    @GetMapping("/")
    public String getIndex() throws IOException {
        dataBaseMocking.rellenar();
        return "index";
    }

    @PostMapping("/auth/register")
    public void register() {
    }

    @GetMapping("auth/login")
    public String login() {
        return "login";
    }
    
}
