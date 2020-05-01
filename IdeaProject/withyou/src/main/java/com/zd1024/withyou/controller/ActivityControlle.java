package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.PictrueDealUtil;
import com.zd1024.withyou.entity.Acount;
import com.zd1024.withyou.entity.ActApply;
import com.zd1024.withyou.entity.Activity;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/activity")
public class ActivityControlle {

    private AndroidData<Activity> data;

    @Autowired
    private ActivityService activityService;

    /**
     * 安卓端获取所有已审核通过的活动数据
     *
     * @Param current: 查询的数据页码
     * @Return: com.zd1024.withyou.entityVo.AndroidData<com.zd1024.withyou.entity.Activity>
     * @Author: zhudi
     * @Date: 2020/4/24
     */
    @GetMapping("/getAll")
    public AndroidData<Activity> getAllActivity(Integer current) {
        ObjVo<Activity> activityVo = activityService.getActivityByState(current, 10, 1);

        data = DataDealUtil.dealdata(activityVo.getDatalist());

        return data;
    }

    /**
     * 获取活动详细信息
     *
     * @param actId
     * @return
     */
    @GetMapping("/getActInfo")
    public AndroidData getActivityById(String actId) {
        Activity activity = activityService.getActivityById(actId);
        data = DataDealUtil.dealbeanData(activity, "活动信息查询");
        return data;
    }

    /**
     * 创建活动
     *
     * @param activity
     * @param pic
     * @return
     */
    @PostMapping("/add")
    public AndroidData addActivity(Activity activity, MultipartFile pic) {
        activity.setActPic(PictrueDealUtil.savePictrue(pic));
        boolean check = activityService.checkActCreateAndApply(activity.getActStartTime(), activity.getUserId());
        if (check) {
            int rs = activityService.addActivity(activity);
            data = DataDealUtil.dealdata(rs, "活动创建");
        } else {
            data.setSuccess(false);
            data.setMsg("该日期下您已参与一个活动");
        }
        return data;
    }

    /**
     * 根据活动id删除活动
     *
     * @param actId
     * @return
     */
    @GetMapping("/delete")
    public AndroidData deleteActivity(String actId) {
        int rs = activityService.deleteActivityById(actId);
        data = DataDealUtil.dealdata(rs, "活动删除");
        return data;
    }

    /**
     * 获取活动ID活动申请
     *
     * @param current
     * @param userId
     * @return
     */
    @GetMapping("/getApply")
    public AndroidData<ActApply> getAllApplyByActId(Integer current, String userId) {
        List<ActApply> applies = activityService.getApplyByMenber(
                current, 10, userId);
        AndroidData<ActApply> actData = DataDealUtil.dealdata(applies);
        return actData;
    }

    /**
     * 活动申请审核，由活动创建者执行
     * @param actApply
     * @return
     */
    @PostMapping("/checkApply")
    public AndroidData checkActApply(ActApply actApply) {
        int rs = activityService.checkActApply(actApply);
        return data = DataDealUtil.dealdata(rs, "申请审核");
    }

    @GetMapping("/getMenber")
    public AndroidData getMenberByActId(String actId){
        List dataList = activityService.getMenberByActId(actId);
        return DataDealUtil.dealdata(dataList);
    }
}
