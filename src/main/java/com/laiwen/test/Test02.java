package com.laiwen.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laiwen.stream.entity.BEntity;

/**
 * @author laiw
 * @date 2022/9/28 13:33
 */
public class Test02 {

    public static void main(String[] args) {

        BEntity aEntity = new BEntity("22222", 213123);

        String jsonString = JSONObject.toJSONString(aEntity, SerializerFeature.IgnoreErrorGetter);
        System.out.println(jsonString);

    }

}
