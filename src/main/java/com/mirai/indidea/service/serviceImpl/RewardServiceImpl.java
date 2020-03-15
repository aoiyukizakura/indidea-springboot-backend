package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.RewardRepository;
import com.mirai.indidea.entity.Reward;
import com.mirai.indidea.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    RewardRepository rewardRepository;

    @Override
    public List<Reward> findRewardsByProjectId(int projectId) {
        try {
            return rewardRepository.findByProjectId(projectId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveReward(Reward reward) {
        try {
            Reward re = rewardRepository.saveAndFlush(reward);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteReward(int rewardId) {
        try {
            rewardRepository.deleteById(rewardId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reward findById(int id) {
        return rewardRepository.findById(id);
    }
}
