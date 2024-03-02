package com.example.budgetproject.web;

import com.example.budgetproject.domain.UserEntity;
import com.example.budgetproject.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("public")
    public ModelAndView publicPage(@ModelAttribute UserEntity user, @ModelAttribute UserDto userDto){
        ModelAndView modelAndView = new ModelAndView("public");
        return modelAndView;
    }
}
