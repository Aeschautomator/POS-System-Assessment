package com.bridgephase.store; 

public class ItemProduct extends Product {

	private double wholeSalePrice;
	private double retailPrice;
	private int quantity;
	
	public ItemProduct() {
		
	}
	public ItemProduct(String upc, String name, double wholeSalePrice, double retailPrice, int quantity) {
		this.name = name;
		this.upc = upc;
		this.quantity = quantity;
		this.wholeSalePrice = wholeSalePrice;
		this.retailPrice = retailPrice;
	}
	@Override
	public boolean add(Product p) {
		if(p == null) return false;
		if (p.upc != upc) return false;
		quantity += p.getQuantity().intValue(); 
		return true;

	}

	@Override
	public Number getWholeSalePrice() {
		return wholeSalePrice; 
	}

	@Override
	public void setWholeSalePrice(Number wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice.doubleValue();

	}

	@Override
	public Number getRetailPrice() {
	
		return retailPrice;
	}

	@Override
	public void setRetailPrice(Number retailPrice) {
		this.retailPrice = retailPrice.doubleValue();

	}

	@Override
	public Number getQuantity() {
		
		return quantity;
	}

	@Override
	public void setQuantity(Number quantity) {
		this.quantity = quantity.intValue();

	}

}
