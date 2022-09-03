package com.proyecto.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.dao.IUserDao;
import com.proyecto.entities.Genre;
import com.proyecto.entities.User;
import com.proyecto.service.IFilmService;
import com.proyecto.service.IUserService;

@Controller
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IFilmService filmService;

    // * SIGNUP GET
    @GetMapping("/signUp")
    public String loginUser(ModelMap map){  

        List<Genre> genres = filmService.getGenres();
        User user = new User();
        map.addAttribute("user", user);
        map.addAttribute("genres", genres);
        return "signUp";
    }

    // * SIGNUP POST
    @PostMapping("/process_signUp")
    public String createUser(@ModelAttribute(name="user") User user,
                                    @RequestParam(name = "imagen", required = false) MultipartFile file) {

        if (!file.isEmpty()) {
            String rutaAbsoluta = "C://Users/mterrone/Documents/recursos/";
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + file.getOriginalFilename());       
    
            try {
                byte [] bytesImages = file.getBytes();
                Files.write(rutaCompleta, bytesImages);

                user.setAvatar(file.getOriginalFilename());

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodedPassword1 = encoder.encode(user.getPassword());
                String encodedPassword2 = encoder.encode(user.getMatchingPassword());
                user.setPassword(encodedPassword1);
                user.setMatchingPassword(encodedPassword2);
                userService.saveUser(user);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
            return "redirect:/signUp?exito";                                  
    }

    @GetMapping("/loginUser")
    public String loginPrueba(ModelMap map) {
        User user = new User();
        map.addAttribute("user", user);
        return "loginUser";
    }

    @PostMapping("loginSuccess")
    public String loginSuccess(@RequestParam(name = "email", required = true) String email){

        User userNew = new User();
        userNew = userService.findByEmail(email);

        if (userNew == null) {
            return "redirect:/loginUser?noExito";
        } else {
            return "redirect:/loginUser?exito";
        }
    }

  
}
