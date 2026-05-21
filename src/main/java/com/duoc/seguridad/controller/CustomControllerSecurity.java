package com.duoc.seguridad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomControllerSecurity {

    @GetMapping("/app/index_normal")
    public String indexNormal(){
        return "Bienvenido al sitio publico !!!!";
    }

    @GetMapping("/app/info")
    public String infoPublica(){
        return "Bienvenido a la info publica";
    }

    @GetMapping("/app/index_protegido")
    public String indexProtegido(){
        return "Bienvenido al area protegida !!!";
    }

    @GetMapping("/app/admin")
    public String zonaAdmin(){
        return "Zona de administradores";
    }

}
