package com.bridgephase.store; 

public class ItemProduct extends Product {

	private double wholeSalePrice;
	private double retailPrice;
	private double quantity;
	
	public ItemProduct() {
		
	}
	public ItemProduct(String upc, String name, double wholeSalePrice, double retailPrice, double quantity) {
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
		quantity += p.getQuantity().doubleValue(); 
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
		this.quantity = quantity.doubleValue();

	}
	@Override
	public boolean reduce(double sold) {
		if(quantity >= sold) {
			quantity -= sold;
			return true;
		}
		return false;
	}

}
