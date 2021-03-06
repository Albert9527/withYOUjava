package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.DateUtil;
import com.zd1024.withyou.Util.MySHA512;
import com.zd1024.withyou.dao.ActApplyMapper;
import com.zd1024.withyou.dao.ActivityMapper;
import com.zd1024.withyou.dao.MenberMapper;
import com.zd1024.withyou.dao.UserMapper;
import com.zd1024.withyou.entity.*;
import com.zd1024.withyou.entityVo.MenberVo;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private MySHA512 myactSHA512 = new MySHA512();

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActApplyMapper actApplyMapper;

    @Autowired
    private MenberMapper menberMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addActivity(Activity activity) {
        activity.setActId(myactSHA512.getId("act"));
        return activityMapper.insert(activity);
    }

    @Override
    public ObjVo<Activity> getActivityByState(Integer current, Integer size, int state) {
        ObjVo<Activity> actVo = new ObjVo<>();
        IPage<Activity> page = new Page<>(current, size);

        activityMapper.selectPage(page, new QueryWrapper<Activity>()
                .eq("act_audit_state", state));
        actVo.setCurrent(current);
        actVo.setSize(size);
        actVo.setTotal(page.getTotal());
        actVo.setDatalist(page.getRecords());
        actVo.setPages(page.getPages());
        return actVo;
    }

    /**
     * 根据id获取活动详情
     *
     * @param userid
     * @return
     */
    @Override
    public List getActivityByOption(String userid) {
        List<String> actIds = menberMapper.getActId(userid);
        if (actIds.size() != 0) {
            QueryWrapper<Activity> queryWrapper = new QueryWrapper();
            queryWrapper.in("act_id", menberMapper.getActId(userid));
            return activityMapper.selectList(queryWrapper);
        } else
            return null;
    }

    @Override
    public List getMyActByMenber(String userid) {
        List<String> actIds = menberMapper.getMenberActId(userid);
        if (actIds.size() != 0) {
            QueryWrapper<Activity> queryWrapper = new QueryWrapper();
            queryWrapper.in("act_id", actIds);
            return activityMapper.selectList(queryWrapper);
        } else
            return null;
    }

    @Override
    public Activity getActivityById(String actId) {
        return activityMapper.selectById(actId);
    }

    @Override
    public int deleteActivityById(String actId) {
        return activityMapper.deleteById(actId);
    }

    /**
     * 管理员审核活动
     *
     * @Param actId: 活动ID
     * @Param state: 审核结果 -1：未通过，1：通过
     * @Return: int
     * @Author: zhudi
     * @Date: 2020/4/25
     */
    @Override
    public int examineActivity(String actId, int state) {
        int stateRs = activityMapper.UpdateActivityState(actId, state);
        if (stateRs == 1 && state == 1) {
            String userid = activityMapper.getuUserIdByActId(actId);
            Menber menber = new Menber();
            menber.setMenberId(myactSHA512.getId("menber"));
            menber.setActId(actId);
            menber.setUserId(userid);
            menber.setMenberRole(1);
            return menberMapper.insert(menber);
        } else
            return stateRs;
    }

    /**
     * 根据活动id查询所有未审核申请
     *
     * @param current
     * @param size
     * @param actId
     * @return
     */
    @Override
    public List<ActApply> getAllApplyByActId(Integer current, Integer size, String actId) {
        IPage<ActApply> page = new Page<>(current, size);
        QueryWrapper<ActApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("act_id", actId)
                .eq("act_audit_state", 0)
                .orderByDesc("act_apply_time");
        actApplyMapper.selectPage(page, queryWrapper);
        return page.getRecords();
    }

    /**
     * 活动创建和申请认证，返回改用户参与活动的时间，
     * 若同一天已有活动，则拒绝创建和申请
     *
     * @param date
     * @param userid
     * @return
     */
    @Override
    public boolean checkActCreateAndApply(Date date, String userid) {
        List<Date> dates = actApplyMapper.getMyActivityDate(userid);
        return DateUtil.checkDate(date, dates);
    }

    @Override
    public int addActApply(ActApply actApply) {
        actApply.setActAuditState(0);
        actApply.setActApplyTime(new Date(System.currentTimeMillis()));
        actApply.setActApplyId(myactSHA512.getId("ActApply"));
        return actApplyMapper.insert(actApply);
    }

    /**
     * 根据用户id查询该用户的所有申请
     *
     * @Param current: 数据页码
     * @Param size: 每页数据数
     * @Param userid: 用户id
     * @Return: java.util.List<com.zd1024.withyou.entity.ActApply>
     * @Author: zhudi
     * @Date: 2020/4/25
     */
    @Override
    public List<MyApply> getActApplyByUserId(String userid) {
        return actApplyMapper.getMyapply(userid);
    }

    /**
     * 根据组织者用户id获取活动id，返回该活动的所有申请
     *
     * @Param userid: 组织者id
     * @Return: java.util.List<com.zd1024.withyou.entity.ActApply>
     * @Author: zhudi
     * @Date: 2020/4/25
     */
    @Override
    public List<ActApply> getApplyByMenber(Integer current, Integer size, String userid) {
        String actId = menberMapper.selectActId(userid);
        if (actId != null) {
            return getAllApplyByActId(current, size, actId);
        } else
            return null;
    }

    /**
     * 审核活动的用户申请，在ActivityController中被调用，由活动组织者执行
     *
     * @param actApply
     * @return
     */
    @Override
    public int checkActApply(ActApply actApply) {
        int state = actApply.getActAuditState();
        if (state == -1) {
            return actApplyMapper.updateApplyState(actApply.getActApplyId(), state);
        } else {
            if (actApplyMapper.updateApplyState(actApply.getActApplyId(), state) == 1) {

                Menber menber = new Menber();
                menber.setUserId(actApply.getUserId());
                menber.setActId(actApply.getActId());
                menber.setMenberId(myactSHA512.getId("menber"));
                menber.setMenberRole(0);
                return menberMapper.insert(menber);
            } else
                return -1;
        }
    }


    @Override
    public List<MenberVo> getMenberByActId(String actId) {
        return menberMapper.queryMenberInfoByActId(actId);
    }

    @Override
    public ObjVo<Activity> searchActivity(Integer current, Integer size, String keyWord) {
        IPage<Activity> page = new Page<>(current, size);

        QueryWrapper<Activity> queryWrapper = new QueryWrapper();
        queryWrapper.like("act_title", keyWord)
                .or()
                .like("act_address", keyWord)
                .or()
                .like("act_intro", keyWord)
                .or()
                .like("act_tag", keyWord);
        activityMapper.selectPage(page, queryWrapper);

        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public ObjVo<Activity> searchActive(Integer current, Integer size, String keyWord, String ctgy, String userid) {

        IPage<Activity> actpage = new Page<>(current, size);
        QueryWrapper<Activity> qwact = new QueryWrapper();

        List<String> joinactIds = menberMapper.getMenberActId(userid);
        List<String> optionactIds = menberMapper.getActId(userid);

        switch (ctgy) {
            case "allact":
                qwact.or(qw -> qw.like("act_title", keyWord)
                                .like("act_address", keyWord)
                                .like("act_intro", keyWord)
                                .like("act_tag", keyWord))
                        .eq("act_audit_state", 1);
                activityMapper.selectPage(actpage, qwact);
                return DataDealUtil.PageDataDeal(actpage);

            case "optionact":
                if (optionactIds.size() != 0) {
                    qwact.or(qw -> qw.like("act_title", keyWord)
                            .like("act_address", keyWord)
                            .like("act_intro", keyWord)
                            .like("act_tag", keyWord))
                            .eq("act_audit_state", 1)
                            .in("user_id", optionactIds);
                    activityMapper.selectPage(actpage, qwact);
                }
                return DataDealUtil.PageDataDeal(actpage);

            case "joinact":
                if (joinactIds.size() != 0) {
                    qwact.or(qw -> qw.like("act_title", keyWord)
                            .like("act_address", keyWord)
                            .like("act_intro", keyWord)
                            .like("act_tag", keyWord))
                            .eq("act_audit_state", 1)
                            .in("user_id", joinactIds);
                    activityMapper.selectPage(actpage, qwact);
                }
                return DataDealUtil.PageDataDeal(actpage);
            default:
                return null;
        }
    }

    @Override
    public ObjVo serach(Integer current, Integer size, String keyWord, String ctgy, String userid) {

        switch (ctgy) {
            case "apply":
                IPage<ActApply> applypage = new Page<>(current, size);

                QueryWrapper<ActApply> qwapply = new QueryWrapper();
                qwapply.like("act_apply_reason", keyWord)
                        .or()
                        .like("act_audit_state", keyWord)
                        .or()
                        .in("user_id", userMapper.selectLikeName("%" + keyWord + "%"));
                actApplyMapper.selectPage(applypage, qwapply);
                return DataDealUtil.PageDataDeal(applypage);

            case "menber":
                IPage<Menber> menberpage = new Page<>(current, size);

                QueryWrapper<Menber> qwmenber = new QueryWrapper();
                qwmenber.in("user_id", userMapper.selectLikeName("%" + keyWord + "%"));
                menberMapper.selectPage(menberpage, qwmenber);
                return DataDealUtil.PageDataDeal(menberpage);
            default:
                return null;
        }
    }
}
