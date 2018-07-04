package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("toqueryOrganization")
    public String toqueryOrganization(){
        return "organization/showOrganization";
    }
}
