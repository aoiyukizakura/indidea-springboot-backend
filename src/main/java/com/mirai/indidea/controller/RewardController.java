package com.mirai.indidea.controller;

import com.mirai.indidea.annotation.UserLoginToken;
import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.Reward;
import com.mirai.indidea.service.RewardService;
import com.mirai.indidea.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    RewardService rewardService;

    @GetMapping("/rewardListByProjectId")
    public ResultDto<Object> rewardList(@RequestParam("projectId") int projectId) {
        try {
            return ResultUtils.success(rewardService.findRewardsByProjectId(projectId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail();
        }
    }

    @UserLoginToken
    @PostMapping("/saveReward")
    public ResultDto<Object> saveReward(@RequestBody Reward reward) {
        try {
            return ResultUtils.success(rewardService.saveReward(reward));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail();
        }
    }

    @UserLoginToken
    @DeleteMapping("/deleteReward")
    public ResultDto<Object> deleteReward(@RequestParam("rewardId") int rewardId) {
        try {
            return ResultUtils.success(rewardService.deleteReward(rewardId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail();
        }
    }

    @UserLoginToken
    @GetMapping("/{id}")
    public ResultDto<Object> rewardDetail(@PathVariable int id) {
        try {
            return ResultUtils.success(rewardService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail();
        }
    }
}
