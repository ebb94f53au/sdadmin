package com.sd.modules.security.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-13 15:59
 */
@Data
@AllArgsConstructor
public class JwtUser {

    private final Long id;

    private final String username;

    private final String nickName;

    private final String sex;

    private final String avatar;

    private final String email;

    private final String phone;

    private final String dept;

    private final String job;

    private final Set<String> roles;

    private final boolean enabled;

    private Long createTime;


}
