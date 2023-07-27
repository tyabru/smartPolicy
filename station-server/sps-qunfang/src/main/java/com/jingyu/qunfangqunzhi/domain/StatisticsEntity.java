package com.jingyu.qunfangqunzhi.domain;

/**
 *
 * 图表统计统一返回结果
 */
public class StatisticsEntity {
    /**
     * 出现次数
     */
    private Integer times;

    private Long userId;

    private String userName;

    private String realName;

    private String deptName;

    private String userType;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "StatisticsEntity{" +
                "times=" + times +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
