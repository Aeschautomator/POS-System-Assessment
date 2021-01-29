package com.bridgephase.store;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

	public static Product inflateItemProduct(String str) {
		String[] props = str.split(",");
		double wholeSalePrice = Double.parseDouble(props[2]);
		double retailPrice = Double.parseDouble(props[3]);
		double quantity = Double.parseDouble(props[4]);

		return new ItemProduct(props[0], props[1], wholeSalePrice, retailPrice, quantity);
	}

	public static List<Product> createInventory(InputStream inputStream) {
		ArrayList list = new ArrayList<Product>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			reader.readLine();
			while (reader.ready()) {
				String line = reader.readLine();
				list.add(inflateItemProduct(line));
			}
			
		} catch (

		Exception e) {
		
		}
		return list;
	}
}
