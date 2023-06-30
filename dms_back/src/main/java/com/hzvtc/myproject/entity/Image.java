package com.hzvtc.myproject.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * @author XiongXinxin
 * @date 2023-03-19
 */
@Data
@Accessors(chain = true)
public class Image {
    private Long id;

    private String saveName;

    private String originalName;

    private String md5;

    private Date uploadTime;

    private Long uploadUser;
}
