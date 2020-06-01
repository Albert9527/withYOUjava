package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.MySHA512;
import com.zd1024.withyou.dao.DyMapper;
import com.zd1024.withyou.dao.FollowMapper;
import com.zd1024.withyou.dao.UserMapper;
import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.DyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class DyServiceImpl implements DyService {

    @Autowired
    private DyMapper dyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    private MySHA512 mySHA512 = new MySHA512();

    @Override
    public ObjVo serach(Integer current, Integer size, String keyWord, String userid) {
        IPage<Dynamic> page = new Page<>(current, size);
        QueryWrapper<Dynamic> queryWrapper = new QueryWrapper<>();
        if (followMapper.getMyFollowId(userid).size() != 0) {
            queryWrapper.or(qw -> qw.in("user_id", followMapper.getMyFollowId(userid))
                    .eq("user_id", userid))
                    .like("dy_content", keyWord);
        }else {
            queryWrapper.eq("user_id", userid)
                    .like("dy_content", keyWord);
        }
        dyMapper.selectPage(page, queryWrapper);
        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public List<Dynamic> getMyFollowDynamic(String userid) {
        return dyMapper.selectMyfollowDy(userid);
    }

    @Override
    public List<Dynamic> getMyDynamic(String userid) {
        QueryWrapper<Dynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userid)
                .orderByDesc("dy_create_time");
        return dyMapper.selectList(queryWrapper);
    }

    @Override
    public int addDynamic(Dynamic dynamic) {
        dynamic.setDyId(mySHA512.getId("dynamic"));
        dynamic.setDyCreateTime(new Date(System.currentTimeMillis()));
        return dyMapper.insert(dynamic);
    }

    @Override
    public int deleteDyByID(String dynamicId) {
        return dyMapper.deleteById(dynamicId);
    }
}
