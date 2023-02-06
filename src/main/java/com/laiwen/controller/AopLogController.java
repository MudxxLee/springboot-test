package com.laiwen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laiwen.spring.aop.AnnotationLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laiw
 * @date 2022/9/30 10:39
 */
@RestController
@RequestMapping("/api/aop/log")
public class AopLogController {

    @PostMapping("test")
    @AnnotationLog("日志切入测试")
    @ResponseBody
    public Object test(String jsonStr, @JSONField(serialize = false) MultipartFile file) {
        String s = "Hello World!";
        return JSON.toJSONString(s);
    }

    @GetMapping("get")
    @AnnotationLog("获取真实IP")
    @ResponseBody
    public Object getIp(HttpServletRequest request) {
        return JSON.toJSONString(getIpAdrress(request), SerializerFeature.WriteMapNullValue);
    }

    /**
     * 获取请求Ip地址
     * @param request
     * @return
     */
    public static Map<String, String> getIpAdrress(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        //X-Real-IP，一般只记录真实发出请求的客户端IP
        String xRealIp = request.getHeader("X-Real-IP");
        //x_forwarded_for:  【用户经过代理时，代理会增加这个字段，nginx可用内置变量$http_x_forwarded_for取到这个字段，没有使用代理时，此字段为空】
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        map.put("XRealIp", xRealIp);
        map.put("XForwardedFor", xForwardedFor);
        if(StringUtils.isNotEmpty(xForwardedFor) && !"unKnown".equalsIgnoreCase(xForwardedFor)){
            //多次反向代理后会出现多个ip值，但只有第一个ip才是真实ip。所以只拿第一个就好
            int index = xForwardedFor.indexOf(",");
            if(index != -1){
                map.put("ip", xForwardedFor.substring(0,index));
                return map;
            }else{
                map.put("ip", xForwardedFor);
                return map;
            }
        }
        xForwardedFor = xRealIp;
        if(StringUtils.isNotEmpty(xForwardedFor) && !"unKnown".equalsIgnoreCase(xForwardedFor)){
            map.put("ip", xForwardedFor);
            return map;
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getRemoteAddr();
        }
        map.put("ip", xForwardedFor);
        return map;
    }

}
