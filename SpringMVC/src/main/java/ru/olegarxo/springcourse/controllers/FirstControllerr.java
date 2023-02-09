package ru.olegarxo.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/first")
public class FirstControllerr {
    @GetMapping("/hello")
    public String hellowPage(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "secondName", required = false) String secondName, Model model){
        model.addAttribute("fullName", name + "   " + secondName);
        return "first/hello";
    }
    @GetMapping("/goodbey")
    public String goodbeyPage(){
        return "first/goodbey";
    }
    @GetMapping("/calcul")
    public String calculPage(HttpServletRequest httpServletRequest, Model model){
        try {
            double a = Double.parseDouble(httpServletRequest.getParameter("a"));
            double b = Double.parseDouble(httpServletRequest.getParameter("b"));
            String action = httpServletRequest.getParameter("action");
            switch (action){
                case "multiplication" -> model.addAttribute("calc", a * b);
                case "division" -> model.addAttribute("calc", a / b);
                case "subtraction" -> model.addAttribute("calc", a - b);
                case "addition" -> model.addAttribute("calc", a + b);
            }
        }catch (Exception e){
            model.addAttribute("calc", "bad req");
        }

        return "first/calcul";
    }
}
