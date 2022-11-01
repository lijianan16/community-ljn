package com.ljn.communityljn.dao;

import com.ljn.communityljn.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author li
 * @Date 10/29/22 4:36 PM
 * @Version 1.0
 * 描述 ：
 * 名称：UserMapper
 */
@Mapper
public interface UserMapper {

    List<User> selectAllUser();

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

    int updateGender(int id, int gender);

    int updateCity(int id, String city);

    int updateSignature(int id, String signature);

    int updateReward(int id, int reward);

    int deleteById(int id);

}
