package com.laiwen.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laiwen.stream.entity.AEntity;

/**
 * @author laiw
 * @date 2022/9/28 13:33
 */
public class Test01 {

    public static void main(String[] args) {

        //Assert.isNull(null, "ces");

        AEntity aEntity = new AEntity();
        aEntity.setId("1111");
        aEntity.setType(555245);

        String jsonString = JSONObject.toJSONString(aEntity, SerializerFeature.IgnoreErrorGetter);
        System.out.println(jsonString);

    }

}
