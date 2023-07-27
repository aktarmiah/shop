package com.generic.retailer;

public class DVD extends Product {

	public DVD() {
		super("DVD", 15.0f );
	}

	@Override
	 protected float calculateThursdayDiscount() throws AssertionError {

		int totalCount = this.getCount();
		assert totalCount > 0;
		int remainder = totalCount % 2;

		if( this.isThursday() && remainder > 0 ) {
			return ( remainder * this.getPrice() ) * 0.2f; // 20%
		}

		return 0f;
	}

	@Override
	public float calculateProductDiscount() throws AssertionError {

		int totalCount = this.getCount();
		assert totalCount > 0;
		// 2 for 1 deal
		int dvdPairs = totalCount / 2; // will return the quotient part
		float twoForOneDiscount = this.getPrice() * dvdPairs;

		return twoForOneDiscount;
	}
}