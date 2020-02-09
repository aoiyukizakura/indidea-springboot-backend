package com.mirai.indidea.utils;

import com.mirai.indidea.dto.Result.ResultDto;

public class ResultUtil {
    public static ResultDto<Object> Result(int code, String msg, Object data) {
        ResultDto<Object> resultDto = new ResultDto<>();
        resultDto.setCode(code);
        resultDto.setMsg(msg);
        resultDto.setData(data);
        return resultDto;
    }
}
