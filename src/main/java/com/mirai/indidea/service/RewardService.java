package com.mirai.indidea.service;

import com.mirai.indidea.entity.Reward;

import java.util.List;

public interface RewardService {
    List<Reward> findRewardsByProjectId(int projectId);
    boolean saveReward(Reward reward);
    boolean deleteReward(int rewardId);
    Reward findById(int id);
}
