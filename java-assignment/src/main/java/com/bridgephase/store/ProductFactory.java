package com.bridgephase.store;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

	public static Product inflateItemProduct(String str) {
		String[] props = str.split(",");
		double wholeSalePrice = Double.parseDouble(props[2]);
		double retailPrice = Double.parseDouble(props[3]);
		double quantity = Double.parseDouble(props[4]);
		
		return new ItemProduct(props[0],props[1], props[2], props[3], props[4]);
	}
	public static List<Product> createInventory(InputStream inputStream){
//		TODO
		skipHeader(inputStream); 
		ArrayList list = new ArrayList<Product>();
//		Loop until end of file
//		for each line of file call inflate product
		return list;
		
	}
	private static void skipHeader(InputStream inputStream) {
//		TODO
	}
}
