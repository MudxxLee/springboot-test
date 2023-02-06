package com.laiwen.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiw
 * @date 2022/9/30 15:35
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {


    @PostMapping("demo/update")
    @ResponseBody
    public Object update(String cron) {

        return null;

    }


}
