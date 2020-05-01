package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.MySHA512;
import com.zd1024.withyou.dao.AcountMapper;
import com.zd1024.withyou.entity.Acount;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.dao.UserMapper;
import com.zd1024.withyou.entity.User;
import com.zd1024.withyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private MySHA512 mySHA512 = new MySHA512();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AcountMapper acountMapper;

    @Override
    public String SigUp(String username, String password) {
        Acount acount = new Acount();
        acount.setUsername(username);
        acount.setPassword(password);
        acount.setRole(-1);
        acount.setUserId(mySHA512.getId("acount"));
        int rs = acountMapper.insert(acount);
        if (rs == 1)
            return acount.getUserId();
        else
            return rs + "";
    }

    @Override
    public int insertUser(String userid, String username) {
        User user = new User();
        user.setUserId(userid);
        user.setId(mySHA512.getId("user"));
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setNickname(username);
        return userMapper.insert(user);
    }

    @Override
    public int UpdateUserInfo(String userId, String clumName, String info) {
        switch (clumName) {
            case "nickname":
                return userMapper.updateNickname(userId, info);
            case "intro":
                return userMapper.updateIntro(userId, info);
            case "location":
                return userMapper.updateLocation(userId, info);
            default:
                return -1;
        }
    }

    @Override
    public int updateAvatar(String userId, String picname) {
        return userMapper.updateAvatar(userId, picname);
    }

    @Override
    public ObjVo getUser(Integer current, Integer size) {
        ObjVo<User> userVo = new ObjVo<>();
        IPage<User> page = new Page<>(current, size);

        userMapper.selectPage(page, null);
        userVo.setCurrent(current);
        userVo.setSize(size);
        userVo.setTotal(page.getTotal());
        userVo.setDatalist(page.getRecords());
        userVo.setPages(page.getPages());
        return userVo;
    }

    @Override
    public Acount checkAcount(String username, String password) {
        QueryWrapper<Acount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", username)
                .eq("password", password);
        return acountMapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserinfo(String userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userid);
        return userMapper.selectOne(queryWrapper);
    }

    /*   private Acount selectOne(String username,String password){
        QueryWrapper<Acount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username",username)
                .eq("password",password);
        Acount acount= acountMapper.selectOne(queryWrapper);
        return acount;
    }*/

    @Override
    public ObjVo<User> searchUser(Integer current,Integer size,String keyWord) {
        IPage<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.like("nickname", keyWord)
                .or()
                .like("intro", keyWord);
        /*  String keyWordbuffer = "%" + keyWord + "%";*/
        userMapper.selectPage(page, queryWrapper);

        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public ObjVo<User> searchvolunteer(Integer current,Integer size,String keyWord) {
        IPage<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.or(wrapper->wrapper.like("nickname", keyWord).like("intro", keyWord))
                .inSql("user_id","select user_id from t_acount where role = 0");
        /*  String keyWordbuffer = "%" + keyWord + "%";*/
        userMapper.selectPage(page, queryWrapper);

        return DataDealUtil.PageDataDeal(page);
    }
}
