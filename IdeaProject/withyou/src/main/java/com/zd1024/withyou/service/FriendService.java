package com.zd1024.withyou.service;

import com.zd1024.withyou.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {

    List<Friend> getMyFollow(String userid);

    List<Friend> getFollowMe(String userid);

    int addFollow(String userId, String targetId);

    int cancelFollow(String userId, String targetId);
}
