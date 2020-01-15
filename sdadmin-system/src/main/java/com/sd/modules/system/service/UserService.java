package com.sd.modules.system.service;

import com.sd.exception.BadRequestException;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.mapper.UserMapper;
import com.sd.modules.system.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author siyang
 * @create 2020-01-12 19:48
 */

public interface UserService {

    public UserDto getUserByUsername(String username) throws BadRequestException;
}
