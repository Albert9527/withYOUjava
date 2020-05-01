package com.zd1024.withyou.service;

import com.zd1024.withyou.entity.Acount;
import com.zd1024.withyou.entity.User;
import com.zd1024.withyou.entityVo.ObjVo;

public interface UserService {
    ObjVo<User> getUser(Integer current, Integer size);

    User getUserinfo(String userid);

    Acount checkAcount(String username, String password);

    String SigUp(String username, String password);

    int insertUser(String userId, String username);


    int UpdateUserInfo(String userId, String clumName, String info);

    int updateAvatar(String userId, String picname);

    ObjVo<User> searchUser(Integer current,Integer size,String keyWord);

    ObjVo<User> searchvolunteer(Integer current,Integer size,String keyWord);
}
