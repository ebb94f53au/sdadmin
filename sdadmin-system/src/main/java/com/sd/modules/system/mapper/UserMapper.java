package com.sd.modules.system.mapper;

import com.sd.modules.system.domain.User;
import com.sd.modules.system.domain.UserExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名得到用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户名得到所有角色Id
     * @param username
     * @return
     */
    Set<Long> getRoleIdsByUsername(String username);
}