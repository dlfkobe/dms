package com.hzvtc.myproject.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 
 * @date 2023-01-19
 */
@Data
@Accessors(chain = true)
public class Building {
    private Long id;

    @NotBlank
    private String name;

    private Long parentId;

    private Integer studentNum;

    private Integer roomNum;

    private List<Building> children;
 }
