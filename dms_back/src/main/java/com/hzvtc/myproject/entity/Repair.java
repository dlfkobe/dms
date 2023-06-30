package com.hzvtc.myproject.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

/**
 * @author XiongXinxin
 * @date 2023-03-13
 */
@Data
public class Repair {
    private Long id;

    @NotNull
    private Long roomId;

    @NotBlank
    private String describe;

    private List<String> picture;

    private Boolean status;

    private Date createDate;

    private Date finishDate;

    private Room room;
}
