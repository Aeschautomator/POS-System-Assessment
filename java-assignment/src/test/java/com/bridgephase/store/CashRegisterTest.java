package com.bridgephase.store;

import static org.junit.Assert.*;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CashRegisterTest {

	@Test
	public void test() {testBeginTransaction()}

	public void testBeginTransaction() {
		CashRegister c = new CashRegister(new Inventory());
		c.beginTransaction();
		assert (c.total == 0);
		assert (c.inTransaction == true);
		assert (c.transaction.size() == 0);
	}

	private void testaddTransaction() {
		CashRegister c = new CashRegister(new Inventory());
		c.beginTransaction();
		String p = "A123,Apple,0.50,1.00,100";
		Product pt = ProductFactory.inflateItemProduct(p);
		c.addTransaction(pt, 5);
		assert (c.inTransaction == true);
		assert (c.total == 5);
		assert (c.transaction.size() == 1);
	}

	public void testBigDecimal() {
//				TODO test BigDecimal getTotal
	}

	public void testBigDecimalpay() {
		CashRegister c = new CashRegister(new Inventory());
		c.beginTransaction();
		String p = "A123,Apple,0.50,1.00,100";
		Product pt = ProductFactory.inflateItemProduct(p);
		c.addTransaction(pt, 5);
		BigDecimal b = 5;
	}

	public void testprintReceipt() {
		CashRegister c = new CashRegister(new Inventory());
		c.beginTransaction();
		String p = "A123,Apple,0.50,1.00,100";
		Product pt = ProductFactory.inflateItemProduct(p);
		c.addTransaction(pt, 5);
		OutputStream os = null;
		List<String> ar = c.transaction;
		c.printReceipt(os);
		assert (os != null);
	}

}
