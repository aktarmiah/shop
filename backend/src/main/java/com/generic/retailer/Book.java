package com.generic.retailer;

public final class Book extends Product {

	public Book() {
		super("book", 5.0f );
	}

	@Override
	protected float calculateProductDiscount() {
		return 0;
	}
}
