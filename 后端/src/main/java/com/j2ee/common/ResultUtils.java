package com.j2ee.common;

import com.j2ee.status.StatusCode;

public class ResultUtils {
    /*
     * 返回带参
     * */
    public static ResultVo vo(int code, String msg, Object data) {
        return new ResultVo(code, msg, data);
    }
    /*
    返回无参数
    * */
    public static ResultVo success() {
        return new ResultVo(StatusCode.SUCCESS_CODE, null, null);
    }
    //一个参数
    public static ResultVo success(String msg) {
        return new ResultVo(StatusCode.SUCCESS_CODE, msg, null);
    }
    //全部参数
    public static ResultVo success(int code, String msg, Object data) {
        return new ResultVo(code, msg, data);
    }
    //提示信息和数据
    public static ResultVo success(String msg, Object data) {
        return new ResultVo(StatusCode.SUCCESS_CODE, msg, data);
    }
    //返回失败信息
    public static ResultVo error() {
        return new ResultVo(StatusCode.ERROR_CODE, null, null);
    }
    //一个参数
    public static ResultVo error(String msg) {
        return new ResultVo(StatusCode.ERROR_CODE, msg, null);
    }
    //全部参数
    public static ResultVo error(int code, String msg, Object data) {
        return new ResultVo(code, msg, data);
    }
    //提示信息和数据
    public static ResultVo error(String msg, Object data) {
        return new ResultVo(StatusCode.ERROR_CODE, msg, data);
    }
    //返回带分页
    public static ResultPageVo success(String msg, int pageNum, int pageSize, int total, Object data) {
        return new ResultPageVo(msg, StatusCode.SUCCESS_CODE, pageNum, pageSize, total, data);
    }
}
