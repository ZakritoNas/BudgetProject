package com.example.budgetproject.web;

import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceDto;
import com.example.budgetproject.dto.expenses.FinanceSearchDto;
import com.example.budgetproject.service.FinanceCategoryService;
import com.example.budgetproject.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping()
public class FinanceController {

    private final FinanceService service;
    private final FinanceCategoryService categoryService;

    @GetMapping("/user")
    public ModelAndView homePage(@ModelAttribute(name="fin") FinanceEntity finance,
                                 @ModelAttribute(name="searchDto") FinanceSearchDto dto){
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("finances", service.findAll());
//        modelAndView.addObject("search", service.search(dto));
        modelAndView.addObject("sum", service.sum());
        modelAndView.addObject("sumOfGain", service.sumOfGain());
        modelAndView.addObject("sumOfExpenses", service.sumOfExpenses());
        return modelAndView;
}

    @GetMapping("/findAll")
    public ModelAndView findAll (@ModelAttribute(name="fin") FinanceEntity entity){
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        List<FinanceEntity> all = service.findAll();
        modelAndView.addObject("finances", all);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save (@ModelAttribute(name="findto") FinanceDto dto, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        modelAndView.addObject("categories", categoryService.findAll());
        service.save(dto);
        modelAndView.addObject("finances", service.findAll());
        return modelAndView;
    }

    @GetMapping("/sum")
    public ModelAndView sum (){
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        modelAndView.addObject("sum", service.sum());
        return modelAndView;
    }

    @GetMapping("/sumOfGain")
    public ModelAndView sumOfGain (){
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        modelAndView.addObject("sumOfGain", service.sumOfGain());
        return modelAndView;
    }

    @GetMapping("/sumOfExpenses")
    public ModelAndView sumOfExpenses (){
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        modelAndView.addObject("sumOfExpenses", service.sumOfExpenses());
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search (FinanceSearchDto dto){
        List<FinanceEntity> result = service.search(dto);
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("search", result);
        modelAndView.addObject("exp", new FinanceEntity());
        modelAndView.addObject("searchDto", new FinanceSearchDto());
        modelAndView.addObject("sum", service.sum());
        modelAndView.addObject("sumOfGain", service.sumOfGain());
        modelAndView.addObject("sumOfExpenses", service.sumOfExpenses());
        return modelAndView;
    }
}
