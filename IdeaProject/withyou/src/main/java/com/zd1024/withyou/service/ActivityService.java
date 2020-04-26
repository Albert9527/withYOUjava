package com.zd1024.withyou.service;

import com.zd1024.withyou.entity.ActApply;
import com.zd1024.withyou.entity.Activity;
import com.zd1024.withyou.entityVo.ObjVo;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface ActivityService {

    int addActivity(Activity activity);

    ObjVo<Activity> getActivityByState(Integer current,Integer size,int state);

    Activity getActivityById(String actId);

    int deleteActivityById(String actId);

    int examineActivity(String actId,int state);

    List<ActApply> getAllApplyByActId(Integer current,Integer size,String actId);
    
    boolean checkActCreateAndApply(Date date,String userid);

    int addActApply(ActApply actApply);

    List<ActApply> getActApplyByUserId(Integer current,Integer size,String userid);

    List<ActApply> getApplyByMenber(Integer current,Integer size,String userid);

    int checkActApply(ActApply actApply);
}
