package com.example.smartcity30.bean;

public class GetUserInfoResult {

    /**
     * msg : 操作成功
     * code : 200
     * user : {"userId":1111943,"userName":"justyn","nickName":null,"email":"justyn@gmail.com","phonenumber":"17614122383","sex":"0","avatar":"","idCard":"210882199807251656","balance":1000,"score":1000}
     */

    private String msg;
    private int code;
    /**
     * userId : 1111943
     * userName : justyn
     * nickName : null
     * email : justyn@gmail.com
     * phonenumber : 17614122383
     * sex : 0
     * avatar :
     * idCard : 210882199807251656
     * balance : 1000.0
     * score : 1000
     */

    private UserBean user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private int userId;
        private String userName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String idCard;
        private double balance;
        private int score;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
