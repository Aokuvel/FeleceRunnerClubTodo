package com.ozdemir.ogrenci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    //@ResponseBody
    @RequestMapping("/accessdenied")
    public String access(Model model)
    {
        return "access-denied";
    }
}
