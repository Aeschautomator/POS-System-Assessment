package com.bridgephase.store;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;

import com.bridgephase.store.interfaces.IInventory;

public class InventoryTest {

	@Test
	public void test() {
		InputStream input = inputStreamFromString("upc,name,wholesalePrice,retailPrice,quantity\n"
				+ "A123,Apple,0.50,1.00,100\nB234,Peach,0.35,0.75,200\n" + "C123,Milk,2.15,4.50,40");
		testInventory(input);
	}

	private void testInventory(InputStream input) {
		IInventory inventory = new Inventory();
		List<Product> list = inventory.list();
		assertEquals(0, list.size() );
		inventory.replenish(input);
		list = inventory.list();
		assertEquals(3, list.size() );
		Product apple = list.get(0);
		assertEquals(100, apple.getQuantity().intValue());
	}

	private static InputStream inputStreamFromString(String value) {
		try {
			return new ByteArrayInputStream(value.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
