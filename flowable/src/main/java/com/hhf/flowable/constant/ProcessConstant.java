package com.hhf.flowable.constant;

/**
 * @author huhaifeng
 * @since 2019-03-19
 * 工作流静态变量及枚举
 */
public class ProcessConstant {

    public final static String TENANTID = "myproject";
    public final static String HANDLE_LEVEL = "currentHandlerLevel";
    public final static String AUDIT_CODE = "auditCode";
    public final static String AUDIT_REASON = "auditReason";


    /**
     * 流程id枚举
     */
    public enum ProcessEnum {
        PROCESS_ID("myproject", "myProcess");
        private String code;
        private String lable;

        ProcessEnum(String code, String lable) {
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

    /**
     * 任务等级
     */
    public enum TaskLevelEnum {
        /**
         * 分公司任务等级
         */
        BRANCH(1, 3),
        /**
         * 总公司任务等级
         */
        HEAD_BRANCH(4,6);


        private Integer minLv;
        private Integer maxLv;

        TaskLevelEnum(Integer minLv, Integer maxLv) {
            this.minLv = minLv;
            this.maxLv = maxLv;
        }

        public Integer getMinLv() {
            return minLv;
        }

        public void setMinLv(Integer minLv) {
            this.minLv = minLv;
        }

        public Integer getMaxLv() {
            return maxLv;
        }

        public void setMaxLv(Integer maxLv) {
            this.maxLv = maxLv;
        }
    }

    /**
     * 任务状态枚举
     */
    public enum TaskStatusEnum {
        Y("已审核", "Y"),
        N("未审核","N");
        private String code;
        private String lable;

        TaskStatusEnum(String code, String lable) {
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
