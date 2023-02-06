package com.laiwen.controller;

import com.laiwen.spring.transaction.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiw
 * @date 2022/9/30 15:35
 */
@RestController
@RequestMapping("/api/tt")
public class TransactionController {

    @Autowired
    private A a;

    @GetMapping("/a")
    @ResponseBody
    public Object test() {
        a.aVoid();
        return null;
    }

}
