package com.laiwen.stream.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author laiw
 * @date 2022/9/26 16:40
 */
@Getter
@Setter
public class BEntity implements Serializable {

    private String id;
    private Integer type;

    public BEntity(String id, Integer type) {
        this.id = id;
        this.type = type;
    }

}
