package com.example.smartcity30.bean;

public class RegisterInfo {
    private String userName;
    private String password;
    private String phonenumber;
    private String sex;

    public RegisterInfo(String userName, String password, String phonenumber, String sex) {
        this.userName = userName;
        this.password = password;
        this.phonenumber = phonenumber;
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
