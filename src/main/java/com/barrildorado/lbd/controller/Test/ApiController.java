package com.barrildorado.lbd.controller.Test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    @PostMapping("/admin/registrar")
    public String registrarAdmin() {
        return "bienvendido pag ";
    }
    @PostMapping("/user/registrar")
    public String registrarUser() {
        return "bienvendido pag ";
    }
}
