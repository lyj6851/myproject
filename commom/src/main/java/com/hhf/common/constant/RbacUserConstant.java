package com.hhf.common.constant;

/**
 * RbacUser用户相关枚举
 */
public class RbacUserConstant {

    /**
     * 用户状态枚举
     */
    public enum UserStatusEnum {
        EFFECTIVE("有效", "1"),
        INVALID("无效","0");
        private String code;
        private String lable;

        UserStatusEnum(String code, String lable) {
            this.code = code;
            this.lable = lable;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }
    }
}
