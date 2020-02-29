package com.mirai.indidea.utils;

import com.mirai.indidea.dto.Result.ResultDto;

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
}
