package com.bridgephase.store;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class CashRegisterTest {

	@Test
	public void test() {
		testBeginTransaction() ;
		test1();
		
	}
	private void testBeginTransaction() {
		CashRegister c = new CashRegister(new Inventory());
		assertFalse (c.inTransaction);

		c.beginTransaction();
		assertTrue(c.inTransaction);
		assertEquals( 0.0, c.total, .001);
		assertEquals (c.transaction.size(), 0);
	}

	private void test1() {
		CashRegister c = new CashRegister(new Inventory());
		c.beginTransaction();
		
		String p = "A123,Apple,0.50,1.00,100";
		Product pt = ProductFactory.inflateItemProduct(p);
		c.addTransaction(pt, 5);
		
		assertTrue(c.inTransaction);
		assertEquals(5.0, c.total, .001);
		assertEquals (c.transaction.size(), 1);

		BigDecimal change = c.pay(new BigDecimal(10));
		assertEquals(change.doubleValue(), 5.0, .001 );
		assertEquals(10, c.paid, .001);
		
		p="B234,Peach,0.35,0.75,200";
		pt= ProductFactory.inflateItemProduct(p);
		c.addTransaction(pt, 10);
		
		assertTrue(c.inTransaction);
		assertEquals(12.50, c.total, .001);
		assertEquals (c.transaction.size(), 2);
		
		change = c.pay(new BigDecimal(20));
		assertEquals (change.doubleValue(), 7.50, .001 );

		try {
			OutputStream os = new ByteArrayOutputStream (30000);
			c.printReceipt(os);
			String receipt = os.toString();
			String[] lines = receipt.split("\n");
			assertEquals ("Bridgephase Convenience Store", lines[0].trim());
			assertEquals ("-----------------------------", lines[1].trim());
			assertEquals ("Total Products Bought: " + c.numberOfItems, lines[2].trim());
			assertEquals (0, lines[3].trim().length());
			assertEquals ("5 Apple @ $1.00: $5.00", lines[4].trim());
			assertEquals ("10 Peach @ $0.75: $7.50", lines[5].trim());
			assertEquals ("-----------------------------", lines[6].trim());
			assertEquals ("Total: $12.50", lines[7].trim());
			assertEquals ("Paid: $20.00", lines[8].trim());
			assertEquals ("Change: $7.50", lines[9].trim());
			assertEquals ("-----------------------------", lines[10].trim());
		} catch (Exception e) {
		}
		
		
		
		
	}

}
