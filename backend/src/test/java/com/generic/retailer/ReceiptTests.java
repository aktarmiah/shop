package com.generic.retailer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ReceiptTests {

	@Test
	public void noThursdayDiscount() {
		DVD mockedDvd = spy(DVD.class);
		when(mockedDvd.isThursday()).thenReturn(false);
		Trolley trolley = new Trolley();
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			DVD (x3)        £45.0
			2 FOR 1        -£15.0
			=====================
			TOTAL           £30.0
			""");
	}

	@Test
	public void threeDvdsOnThursday() {
		DVD mockedDvd = spy(DVD.class);
		when(mockedDvd.isThursday()).thenReturn(true);
		Trolley trolley = new Trolley();
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			DVD (x3)        £45.0
			2 FOR 1        -£15.0
			THURS           -£3.0
			=====================
			TOTAL           £27.0
			""");
	}

	@Test
	public void bestPriceDiscountOnThursday() {
		DVD mockedDvd = spy(DVD.class);
		when(mockedDvd.isThursday()).thenReturn(true); // Make it Thursday
		Trolley trolley = new Trolley();
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			DVD (x4)        £60.0
			2 FOR 1        -£30.0
			=====================
			TOTAL           £30.0
			""" );
	}

	@Test
	public void calculateTotal1() {
		DVD mockedDvd = spy(DVD.class);
		CD mockedCd = spy(CD.class);
		Book mockedBook = spy(Book.class);
		when(mockedDvd.isThursday()).thenReturn(false); // Make it Thursday
		when(mockedCd.isThursday()).thenReturn(false); // Make it Thursday
		when(mockedBook.isThursday()).thenReturn(false); // Make it Thursday
		Trolley trolley = new Trolley();
		trolley.add(mockedCd);
		trolley.add(mockedBook);
		trolley.add(mockedBook);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			CD              £10.0
			BOOK (x2)       £10.0
			=====================
			TOTAL           £20.0
			""" );
	}

	@Test
	public void calculateTotal2() {
		DVD mockedDvd = spy(DVD.class);
		CD mockedCd = spy(CD.class);
		Book mockedBook = spy(Book.class);
		when(mockedDvd.isThursday()).thenReturn(false); // Make it Thursday
		when(mockedCd.isThursday()).thenReturn(false); // Make it Thursday
		when(mockedBook.isThursday()).thenReturn(false); // Make it Thursday
		Trolley trolley = new Trolley();
		trolley.add(mockedCd);
		trolley.add(mockedBook);
		trolley.add(mockedDvd);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			CD              £10.0
			DVD             £15.0
			BOOK             £5.0
			=====================
			TOTAL           £30.0
			""" );
	}

	@Test
	public void totalOnAThursday() {
		DVD mockedDvd = spy(DVD.class);
		Book mockedBook = spy(Book.class);
		CD mockedCd = spy(CD.class);
		when(mockedDvd.isThursday()).thenReturn(true);
		when(mockedBook.isThursday()).thenReturn(true);
		when(mockedCd.isThursday()).thenReturn(true);
		Trolley trolley = new Trolley();
		trolley.add(mockedCd);
		trolley.add(mockedBook);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			CD              £10.0
			DVD (x2)        £30.0
			BOOK             £5.0
			2 FOR 1        -£15.0
			THURS           -£3.0
			=====================
			TOTAL           £27.0
			""" );
	}

	@Test
	public void totalOnAThursday2() {
		DVD mockedDvd = spy(DVD.class);
		Book mockedBook = spy(Book.class);
		CD mockedCd = spy(CD.class);
		when(mockedDvd.isThursday()).thenReturn(true);
		when(mockedBook.isThursday()).thenReturn(true);
		when(mockedCd.isThursday()).thenReturn(true);
		Trolley trolley = new Trolley();
		trolley.add(mockedCd);
		trolley.add(mockedBook);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);
		trolley.add(mockedDvd);

		assertThat( trolley.getReceipt() ).isEqualTo("""
			====== RECEIPT ======
			CD              £10.0
			DVD (x3)        £45.0
			BOOK             £5.0
			2 FOR 1        -£15.0
			THURS           -£6.0
			=====================
			TOTAL           £39.0
			""");
	}
}