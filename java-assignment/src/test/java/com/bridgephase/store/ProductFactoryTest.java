package com.bridgephase.store;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductFactoryTest {

	@Test
	public void test() {
		testInflateItemProduct();
	}
		private void testInflateItemProduct() {
			String p="A123,Apple,0.50,1.00,100";
			Product pt= ProductFactory.inflateItemProduct(p);
			assert(pt!= null);
			assert(pt.getName().equals("Apple"));
			System.out.println("/"+ pt.getUpc()+ "/");
			assert(pt.getUpc().equals("A123")); 
			
		}
}
