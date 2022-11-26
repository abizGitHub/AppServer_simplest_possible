package com.abiz.controller;

import com.abiz.header.HttpMethod;
import net.HttpMapping;

import java.util.concurrent.atomic.AtomicInteger;

public class SampleController {

    AtomicInteger i = new AtomicInteger();

    @HttpMapping(method = HttpMethod.POST)
    public String addPerson(String req) {
        String s = "Received !" + req;
        System.out.println(s);
        return s;
    }

    @HttpMapping(method = HttpMethod.GET)
    public String allPerson() {
        String s = "person list";
        System.out.println(s);
        return s;
    }

    @HttpMapping(method = HttpMethod.GET)
    public String getPerson(String code) {
        String s = i.getAndIncrement() + "-person by code: " + code;
        System.out.println(s);
        return s;
    }

}
