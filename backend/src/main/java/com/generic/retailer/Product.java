package com.generic.retailer;

import lombok.Getter;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Getter
public abstract class Product {

	private float price;
	private int count;
	private String name;

	protected Product( String name, float price ) {

		this.name = name;
		this.price = price;
		this.count = 0;
	}

	/**
	 * A description to add to the receipt
	 * @return description
	 */
	protected String getReceiptLine() {

		return String.format(
				"%s %s",
				this.name.toUpperCase(),
				this.count > 1 ? "(x" + this.count + ")" : ""
		);
	}

	/**
	 * Calculate any discount applied to the product
	 * @return discount amount
	 */
	protected abstract float calculateProductDiscount();

	/**
	 * Child classes that offer discounts should override this method
	 * @return The amount in price to discount
	 */
	public float calculateDiscount() {
		return this.calculateProductDiscount() + this.calculateThursdayDiscount();
	}
	/**
	 * Gets the aggregate cost for this product
	 * @return
	 */
	public float total() {
		return count * this.getPrice();
	}

	public void add( int count ) {
		this.count += count;
	}

	protected boolean isThursday() {

		Date date = Date.from(Instant.now());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
	}

	protected float calculateThursdayDiscount() {

		Date date = Date.from(Instant.now());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if( this.isThursday() ) {

			float totalForItemCount = this.price * this.getCount();
			return totalForItemCount * 0.2f;
		}

		return 0f;
	}
}
