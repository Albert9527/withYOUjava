package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zd1024.withyou.dao.DyMapper;
import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.service.DyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DyServiceImpl implements DyService {

    @Autowired
    private DyMapper dyMapper;

    @Override
    public List<Dynamic> getMyFollowDynamic(String userid) {
        return dyMapper.selectMyfollowDy(userid);
    }

    @Override
    public List<Dynamic> getMyDynamic(String userid) {
        QueryWrapper<Dynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userid)
                    .orderByDesc("dy_create_time");
        return dyMapper.selectList(queryWrapper);
    }

    @Override
    public int addDynamic(Dynamic dynamic) {
       return dyMapper.insert(dynamic);
    }

    @Override
    public int deleteDyByID(String dynamicId) {
        return dyMapper.deleteById(dynamicId);
    }
}
