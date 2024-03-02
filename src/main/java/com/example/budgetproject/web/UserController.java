package com.example.budgetproject.web;

import com.example.budgetproject.dto.UserDto;
import com.example.budgetproject.service.impl.user.DBUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping()
public class UserController {

    private final DBUserDetailsService userService;

    @PostMapping("/register")
    public ModelAndView save (@Valid @ModelAttribute UserDto user, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/public");
        userService.save(user);
        return modelAndView;
    }

    @GetMapping("/login")
    public String login (){
        return "/user";
    }
}
