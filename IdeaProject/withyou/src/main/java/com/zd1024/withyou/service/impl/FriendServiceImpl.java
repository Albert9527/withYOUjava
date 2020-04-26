package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zd1024.withyou.Util.MySHA512;
import com.zd1024.withyou.dao.FollowMapper;
import com.zd1024.withyou.entity.Follow;
import com.zd1024.withyou.entity.Friend;
import com.zd1024.withyou.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    private MySHA512 mySHA512 = new MySHA512();

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<Friend> getMyFollow(String userid) {
        return followMapper.getMyFollow(userid);
    }

    @Override
    public List<Friend> getFollowMe(String userid) {
        return followMapper.getFollowMe(userid);
    }

    @Override
    public int addFollow(String userId, String targetId) {
        Follow follow = new Follow();
        Follow rsFollow = followMapper.selectOne(new QueryWrapper<Follow>()
                .eq("target_id", userId)
                .eq("user_id", targetId));
        if (rsFollow != null) {
            follow.setIstwoway(1);
        } else {
            follow.setIstwoway(0);
        }
        follow.setFollowId(mySHA512.getId("follow"));
        follow.setUserId(userId);
        follow.setTargetId(targetId);
        if (followMapper.insert(follow) == 1) {
            if (rsFollow!=null)
            return followMapper.updatetwoway(userId, 1);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int cancelFollow(String userId, String targetId) {
        Follow follow;
        follow = followMapper.selectOne(new QueryWrapper<Follow>()
                .eq("target_id", userId));
        if (followMapper.cancelFollow(userId, targetId) == 1) {
            if (follow != null)
                return followMapper.updatetwoway(userId, 0);
            else
                return 1;
        } else
            return -1;
    }
}
