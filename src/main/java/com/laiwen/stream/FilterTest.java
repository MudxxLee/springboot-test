package com.laiwen.stream;

import com.laiwen.stream.entity.AEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laiw
 * @date 2022/9/26 16:35
 */
public class FilterTest {

    @Test
    public void test() {
        List<AEntity> list = new ArrayList<>();
        AEntity e = new AEntity();
        e.setId("1");
        e.setType(1);
        list.add(e);
        AEntity e1 = new AEntity();
        e1.setId("2");
        e1.setType(2);
        list.add(e1);
        AEntity e2 = new AEntity();
        e2.setId("3");
        e2.setType(3);
        list.add(e2);

        List<AEntity> result = list.stream().filter(item -> {
            return item.getType() != null && item.getType().equals(1);
        }).collect(Collectors.toList());

        System.out.println(result.size());

    }


}
