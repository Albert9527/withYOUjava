package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.PictrueDealUtil;
import com.zd1024.withyou.entity.*;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.service.ActivityService;
import com.zd1024.withyou.service.FriendService;
import com.zd1024.withyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    private AndroidData data;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private ActivityService activityService;

    /**
     * 安卓端注册接口
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/sigup")
    public AndroidData SigUp(String username, String password) {
        boolean success = false;
        String msg = "";
        if (userService.checkAcount(username, password) == null) {
            String result = userService.SigUp(username, password);
            switch (result) {
                case "-1":
                    success = false;
                    msg = "注册失败：系统数据错误";
                    break;
                default:
                    int rs = userService.insertUser(result, username);
                    if (rs == 1) {
                        success = true;
                        msg = "注册成功";
                    } else {
                        success = false;
                        msg = "注册失败，更新用户数据错误";
                    }
                    break;
            }
        } else {
            success = false;
            msg = "注册失败：该账号已被注册";
        }
        data = new AndroidData<>();
        data.setMsg(msg);
        data.setSuccess(success);
       /* JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", success);
        jsonObject.put("Msg", msg);
        String resultVlue = jsonObject.toJSONString();*/
        return data;
    }

    @PostMapping("/login")
    public AndroidData login(String username, String password) {
        data = new AndroidData<User>();
        User user;
        Acount acount = userService.checkAcount(username, password);
        if (acount != null) {
            user = userService.getUserinfo(acount.getUserId());
            data.setSuccess(true);
            data.setMsg("登陆成功");
            data.setData(user);
        } else {
            data.setSuccess(false);
            data.setMsg("账号未注册");
        }
        return data;
    }

    @GetMapping("/getinfo")
    public AndroidData getUserInfoById(String userid) {
        data = new AndroidData<User>();
        User user = userService.getUserinfo(userid);
        if (user != null) {
            data.setSuccess(true);
            data.setData(user);
        } else {
            data.setSuccess(false);
            data.setMsg("数据获取失败");
        }
        return data;
    }

    /**
     * 获取我的关注
     *
     * @param userid
     * @return
     */
    @GetMapping("/MyFollow")
    public AndroidData<Friend> getMyFollow(String userid) {
        List<Friend> friends = friendService.getMyFollow(userid);

        data = DataDealUtil.dealdata(friends);
        return data;
    }

    /**
     * 获取我的粉丝
     *
     * @param userid
     * @return
     */
    @GetMapping("/FollowMe")
    public AndroidData<Friend> getFollowMe(String userid) {
        List<Friend> friends = friendService.getFollowMe(userid);

        data = DataDealUtil.dealdata(friends);
        return data;
    }

    /**
     * 新增关注
     *
     * @param userId   关注者id
     * @param targetId 关注对象id
     * @return
     */

    @GetMapping("/addFollow")
    public AndroidData addFollow(String userId, String targetId) {
        int rs = friendService.addFollow(userId, targetId);
        return DataDealUtil.dealdata(rs, "关注");
    }

    @GetMapping("/cancelFollow")
    public AndroidData cancelFollow(String userId, String targetId) {
        int rs = friendService.cancelFollow(userId, targetId);
        return DataDealUtil.dealdata(rs, "取消关注");
    }

    /**
     * 活动申请
     *
     * @param actApply
     * @return
     */
    @PostMapping("/applyAct")
    public AndroidData ApplyActivity(ActApply actApply) {
        data = new AndroidData();
        Activity activity = activityService.getActivityById(actApply.getActId());
        boolean checked = activityService.checkActCreateAndApply(
                activity.getActStartTime(), actApply.getUserId());
        if (checked) {
            int rs = activityService.addActApply(actApply);
            data = DataDealUtil.dealdata(rs, "活动申请");
        } else {
            data.setSuccess(false);
            data.setMsg("该日期下您已参与一个活动");
        }
        return data;
    }

    /**
     * 根据用户id获取该用户所有的申请
     * @param current
     * @param userId
     * @return
     */
    @GetMapping("/MyApply")
    public AndroidData getMyAllApply(Integer current,String userId){
        List<ActApply> applies = activityService
                .getActApplyByUserId(current,10,userId);
        return DataDealUtil.dealdata(applies);
    }

    /**
     * 修改用户信息，
     *
     * @Param userId: 用户id
     * @Param clumName: 获取对应的列名
     * @Param info: 新的用户信息
     * @Return: com.zd1024.withyou.entityVo.AndroidData
     * @Author: zhudi
     * @Date: 2020/4/25
     */
    @PostMapping("/update/info")
    public AndroidData UpdateUserInfo(String userId, String clumName, String info) {
        int rs = userService.UpdateUserInfo(userId, clumName, info);
        if (rs == 1) {
            return DataDealUtil.dealbeanData(info, "信息修改");
        } else {
            return DataDealUtil.dealbeanData(null, "信息修改");
        }
    }

    /**
     * 修改用户头像
     *
     * @param avatar 根据data.key 获取对应的列名，传入data.value修改
     * @return
     */
    @PostMapping("/update/avatar")
    public AndroidData UpdateUseravatar(String userId, MultipartFile avatar) {
        String picname = PictrueDealUtil.savePictrue(avatar);
        if (picname!=null){
        int rs = userService.updateAvatar(userId, picname);
        if (rs == 1) {
            return DataDealUtil.dealbeanData(picname, "头像修改");
        } else {
            return DataDealUtil.dealbeanData(null, "头像修改");
        }
        }else {
            return DataDealUtil.dealbeanData(null,"头像上传");
        }
    }

}
