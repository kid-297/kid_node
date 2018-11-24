package com.kid297.springnodes.demo.controller;

import com.kid297.springnodes.demo.service.DemoServcie;
import com.kid297.springnodes.mvcframework.annotation.KidAutowired;
import com.kid297.springnodes.mvcframework.annotation.KidController;
import com.kid297.springnodes.mvcframework.annotation.KidRequestMapping;
import com.kid297.springnodes.mvcframework.annotation.KidRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@KidController
@KidRequestMapping("/demo")
public class DemoController {

    @KidAutowired
    private DemoServcie demoServcie;

    @KidRequestMapping("/showDoc")
    public void showDoc(HttpServletRequest request, HttpServletResponse response, @KidRequestParam("value")String value){
        String txt = demoServcie.showDoc(value);
        try {
            response.getWriter().write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
