package com.czxy.bookstore.domain;

/*
 * 购物车项实体
 */
public class CartItem {

	// 购物车项记录的商品
	private Product product;
	// 该商品的购物数量
	private int count;
	// 该数量的该商品一共多少钱(小计)
	private double subTotal;

	public CartItem() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 每次获取小计时，都在获取的时候现算一遍,将其返回
	 * @return 该商品项的小计
	 */
	public double getSubTotal() {
		
		//计算小计,商品单价*商品数量
		subTotal = product.getShop_price() * count;
		//返回小计
		return subTotal;
	}

	/*不需要set方法，前台页面product_indfo.jsp没有传入小计
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	*/

	@Override
	public String toString() {
		return "CarItem [product=" + product + ", count=" + count + ", subTotal=" + subTotal + "]";
	}
}
