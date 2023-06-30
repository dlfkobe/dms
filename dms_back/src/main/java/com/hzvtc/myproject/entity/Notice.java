package com.hzvtc.myproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;
import java.util.List;

/**
 * @author 
 * @date 2023-02-04
 */
@Data
public class Notice {
    private Long id;

    private String msg;

    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private Boolean isRead;

    private SystemUser user;

    private List<Notice> receiveUsers;
}
