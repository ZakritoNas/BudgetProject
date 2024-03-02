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
public class ExpensesController {

    private final FinanceService service;
    private final FinanceCategoryService categoryService;

    @GetMapping("/expense")
    public ModelAndView expensePage(@ModelAttribute(name="exp") FinanceEntity finance,
                                 @ModelAttribute(name="searchDto") FinanceSearchDto dto){
        ModelAndView modelAndView = new ModelAndView("expense");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("expenses", service.findAll());
        modelAndView.addObject("sumOfAllExpenses", service.sumOfExpenses());
        return modelAndView;
    }

    @GetMapping("/findAllExpenses")
    public ModelAndView findAll (@ModelAttribute(name="exp") FinanceEntity entity){
        ModelAndView modelAndView = new ModelAndView("redirect:/expense");
        List<FinanceEntity> all = service.findAll();
        modelAndView.addObject("expenses", all);
        return modelAndView;
    }

    @PostMapping("/saveExpenses")
    public ModelAndView saveExpenses (@ModelAttribute(name="expdto") FinanceDto dto, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("redirect:/expense");
        modelAndView.addObject("categories", categoryService.findAll());
        if (dto.getAmount() < 0) {
            service.save(dto);
        }
        modelAndView.addObject("expenses", service.findAll());
        return modelAndView;
    }

    @PostMapping("/deleteExpenses")
    public ModelAndView deleteExpenses (@RequestParam(name = "id", required = true) Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/expense");
        service.delete(id);
        List<FinanceEntity> all = service.findAll();
        modelAndView.addObject("expenses", all);
        return modelAndView;
    }


    @PostMapping("/update")
    public ModelAndView update(@RequestParam (name = "id", required = true) Integer id,
                               @RequestParam (name = "newAmount", required = false) Double amount){
        ModelAndView modelAndView = new ModelAndView("user");
        service.updateAmount(id, amount);
        List<FinanceEntity> all = service.findAll();
        modelAndView.addObject("expenses", all);
        return modelAndView;
    }

    @GetMapping("/sumOfAllExpenses")
    public ModelAndView sumOfAllExpenses (){
        ModelAndView modelAndView = new ModelAndView("redirect:/expense");
        modelAndView.addObject("sumOfExpenses", service.sumOfExpenses());
        return modelAndView;
    }

    @PostMapping("/searchExpenses")
    public ModelAndView searchExpenses (FinanceSearchDto dto){
        List<FinanceEntity> result = service.search(dto);
        ModelAndView modelAndView = new ModelAndView("expense");
        modelAndView.addObject("search", result);
        modelAndView.addObject("exp", new FinanceEntity());
        modelAndView.addObject("searchDto", new FinanceSearchDto());
        modelAndView.addObject("sum", service.sum());
        modelAndView.addObject("sumOfExpenses", service.sumOfExpenses());
        return modelAndView;
    }
}
