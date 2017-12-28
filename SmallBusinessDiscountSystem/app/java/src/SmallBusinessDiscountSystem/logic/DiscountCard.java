package SmallBusinessDiscountSystem.logic;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DiscountCard {
  private Number discount;
  private Number balance = 0L;

  public void cg_init_DiscountCard_1(final Number cardDiscount) {

    discount = cardDiscount;
    return;
  }

  public DiscountCard(final Number cardDiscount) {

    cg_init_DiscountCard_1(cardDiscount);
  }

  public Number GetBalance() {
    return balance;
  }

  public Number getDiscount() {
    return discount;
  }

  public void increaseBalance(final Number amount) {

    throw new UnsupportedOperationException();
  }

  public void decreaseBalance(final Number amount) {

    throw new UnsupportedOperationException();
  }

  public void withdraw(final Number value) {

    throw new UnsupportedOperationException();
  }

  public void deposit(final Number value) {

    throw new UnsupportedOperationException();
  }

  public DiscountCard() {}

  public String toString() {

    return "DiscountCard{"
        + "discount := "
        + Utils.toString(discount)
        + ", balance := "
        + Utils.toString(balance)
        + "}";
  }
}
