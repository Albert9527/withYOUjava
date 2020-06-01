package com.zd1024.withyou.service;

import com.zd1024.withyou.entityVo.ObjVo;
import org.springframework.stereotype.Service;

@Service
public interface RecommendService {

    ObjVo getAllRecommendByCtgy(Integer current, Integer size,String ctgy);

    int addNewRecommend(Object recommend, String ctgy);

    int deleteRecommendById(String recmId, String ctgy);

    ObjVo searchrcm(Integer current, Integer size, String keyWord, String ctgy);
}
