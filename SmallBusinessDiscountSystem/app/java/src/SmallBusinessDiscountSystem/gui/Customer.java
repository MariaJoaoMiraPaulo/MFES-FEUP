package SmallBusinessDiscountSystem.gui;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Customer extends User {
  public static final Number defaultDiscount = 3L;
  public static final Number defaultBonus = 5L;
  private DiscountCard discountCard = new DiscountCard(Customer.defaultDiscount);

  public void cg_init_Customer_1(final String String) {

    discountCard = new DiscountCard(0.1);
    cg_init_User_1(String);
  }

  public Customer(final String String) {

    cg_init_Customer_1(String);
  }

  public Number GetBalance() {

    throw new UnsupportedOperationException();
  }

  public Number getDiscount() {

    throw new UnsupportedOperationException();
  }

  public DiscountCard GetCard() {

    throw new UnsupportedOperationException();
  }

  public void deposit(final Number value) {

    throw new UnsupportedOperationException();
  }

  public void withdraw(final Number value) {

    throw new UnsupportedOperationException();
  }

  public void receiveBonus() {

    throw new UnsupportedOperationException();
  }

  public void increaseBalance(final Number amount) {

    throw new UnsupportedOperationException();
  }

  public void decreaseBalance(final Number amount) {

    throw new UnsupportedOperationException();
  }

  public Customer() {}

  public String toString() {

    return "Customer{"
        + "defaultDiscount = "
        + Utils.toString(defaultDiscount)
        + ", defaultBonus = "
        + Utils.toString(defaultBonus)
        + ", discountCard := "
        + Utils.toString(discountCard)
        + "}";
  }
}
