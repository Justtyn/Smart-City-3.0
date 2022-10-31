package com.example.smartcity30.bean;

import java.util.List;

public class AmountsChangesInfoResult {

    /**
     * total : 9
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":894,"appType":"park","userId":1111943,"event":null,"changeAmount":1000,"changeType":"收入","userName":null,"changeTime":"2022-10-30 09:23:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":893,"appType":"park","userId":1111943,"event":null,"changeAmount":1000,"changeType":"收入","userName":null,"changeTime":"2022-10-30 09:23:16"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":892,"appType":"park","userId":1111943,"event":null,"changeAmount":100,"changeType":"收入","userName":null,"changeTime":"2022-10-30 09:23:14"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":891,"appType":"park","userId":1111943,"event":null,"changeAmount":100,"changeType":"收入","userName":null,"changeTime":"2022-10-30 09:23:11"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":890,"appType":"park","userId":1111943,"event":null,"changeAmount":1000,"changeType":"收入","userName":null,"changeTime":"2022-10-30 09:22:23"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":889,"appType":"park","userId":1111943,"event":null,"changeAmount":1000,"changeType":"收入","userName":null,"changeTime":"2022-10-30 09:21:41"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":750,"appType":"park","userId":1111943,"event":null,"changeAmount":23,"changeType":"收入","userName":null,"changeTime":"2022-10-28 14:03:34"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":749,"appType":"park","userId":1111943,"event":null,"changeAmount":100,"changeType":"收入","userName":null,"changeTime":"2022-10-28 13:59:43"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":721,"appType":"park","userId":1111943,"event":null,"changeAmount":100,"changeType":"收入","userName":null,"changeTime":"2022-10-27 09:55:00"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 894
     * appType : park
     * userId : 1111943
     * event : null
     * changeAmount : 1000
     * changeType : 收入
     * userName : null
     * changeTime : 2022-10-30 09:23:18
     */

    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        private int id;
        private String appType;
        private int userId;
        private Object event;
        private int changeAmount;
        private String changeType;
        private Object userName;
        private String changeTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getEvent() {
            return event;
        }

        public void setEvent(Object event) {
            this.event = event;
        }

        public int getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(int changeAmount) {
            this.changeAmount = changeAmount;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public String getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(String changeTime) {
            this.changeTime = changeTime;
        }
    }
}
