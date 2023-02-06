package com.laiwen.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author laiw
 * @date 2022/11/11 15:41
 */
public class Test03 {

    public static void main(String[] args) {

        String msgStr = "{\"success\":false,\"error\":{\"code\":\"config.deleted\",\"message\":\"The config with uuid: 93e5a4a1-1891-49cf-930a-ab403da0efab has been deleted\",\"args\":[\"93e5a4a1-1891-49cf-930a-ab403da0efab\"]}}";
        JSONObject msg = JSON.parseObject(msgStr);
        if(!msg.containsKey("error")) {
            System.out.println(1);
            return;
        }
        JSONObject error = msg.getJSONObject("error");
        if(!error.containsKey("code") || !error.getString("code").equals("config.deleted")) {
            System.out.println(2);
        }


    }
}
