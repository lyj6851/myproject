package com.hhf.common.utils;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by sun.mahai
 * 请求响应结果的统一封装
 */
public class JsonUtil {

    private static final Boolean SUCCESS = true;
    private static final Boolean FAILURE = false;
    private static final String IS_SUCCESS = "isSuccess";
    private static final String ERROR_MESSAGE = "errorMessage";

    /**
     * 请求成功
     *
     * @param resultData
     * @return
     */
    public static JSONObject success(Object resultData) {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put("data", resultData);
        resultJSON.put(IS_SUCCESS, SUCCESS);

//        return resultJSON.toJSONString();
        return resultJSON;
    }

    /**
     * 请求成功  无返回数据
     *
     * @return
     */
    public static JSONObject success() {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put("data", "");
        resultJSON.put(IS_SUCCESS, SUCCESS);

        return resultJSON;
    }

    /**
     * 请求失败
     *
     * @param errorMessage
     * @return
     */
    public static JSONObject failure(String message,String errorMessage) {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put(IS_SUCCESS, FAILURE);
        resultJSON.put("MESSAGE", message);
        resultJSON.put(ERROR_MESSAGE, errorMessage);

        return resultJSON;

    }

    public static void main(String[] args) {

        System.out.println("成功信息:" + JsonUtil.success("响应结果,数据"));
        System.out.println("成功信息:" + JsonUtil.success());
        System.out.println("错误信息:" + JsonUtil.failure("请求失败","报错信息"));

    }



}
