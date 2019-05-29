package com.czxy.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 89695 on 2019/5/28.
 */
@Table(name="orderitem")
public class OrderItem {

    @Id
    @Column(name = "itemid")
    private String itemid;
    @Column(name="quantity")
    private Integer count;
    @Column(name="sub_price")
    private Double subtotal;

    private String pid;  //商品id
    private Product product; // 订单项中的商品的信息
    private String oid;   //订单id
    private Order order; // 订单项属于哪个订单

    public OrderItem() {
        super();
    }

    public String getItemid() {
        return itemid;
    }
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                "itemid='" + itemid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", pid='" + pid + '\'' +
                ", product=" + product +
                ", oid='" + oid + '\'' +
                ", order=" + order +
                '}';
    }
}
