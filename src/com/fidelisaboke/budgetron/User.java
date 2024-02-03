package com.fidelisaboke.budgetron;

public class User {
    private String userName;
    private String userPhone;
    private String userPass;

    public User(String userName, String userPhone, String userPass){
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
