package com.czxy.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 89695 on 2019/5/28.
 */
@Table(name="orders")
public class Order {
    @Id
    @Column(name = "oid")
    @GeneratedValue(generator = "UUID")
    private String oid;
    private Date ordertime;
    @Column(name="total_price")
    private Double total;
    private Integer state; //1=未付款;2=已付款,未发货;3=已发货,没收货;4=收货,订单结束
    private String address;
    private String name;
    private String telephone;

    //如果是为了存储数据，则应该定义uid。与数据库表对应。如果用通用mapper则必须是uid，如果是User对象，则无法自动添加
    //如果是为了查找，则一定在这里定义User的对象。如果不使用通用mapper，则可以在插入时也使用user。#{user.pid}
    //这里选择都写。这样虽然重复，但一定不会出问题。正常情况下，是二选一的。
    private String uid;
    private User user;

    private List<OrderItem> orderItemList = new ArrayList<OrderItem>();


    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", state=" + state +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", uid='" + uid + '\'' +
                ", user=" + user +
                '}';
    }
}
