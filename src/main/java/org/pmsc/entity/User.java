package org.pmsc.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    /**
     * 用户工号
     * 主键，不用数据库的生成策略，用户自定义
     */
    @Id
    @GeneratedValue(generator = "user-defined-assigned")
    @GenericGenerator(name = "user-defined-assigned", strategy = "assigned")
    private String userID;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 用户角色，0是管理员，1是制片人
     */
    private int role;

    /**
     * 用户状态，0是启用，1是禁用
     */
    private int state;

    /**
     * 备注
     */
//    private String comment;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
}
