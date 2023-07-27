package com.generic.retailer;

import java.util.ArrayList;
import java.util.HashMap;

public final class Trolley {

	static final int RECEIPT_LINE_LENGTH = 20;

	private HashMap<String, Product> products;

	public Trolley() {
		this.products = new HashMap<>();
	}

	public void add( Product product, int count ) {

		// Check if the item already exists in the trolley
		product = this.products.getOrDefault( product.getName(), product );
		product.add( count );
		this.products.putIfAbsent( product.getName(), product );
	}
	public void add( Product product ) {
		this.add( product, 1);
	}

	/**
	 * Get the total number of items in the trolley
	 * @return the number of items
	 */
	public int getTotalItems() {

		int totalItems = 0;

		for( Product product  : this.products.values() ) {
			totalItems += product.getCount();
		}

		return totalItems;
	}

	public float totalDiscounts() {

		float discounts = 0f;

		for( Product product : this.products.values() ) {
			discounts += product.calculateDiscount();
		}

		return discounts;
	}

	/**
	 * Total for the trolley, excluding any discounts
	 * @return amount
	 */
	public float subtotal() {

		float total = 0f;

		for( Product product : this.products.values() ) {
			total += product.total();
		}

		return total;
	}

	/**
	 * The total for the trolley, including any discounts
	 * @return amount
	 */
	public float total() {
		return this.subtotal() - this.totalDiscounts();
	}

	private String getPadding(int size) {
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<size; i++){
			sb.append(" ");
		}
		return sb.toString();
	}

	public String getReceipt() {

		ArrayList<String> receiptLines = new ArrayList<>();
		receiptLines.add("====== RECEIPT ======");

		float twoForOneDiscounts = 0f;
		float thursdayDiscount = 0f;

		for( Product product : this.products.values()) {
			String line = product.getReceiptLine();
			String price = String.valueOf(product.total());
			int padding = RECEIPT_LINE_LENGTH - (line.length() + price.length());

			receiptLines.add(String.format(
				"%s%s£%s",
				line,
				this.getPadding(padding),
				price
			));

			twoForOneDiscounts += product.calculateProductDiscount();
			thursdayDiscount += product.calculateThursdayDiscount();
		}

		if( twoForOneDiscounts > 0 ) {
			String TFOlineString = "2 FOR 1";
			String strTwoForOneDiscounts = String.valueOf(twoForOneDiscounts);
			// +1 for the minus sign
			int twoForOnePadding = RECEIPT_LINE_LENGTH - (TFOlineString.length() + strTwoForOneDiscounts.length() + 1);

			receiptLines.add(String.format(
				"%s%s-£%s",
				TFOlineString,
				this.getPadding(twoForOnePadding),
				twoForOneDiscounts
			));
		}

		if( thursdayDiscount > 0 ) {
			String thursLineString = "THURS";
			String strThursdayDiscounts = String.valueOf(thursdayDiscount);
			// +1 for the minus sign
			int thursdayPadding = RECEIPT_LINE_LENGTH - (thursLineString.length() + strThursdayDiscounts.length() + 1);
			receiptLines.add(String.format(
				"%s%s-£%s",
				thursLineString,
				this.getPadding(thursdayPadding),
				strThursdayDiscounts
			));
		}

		receiptLines.add("=====================");
		String total = String.valueOf(this.total());
		int totalPaddingSize = RECEIPT_LINE_LENGTH - ("TOTAL".length() + total.length());
		receiptLines.add(String.format(
			"TOTAL%s£%s",
			this.getPadding(totalPaddingSize),
			total
		));

		StringBuilder sb = new StringBuilder();
		for (String s : receiptLines) {
			sb.append(s + "\n");
		}

		return sb.toString();
	}
}

