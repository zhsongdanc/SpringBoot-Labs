package cn.iocoder.springboot.lab16.springdatamongodb.vo;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/12/26 16:54
 */

public class UserVO{
    private String id;
    private String username;
    private String nickname;
    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
