package com.xxy.pojo;

import java.sql.Timestamp;
import java.util.Objects;

public class Courier {
    private int number;
    private String couriername;
    private String courierphone;
    private String idcard;
    private String password;
    private int count;
    private Timestamp registertime;
    private Timestamp logintime;

    public Courier() {
    }

    public Courier(String couriername, String courierphone, String idcard, String password) {
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.password = password;
    }

    public Courier(int number, String couriername, String courierphone, String idcard, String password) {
        this.number = number;
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.password = password;
    }

    public Courier(int number, String couriername, String courierphone, String idcard, String password, int count, Timestamp registertime, Timestamp logintime) {
        this.number = number;
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.password = password;
        this.count = count;
        this.registertime = registertime;
        this.logintime = logintime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getCourierphone() {
        return courierphone;
    }

    public void setCourierphone(String courierphone) {
        this.courierphone = courierphone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Timestamp registertime) {
        this.registertime = registertime;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "number='" + number + '\'' +
                ", couriername='" + couriername + '\'' +
                ", courierphone='" + courierphone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", password='" + password + '\'' +
                ", count=" + count +
                ", registertime=" + registertime +
                ", logintime=" + logintime +
                '}';
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return number == courier.number &&
                count == courier.count &&
                Objects.equals(couriername, courier.couriername) &&
                Objects.equals(courierphone, courier.courierphone) &&
                Objects.equals(idcard, courier.idcard) &&
                Objects.equals(password, courier.password) &&
                Objects.equals(registertime, courier.registertime) &&
                Objects.equals(logintime, courier.logintime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, couriername, courierphone, idcard, password, count, registertime, logintime);
    }
}
