package com.generic.retailer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrolleyTest {

  @Test
  public void throwWhenZeroItems() {

    DVD dvd = new DVD();

    assertThatThrownBy( () -> dvd.calculateDiscount() ).isInstanceOf( AssertionError.class );
  }

  @Test
  public void noDiscount() {

    DVD mockedDvd = spy(DVD.class);
    when(mockedDvd.isThursday()).thenReturn(false); // Make it Thursday
    Trolley trolley = new Trolley();

    trolley.add(mockedDvd);
    assertThat( mockedDvd.calculateDiscount() ).isEqualTo( 0f );

  }

  @Test
  public void returnValueWhenGtZero() {

    Trolley trolley = new Trolley();
    DVD dvd = new DVD();
    trolley.add(dvd);
    trolley.add(dvd);

    assertThat( dvd.calculateDiscount() ).isGreaterThan( 0f );

  }

  @Test
  public void twoForOneDiscount() {

    Trolley trolley = new Trolley();
    DVD dvd = new DVD();
    trolley.add(dvd);
    trolley.add(dvd);

    assertThat( dvd.calculateDiscount() ).isEqualTo( 15f );

  }

  @Test
  public void noThursdayDiscount() {

    DVD mockedDvd = spy(DVD.class);
    when(mockedDvd.isThursday()).thenReturn(false);

    Trolley trolley = new Trolley();
    trolley.add(mockedDvd);

    assertThat( mockedDvd.calculateThursdayDiscount() ).isEqualTo(0f);

  }

  @Test
  public void thursdayDiscount() {

    DVD mockedDvd = spy(DVD.class);
    when(mockedDvd.isThursday()).thenReturn(true); // Make it Thursday

    Trolley trolley = new Trolley();
    trolley.add(mockedDvd);

    assertThat( mockedDvd.calculateThursdayDiscount() ).isEqualTo( ((float) (mockedDvd.getPrice() * 0.2)));

  }

  @Test
  public void bestPriceDiscount() {
    DVD mockedDvd = spy(DVD.class);
    when(mockedDvd.isThursday()).thenReturn(true); // Make it Thursday
    Trolley trolley = new Trolley();
    trolley.add(mockedDvd);
    trolley.add(mockedDvd);
    trolley.add(mockedDvd);

    assertThat( mockedDvd.calculateDiscount() ).isEqualTo( 18f );

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

    assertThat( mockedDvd.calculateDiscount() ).isEqualTo( 30f );
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

    assertThat( trolley.total() ).isEqualTo( 20f );
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

    assertThat( trolley.total() ).isEqualTo( 30f );
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

    assertThat( trolley.total() ).isEqualTo( 27f );
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

    assertThat( trolley.total() ).isEqualTo( 39f );
  }

}
