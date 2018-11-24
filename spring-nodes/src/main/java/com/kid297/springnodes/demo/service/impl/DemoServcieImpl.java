package com.kid297.springnodes.demo.service.impl;

import com.kid297.springnodes.demo.service.DemoServcie;
import com.kid297.springnodes.mvcframework.annotation.KidService;

@KidService
public class DemoServcieImpl implements DemoServcie {

    @Override
    public String showDoc(String value) {
        return "this is a "+ value;
    }

}
