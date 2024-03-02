package com.example.budgetproject.web;

import com.example.budgetproject.domain.enams.CategoryColor;
import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;
import com.example.budgetproject.service.FinanceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping()
public class CategoryController {

    private final FinanceCategoryService service;

    @GetMapping("/categories")
    public ModelAndView homePage(@ModelAttribute(name="category") FinanceCategoryEntity category){
        ModelAndView modelAndView = new ModelAndView("categories");
        CategoryColor[] values = CategoryColor.values();
        modelAndView.addObject("colors", values);
        modelAndView.addObject("categories", service.findAll());
        return modelAndView;
    }

    @GetMapping("/findAllCategories")
    public ModelAndView findAll (@ModelAttribute(name="exp") FinanceCategoryEntity entity){
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        List<FinanceCategoryEntity> all = service.findAll();
        modelAndView.addObject("categories", all);
        return modelAndView;
    }

    @PostMapping("/saveCategory")
    public ModelAndView saveCategory (@ModelAttribute(name="catdto") FinanceCategoryDto dto, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        service.save(dto);
        modelAndView.addObject("categories", service.findAll());
        return modelAndView;
    }

    @PostMapping("/deleteCategory")
    public ModelAndView deleteCategory (@RequestParam(name = "id", required = true)  Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        service.delete(id);
        modelAndView.addObject("categories", service.findAll());
        return modelAndView;
    }
}
