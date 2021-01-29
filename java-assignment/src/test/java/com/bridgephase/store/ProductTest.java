package com.bridgephase.store;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

	@Test
	public void test() {
		testInflateItemProduct();
		Product apple = new ItemProduct("A123", "Apple", 0.50, 1.00, 100);
	assertEquals (100, apple.getQuantity().intValue()); 
	assertEquals ("A123", apple.getUpc());
	assertEquals (0.50, apple.getWholeSalePrice());
	assertEquals (1.00, apple.getRetailPrice());
	assertEquals (100.0, apple.getQuantity());
	}
		private void testInflateItemProduct() {
			String p="A123,Apple,0.50,1.00,100";
			Product pt= ProductFactory.inflateItemProduct(p);
			assert(pt!= null);
			assert(pt.getName().equals("Apple"));
			System.out.println("/"+ pt.getUpc()+ "/");
			assert(pt.getUpc().equals("A123")); 
			
		}
		public void testSetWholeSaleNumber() {
		    System.out.println("setWholeSaleNumber");
		    Number WholeSalePrice = 1000;
		    WholeSalePrice instance = new WholeSalePrice();
		    instance.setWholeSaleNumber(WholeSalePrice);
		    // TODO review the generated test code and remove the default call to fail.
		    assertEquals(instance.getwholeSalePrice(), WholeSalePrice);
		}
		
	
	
}
