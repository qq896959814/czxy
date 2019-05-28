package com.czxy.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 刘正风 on 2019/5/21.
 */
@Table(name="user")
public class User {

    /*
     * CREATE TABLE `user` ( `uid` varchar(32) NOT NULL, `username` varchar(20)
     * DEFAULT NULL, #用户名 `password` varchar(32) DEFAULT NULL, #密码 `name`
     * varchar(20) DEFAULT NULL, #昵称 `email` varchar(30) DEFAULT NULL, #电子邮箱
     * `telephone` varchar(20) DEFAULT NULL, #电话 `birthday` date DEFAULT NULL,
     * #生日 `sex` varchar(10) DEFAULT NULL, #性别 `state` int(11) DEFAULT 0,
     * #状态：0=未激活，1=已激活 `code` varchar(64) DEFAULT NULL, #激活码 PRIMARY KEY (`uid`)
     * ) ;
     */
    @Id
    @Column(name="uid")
    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private Date birthday;
    private String sex;
    private Integer state;
    private String code;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                '}';
    }
}
