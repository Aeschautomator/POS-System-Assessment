package com.bridgephase.store;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bridgephase.store.interfaces.IInventory;

public class CashRegister {
	public IInventory inventory;
	public HashMap<String, Product> map;
	public boolean inTransaction;
	public List<String> transaction;
	public double total;
	public double numberOfItems;
	public double paid;
	public double change;
	public BufferedWriter receipt; 
	
	CashRegister(IInventory inventory) {
		this.inventory = inventory;
		map = createHashmap(inventory);
	}

	private HashMap<String, Product> createHashmap(IInventory inventory) {
		HashMap<String, Product> map = new HashMap<String, Product>();
		List<Product> products = inventory.list();
		int size = products.size();
		for (int i = 0; i < size; i++) {
			Product product = products.get(i);
			map.put(product.getUpc(), product);
		}
		return map;
	}

	public void beginTransaction() {
		inTransaction = true;
		transaction = new ArrayList<String>();
		total = 0;
	}

	public boolean scan(String upc, double amount) {
		if (!inTransaction)
			return false;
		Product product = map.get(upc);
		if (product == null)
			return false;
		if (product.reduce(amount)) {
			addTransaction(product, amount);
			return true;

		}
		return false;
	}

	public void addTransaction(Product product, double amount) {
		double cost = product.getRetailPrice().doubleValue() * amount;
		String t = amount + " " + product.getName() + " @ $" + product.getRetailPrice() + ":" + cost;
		total += cost;
		numberOfItems += amount;
		transaction.add(t);
	}

	public BigDecimal getTotal() {
		return new BigDecimal(total);
	}

	public BigDecimal pay(BigDecimal cashAmount) {
		change = total - cashAmount.doubleValue();
//			if it is negative, they still owe money
		paid = cashAmount.doubleValue();
		return new BigDecimal(change);
	}

	public void printReceipt(OutputStream os) {
		try {
			receipt = new BufferedWriter(new OutputStreamWriter(os));
			receipt.write("Bridgephase Convenience Store");
			receipt.newLine();
			receipt.write("-----------------------------");
			receipt.newLine();
			receipt.write("Total Products Bought: " + numberOfItems);
			receipt.newLine();
			receipt.newLine();

			for (int i = 0; i < transaction.size(); i++) {
				receipt.write(transaction.get(i));
				receipt.newLine();
			}
			receipt.write("-----------------------------");
			receipt.newLine();
			receipt.write("Total: $" + total);
			receipt.newLine();
			receipt.write("Paid: $" + paid);
			receipt.newLine();
			receipt.write("Change: $" + change);
			receipt.newLine();
			receipt.write("-----------------------------");
			receipt.close();

		} catch (Exception e) {
		}
	}
}
