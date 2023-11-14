package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户 DO
 */
@Document(collection = "User")
public class UserDO {

    /**
     * 用户信息
     */
    public static class Profile {

        /**
         * 昵称
         */
        private String nickname;
        /**
         * 性别
         */
        private Integer gender;

        public String getNickname() {
            return nickname;
        }

        public Profile setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Integer getGender() {
            return gender;
        }

        public Profile setGender(Integer gender) {
            this.gender = gender;
            return this;
        }


    }

    @Id
    private String id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户信息
     */
    private Profile profile;

    private List<Profile> profiles;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public UserDO setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", profile=" + profile +
                '}';
    }

}
