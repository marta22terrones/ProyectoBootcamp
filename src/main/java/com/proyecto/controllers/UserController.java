package com.proyecto.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.entities.User;
import com.proyecto.service.IUserService;

@Controller
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private IUserService userService;

    @GetMapping("/signUp")
    public String signUpUser(ModelMap map){  
        User user = new User();
        map.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute(name="user") User user,
                                    @RequestParam(name = "imagen", required = false) MultipartFile file) {

        if (!file.isEmpty()) {
            String rutaAbsoluta = "C://Users/mterrone/Documents/carpetaRecursos";
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());       
    
            try {
                byte [] bytesImages = file.getBytes();
                Files.write(rutaCompleta, bytesImages);

                user.setAvatar(file.getOriginalFilename());

                userService.saveUser(user);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
            return "redirect:/signUp?exito";                                  
    }

    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }
}
