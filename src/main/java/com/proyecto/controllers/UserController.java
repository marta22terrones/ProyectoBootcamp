package com.proyecto.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/signUp")
    public String loginUser(ModelMap map){  

        List<Genre> genres = filmService.getGenres();
        User user = new User();
        map.addAttribute("user", user);
        map.addAttribute("genres", genres);
        return "signUp";
    }

    @PostMapping("/createUser")
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


    @GetMapping("/user")
    public String showUser (ModelMap map) {
        List<Genre> genres = filmService.getGenres();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }


        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Object user= auth.getCredentials();
        User user = userService.findByEmail(username);
        map.addAttribute("user", user);
        map.addAttribute("genres", genres);
        map.addAttribute("id", user.getId());
        return "username";
    }

    @GetMapping("/changePassword/{id}")
    public String changePass (ModelMap map) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        List<Genre> genres = filmService.getGenres();
        User user = userService.findByEmail(username);
        map.addAttribute("user", user);
        map.addAttribute("genres", genres);
        return "changePass";
    }

    @PostMapping("/changePasswordPOST")
    public String change(@ModelAttribute(name="user") User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword1 = encoder.encode(user.getPassword());
        String encodedPassword2 = encoder.encode(user.getMatchingPassword());
        user.setPassword(encodedPassword1);
        user.setMatchingPassword(encodedPassword2);
        userService.saveUser(user);
        return "redirect:/changePassword/"+ user.getId()+"?exito";
    }

}
