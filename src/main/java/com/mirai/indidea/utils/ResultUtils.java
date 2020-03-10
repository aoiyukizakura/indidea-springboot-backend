package com.mirai.indidea.utils;

import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtils {
    public static ResultDto<Object> Result(int code, String msg, Object data) {
        ResultDto<Object> resultDto = new ResultDto<>();
        resultDto.setCode(code);
        resultDto.setMsg(msg);
        resultDto.setData(data);
        return resultDto;
    }

    public static ResultDto<Object> success(Object data) {
        return ResultUtils.Result(200,"success",data);
    }

    public static ResultDto<Object> fail() {
        return ResultUtils.Result(-1, "fail", false);
    }

    public static <T> ResultDto<Object> pager(List<T> list, int pageNum, int limit) {
        int total = list.size();
        PageUtils<T> pageUtils = new PageUtils<>(total,pageNum,limit);
        pageUtils.setList(list);
        Map<String, Object> map = new HashMap<>();
        if (total <= limit) {
            map.put("list", list);
            map.put("total", total);
        } else {
            if (pageNum > pageUtils.getPages()) {
                return ResultUtils.fail();
            } else if (pageNum == pageUtils.getPages()) {
                List<T> sub = list.subList((pageNum-1)*10, total-1);
                map.put("list", sub);
                map.put("total", total);
            } else {
                List<T> sub = list.subList((pageNum-1)*10, pageNum * 10);
                map.put("list", sub);
                map.put("total", total);
            }
        }
        return ResultUtils.success(map);
    }
}
