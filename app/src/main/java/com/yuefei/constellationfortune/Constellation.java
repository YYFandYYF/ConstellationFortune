package com.yuefei.constellationfortune;

/*
今日或明日运势格式
{
        "name": "狮子座",/*星座名称
        "datetime": "2014年06月27日",/*日期
        "date": 20140627,
        "all": "89", /*综合指数
        "color": "古铜色", /*幸运色
        "health": "95", /*健康指数
        "love": "80",/*爱情指数
        "money": "84",/*财运指数
        "number": 8,/*幸运数字
        "QFriend": "处女座",/*速配星座
        "summary": "有些思考的小漩涡，可能让你忽然的放空，生活中许多的细节让你感触良多，五味杂陈，
        常常有时候就慢动作定格，想法在某处冻结停留，陷入一阵自我对话的沉思之中，这个时候你不喜欢被打扰
        或询问，也不想让某些想法曝光，个性变得有些隐晦。",/*今日概述
        "work": "80"/*工作指数
        "error_code": 0/*返回码
        }
 */
public class Constellation {

    private String name;
    private String datetime;
    private String date;
    private String all;
    private String color;
    private String health;
    private String love;
    private String money;
    private String number;
    private String QFriend;
    private String summary;
    private String work;
    private String job;
    private String error_code;
    private int image;

    public Constellation(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getQFriend() {
        return QFriend;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
