package com.example.smartcity30.bean;

public class ChangeUserInfoInfo {

    /**
     * avatar : /profile/2020/10/26/27e7fd58-0972-4dbf-941c-590624e6a886. png
     * userName : David
     * nickName : 大卫
     * password : 123456
     * phonenumber : 15840669812
     * sex : 0
     * email : David@163.com
     * idCard : 210113199808242137
     */

    private String userName;
    private String phonenumber;
    private String sex;
    private String email;
    private String idCard;

    public ChangeUserInfoInfo(String userName, String phonenumber, String sex, String email, String idCard) {
        this.userName = userName;
        this.phonenumber = phonenumber;
        this.sex = sex;
        this.email = email;
        this.idCard = idCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
