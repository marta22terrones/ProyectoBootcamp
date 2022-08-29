package com.proyecto.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.entities.User;
import com.proyecto.service.IUserService;

@Controller
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private IUserService userService;

    @GetMapping("/signUp")
    public ModelAndView loginUser(ModelMap map){
        ModelAndView mav = new ModelAndView();

        List<String> genres = new ArrayList<>();
        genres.add(User.Genre.MAN.toString());
        genres.add(User.Genre.WOMAN.toString());
        genres.add(User.Genre.OTHER.toString());

        map.addAttribute("genres", genres);

        mav.setViewName("createUser");   // * Nombre del HTML donde se encuentra el Login
        return mav;
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute(name="username") String username, 
                                    @RequestParam(name = "avatar", required = false) MultipartFile file) {

        if (!file.isEmpty()) {
            String rutaAbsoluta = "C://Users/mterrone/Documents/carpetaRecursos";
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());       
    
            try {
                byte [] bytesImages = file.getBytes();
                Files.write(rutaCompleta, bytesImages);
    
                // user.setAvatar(file.getOriginalFilename());
    
                // userService.saveUser(user);

                User user = new User();
                user.setUsername(username);

                userService.saveUser(user);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
            return "redirect:/catalogue";                                  
    }
}
