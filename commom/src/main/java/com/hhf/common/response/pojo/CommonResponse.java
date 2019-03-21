package com.hhf.common.response.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hhf.common.exception.model.ResponseInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

/**
 * @author huhaifeng
 * @description 前后台交互返回对象结构
 * @param <T>
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@ApiModel(
        value = "CommonResponse",
        description = "前后台交互返回对象结构"
)
public class CommonResponse<T> {
    @ApiModelProperty("具体内容，不同接口返回对象类型不同，success为true时使用")
    private T data;
    @ApiModelProperty(
            value = "交互是否成功",
            example = "true"
    )
    private boolean success;
    @ApiModelProperty(
            value = "处理结果代码",
            example = "000000"
    )
    private String code;
    @ApiModelProperty(
            value = "处理结果描述",
            example = "处理成功"
    )
    private String message;

    public CommonResponse() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> CommonResponse<T> buildRespose4Success(T resp, String msg) {
        CommonResponse<T> commonResponse = new CommonResponse();
        commonResponse.setData(resp);
        commonResponse.setCode("000000");
        commonResponse.setMessage(msg);
        commonResponse.setSuccess(true);
        return commonResponse;
    }

    public static <T> CommonResponse<T> buildRespose4Fail(String errorCode, String msg) {
        CommonResponse<T> commonResponse = new CommonResponse();
        commonResponse.setCode(errorCode);
        commonResponse.setMessage(msg);
        commonResponse.setSuccess(false);
        return commonResponse;
    }

    public static <T> CommonResponse<T> buildRespose4Fail(String errorCode, String msg, Class<T> clazz) {
        CommonResponse<T> commonResponse = new CommonResponse();
        commonResponse.setCode(errorCode);
        commonResponse.setMessage(msg);
        commonResponse.setSuccess(false);
        return commonResponse;
    }

    public static <T> CommonResponse<T> buildRespose4Fail(ResponseInfo responseInfo) {
        return buildRespose4Fail(responseInfo.getCode(), responseInfo.getMessage());
    }

    public static <T> CommonResponse<T> buildRespose4Fail(ResponseInfo responseInfo, Object... argumentArray) {
        return buildRespose4Fail(responseInfo.getCode(), responseInfo.getFormattedMessage(argumentArray));
    }

    public static <T> CommonResponse<T> buildRespose4Fail(ResponseInfo responseInfo, Class<T> clazz) {
        return buildRespose4Fail(responseInfo.getCode(), responseInfo.getMessage(), clazz);
    }

    public ResponseEntity<CommonResponse<T>> toResponseEntity() {
        return ResponseEntity.ok(this);
    }

    public ResponseEntity<CommonResponse<T>> toResponseEntity4RestApiError(HttpStatus status, String errorMsgKey) {
        String headerErrKey = StringUtils.isEmpty(errorMsgKey) ? "errorMsg" : errorMsgKey.trim();
        String headerErrMsg = this.getCode() + this.getMessage();
        return ((ResponseEntity.BodyBuilder)ResponseEntity.status(status).header(headerErrKey, new String[]{headerErrMsg})).body(this);
    }
}