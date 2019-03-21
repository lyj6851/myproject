package com.hhf.common.exception.model;

import io.swagger.annotations.ApiOperation;

public interface ResponseInfo {
    @ApiOperation("获取结果代码")
    String getCode();

    @ApiOperation("获取结果信息")
    String getMessage();

    @ApiOperation("获取进行参数替换的结果信息，使用logback的MessageFormatter进行参数替换")
    String getFormattedMessage(Object... var1);

    @ApiOperation("基于返回消息的参数构建新的ResonpseInfo")
    ResponseInfo withMessageArgs(Object... var1);
}
