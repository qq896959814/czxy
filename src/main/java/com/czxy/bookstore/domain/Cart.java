package com.czxy.bookstore.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/*
 * 购物车实体
 */
public class Cart {

	// 购物车中的购物车项们
	private Map<String, CartItem> cartItems = new LinkedHashMap<String, CartItem>();
	// 购物车中所有购物车项的总价钱(就是要付的总价钱)
	private double total;
	
	public Cart() {
		super();
	}
	
	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	/**
	 * 每次获取总金额时，都在获取的时候现算一遍,将其返回
	 * @return
	 */
	public double getTotal() {
		
		//将总金额置为0
		total = 0;
		//计算总金额,所有商品项的subTotal之和
		//迭代成员位置的购物车项集合
		//获取map集合(CartItems)中所有的键
		Set<String> keySet = cartItems.keySet();
		//迭代所有键的集合，依次获取每一个购物车项对象
		for (String CartItemsKey: keySet) {
			//根据键找值(购物车项对象)
			CartItem cartItem = cartItems.get(CartItemsKey);
			//获取该项商品小计
			double subTotal = cartItem.getSubTotal();
			//将没个购物车项的小计累加到总金额中
			total += subTotal;
		}
		//返回总金额
		return total;
	}
	
	/*不需要set方法,直接计算即可
	public void setTotal(double total) {
		this.total = total;
	}
     * 
	 */
	
	//定义添加、删除、清空购物车项的方法
	/**
	 * 添加购物车项
	 * @param product 要添加的商品
	 * @param count 要添加商品的数量
	 */
	public void addCart(Product product,int count) {
		
		//非空判断,自己完成
		//1.判断购物车中是否有该购物车项
		//1.1获取购物车中是否有要添加商品的购物车项
		CartItem cartItem = cartItems.get(product.getPid());
		//1.2判断是否存在
		if(cartItem!=null) {
			//1.2.1如果存在该购物车项
			cartItem.setCount(cartItem.getCount()+count);
		}else {
			//1.2.2如果不存在该购物车项，添加新购物车项
			//创建要添加的购物车项对象
			cartItem = new CartItem();
			//为购物车项赋值
			cartItem.setProduct(product);
			cartItem.setCount(count);
			//向CartItems添加新的购物车项
			cartItems.put(product.getPid(),cartItem);
		}
	}
	
	/**
	 * 删除购物车项
	 * @param productId 要删除的购物车项的key,即购物车项中商品id
	 */
	public void removeCart(String productId) {
		//非空判断
		//使用成员位置的map，删除指定key的键值对
		cartItems.remove(productId);
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart() {
		
		//使用成员位置的map，进行清空动作
		cartItems.clear();
	}

	@Override
	public String toString() {
		return "Cart [CartItems=" + cartItems + ", total=" + total + "]";
	}
	
}
