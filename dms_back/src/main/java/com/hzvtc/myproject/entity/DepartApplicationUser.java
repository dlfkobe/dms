package com.hzvtc.myproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;

/**
 * @author 
 * @date 2023-02-09
 */
@Data
public class DepartApplicationUser {

    private Long id;

    private Long operateUserId;

    private SystemUser operateUser;

    private Boolean isAgree;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date operateTime;

    private String reason;

    private Long applicationId;

    private DepartApplication application;
}
