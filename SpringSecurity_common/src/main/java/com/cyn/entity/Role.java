package com.cyn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G0dc
 * @description: 角色类
 * @date 2022/7/1 13:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private Integer id;
    private String name;
    private String nameZh;
}
