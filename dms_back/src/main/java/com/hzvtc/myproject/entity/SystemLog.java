package com.hzvtc.myproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;



import java.util.Date;

/**
 * @author 
 * @date 2023-06-29
 */
@Data
@Accessors(chain = true)
public class SystemLog {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    private Long userId;

    private String clas;

    private String method;

    private String ip;

    private String param;

    private String url;

    private String result;

    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTimeEnd;

    private SystemUser systemUser;
}
