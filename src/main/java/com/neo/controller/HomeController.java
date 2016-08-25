package com.neo.controller;

/**
 * Created by amit on 9/8/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {


    @RequestMapping(value = "/")
    public String welcome(Map<String, Object> model) {
        return "index";
    }



}
