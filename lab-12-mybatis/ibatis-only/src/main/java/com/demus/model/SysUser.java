package com.demus.model;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/8 20:15
 */



/**
 * sys_user实体类
 *
 * @author
 *
 */
public class SysUser {
    /**用户ID*/
    private Long id;
    /**用户名*/
    private String userName;
    /**密码*/
    private String userPassword;
    /**邮箱*/
    private String userEmail;
    /**简介*/
    private String userInfo;
    /**头像*/
    private String headImg;
    /**创建时间*/
    private java.time.Instant createTime;
    /**
     * 实例化
     */
    public SysUser() {
        super();
    }


    /**
     * 获取id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取userName
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置userName
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取userPassword
     *
     * @return
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置userPassword
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取userEmail
     *
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置userEmail
     *
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取userInfo
     *
     * @return
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * 设置userInfo
     *
     * @param userInfo
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 获取headImg
     *
     * @return
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置headImg
     *
     * @param headImg
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取createTime
     *
     * @return
     */
    public java.time.Instant getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime
     *
     * @param createTime
     */
    public void setCreateTime(java.time.Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysUser [id=" + id + " , userName=" + userName + " , userPassword=" + userPassword + " , userEmail=" + userEmail + " , userInfo=" + userInfo + " , headImg=" + headImg + " , createTime=" + createTime + "  ]";

    }


}

