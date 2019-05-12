package com.bridgephase.store;

public abstract class Product {
	
	protected String upc;
	protected String name;
	
public String getName() {
  return name;
}

public String getUpc() {
	return upc;
}

public void setUpc(String upc) {
	this.upc = upc;
}

public void setName(String newName) {
	  this.name = newName;
	}

public abstract boolean add(Product p); 

public abstract Number getWholeSalePrice();

public abstract void  setWholeSalePrice(Number wholeSalePrice);

public abstract Number getRetailPrice();

public abstract void setRetailPrice(Number retailPrice);

public abstract Number getQuantity();

public abstract void setQuantity(Number quantity);
}
