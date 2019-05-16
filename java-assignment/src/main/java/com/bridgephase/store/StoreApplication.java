package com.bridgephase.store;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.bridgephase.store.interfaces.IInventory;

/**
 * This can be used as an entry point to ensure that you have a working implementation for Part 1.
 * 
 * This class will initially contain compilation errors because the classes in
 * the exercise have not been implemented. Once you've implemented them the
 * compilation errors should disappear.
 * 
 * You are free to modify this class as you deem necessary although it's highly
 * recommended that you produce jUnit tests to verify the logic in your code.
 * 
 * @author Jaime Garcia Ramirez (jramirez@bridgephase.com)
 */
public class StoreApplication {

	/**
	 * This is the main entry point to this application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
	
			InputStream input = inputStreamFromString(
					"upc,name,wholesalePrice,retailPrice,quantity\n" + 
					"A123,Apple,0.50,1.00,100\nB234,Peach,0.35,0.75,200\n" + "C123,Milk,2.15,4.50,40");
			IInventory inventory = new Inventory();
			if (input != null) inventory.replenish(input);

			for (Product product : inventory.list()) {
				System.out.println("Found a product " + product);
			}

		}


	/**
	 * This is a simple way to convert a string to an input stream.
	 * 
	 * @param value
	 *            the String value to convert
	 * @return an InputStream that can read the values from the
	 *         <code>value</code> parameter
	 * @throws UnsupportedEncodingException
	 */
	private static InputStream inputStreamFromString(String value) {
		try {
			return new ByteArrayInputStream(value.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}