package com.laiwen.stream.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author laiw
 * @date 2022/9/26 16:40
 */
@Data
@Getter
@Setter
public class AEntity implements Serializable {

    private String id;
    private Integer type;
}
