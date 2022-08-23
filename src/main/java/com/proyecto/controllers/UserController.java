package com.proyecto.controllers;

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

    @GetMapping("/login")
    public ModelAndView loginUser(ModelMap map){
        ModelAndView mav = new ModelAndView();
        
/* 
        List<String> sex = new ArrayList<>();
        sex.add(User.Genre.MAN.toString());
        sex.add(User.Genre.WOMAN.toString());
        sex.add(User.Genre.OTHER.toString());

        map.addAttribute("sex", sex);
*/
        mav.setViewName("loginUser");   // * Nombre del HTML donde se encuentra el Login
        return mav;
    }

    @PostMapping("/createUser")
    public ModelAndView createUser(@ModelAttribute(name = "user") User user, 
                                        @RequestParam(name= "image", required = false) MultipartFile file) {
        ModelAndView mav = new ModelAndView();

        userService.saveUser(user);

        return mav;
    }
}
