package com.bridgephase.store;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Inventory implements com.bridgephase.store.interfaces.IInventory {
	
	private List<Product> currentInventory; 
	public Inventory() {
		currentInventory = new ArrayList<Product>();
	}
//	adding this comment to properly commit after a sucessful test
	public void replenish(InputStream inputStream) {
		List<Product> newProducts = ProductFactory.createInventory(inputStream);
//	 avoid duplicate entries in current entry by insuring only one product per upc exists
		currentInventory.addAll(newProducts);
		int size = currentInventory.size();
		HashMap<String,Product> cmap = new HashMap<String,Product>();
		for(int i=0; i<size; i++) {
			Product prod = currentInventory.get(i);
			cmap.put(prod.getUpc(), prod);
		}
		size = newProducts.size();
//		update list and map
		for(int i=0; i<size; i++) {
			Product prod = newProducts.get(i);
			Product currentProd = cmap.get(prod.getUpc());
			if(currentProd == null) {
				currentInventory.add(prod);
				cmap.put(prod.getUpc(), prod);
			} else {
				currentProd.add(prod);
			}
				
				
		}
	}

	public List<Product> list(){
		return Collections.unmodifiableList(currentInventory);
		
	}
	

}
