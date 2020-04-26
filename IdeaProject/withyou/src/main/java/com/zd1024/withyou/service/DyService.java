package com.zd1024.withyou.service;

import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DyService {

    //获取关注的人的动态
    List<Dynamic> getMyFollowDynamic(String userid);

    //获取当前用户的所有动态
    List<Dynamic> getMyDynamic(String userid);

    int addDynamic(Dynamic dynamic);

    int deleteDyByID(String dynamicId);
}
