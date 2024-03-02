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

import java.util.List;
@RequiredArgsConstructor

@Controller
@RequestMapping()
public class GainController {

    private final FinanceService service;
    private final FinanceCategoryService categoryService;

    @GetMapping("/gain")
    public ModelAndView gainPage(@ModelAttribute(name="gain") FinanceEntity finance,
                                 @ModelAttribute(name="searchDto") FinanceSearchDto dto){
        ModelAndView modelAndView = new ModelAndView("gain");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("gains", service.findAll());
        modelAndView.addObject("sumOfAllGains", service.sumOfGain());
        return modelAndView;
    }

    @GetMapping("/findAllGains")
    public ModelAndView findAll (@ModelAttribute(name="gain") FinanceEntity finance){
        ModelAndView modelAndView = new ModelAndView("redirect:/gain");
        List<FinanceEntity> all = service.findAll();
        modelAndView.addObject("gains", all);
        return modelAndView;
    }

    @PostMapping("/saveGain")
    public ModelAndView saveGain (@ModelAttribute(name="gaindto") FinanceDto dto, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/gain");
        modelAndView.addObject("categories", categoryService.findAll());
        if (dto.getAmount() > 0) {
            service.save(dto);
        }
        modelAndView.addObject("gains", service.findAll());
        return modelAndView;
    }

    @PostMapping("/deleteGain")
    public ModelAndView deleteGain (@RequestParam(name = "id", required = true) Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/gain");
        service.delete(id);
        List<FinanceEntity> all = service.findAll();
        modelAndView.addObject("gains", all);
        return modelAndView;
    }

    @GetMapping("/sumOfAllGains")
    public ModelAndView sumOfAllGains (){
        ModelAndView modelAndView = new ModelAndView("redirect:/gain");
        modelAndView.addObject("sumOfAllGains", service.sumOfGain());
        return modelAndView;
    }

    @PostMapping("/searchGains")
    public ModelAndView searchGains (FinanceSearchDto dto){
        List<FinanceEntity> result = service.search(dto);
        ModelAndView modelAndView = new ModelAndView("gain");
        modelAndView.addObject("search", result);
        modelAndView.addObject("exp", new FinanceEntity());
        modelAndView.addObject("searchDto", new FinanceSearchDto());
        modelAndView.addObject("sum", service.sum());
        modelAndView.addObject("sumOfGains", service.sumOfExpenses());
        return modelAndView;
    }
}
