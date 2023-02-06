package com.laiwen.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author laiw
 * @date 2022/9/30 15:35
 */
@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/get/{id}")
    @ResponseBody
    public Object get(@PathVariable("id") String id, Integer auto) {
        System.out.println(auto);
        return null;
    }

    @GetMapping("/doGet")
    @ResponseBody
    public void doGet(String id, String name, Integer age, HttpServletRequest request) {
        System.out.println("queryString=" + request.getQueryString());
        System.out.println("body=" + getBodyString(request));
        System.out.println("<"+getBodyString(request)+">");
    }

    @PostMapping("/doPost")
    @ResponseBody
    public void doPost(@RequestBody Map<String, Object> parameter, HttpServletRequest request) {
        System.out.println(parameter.toString());
        System.out.println(request.getQueryString());
        System.out.println("body=" + getBodyString(request));
    }

    public static void main(String[] args) {
        System.out.println(""+null+"11");
        String s = null;
        StringBuilder srt = new StringBuilder().append("").append(s).append("11");
        System.out.println(srt.toString());
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        ServletInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
