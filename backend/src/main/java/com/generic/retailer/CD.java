package com.generic.retailer;

public final class CD extends Product {

	public CD() {
		super("CD", 10.0f );
	}

	@Override
	protected float calculateProductDiscount() {
		return 0;
	}
}
