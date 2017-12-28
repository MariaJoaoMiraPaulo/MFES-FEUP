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

    balance = balance.doubleValue() + amount.doubleValue();
  }

  public void decreaseBalance(final Number amount) {

    if (balance.doubleValue() - amount.doubleValue() > 0L) {
      balance = balance.doubleValue() - amount.doubleValue();
    } else {
      balance = 0L;
    }
  }

  public void withdraw(final Number value) {

    balance = balance.doubleValue() - value.doubleValue();
  }

  public void deposit(final Number value) {

    balance = balance.doubleValue() + value.doubleValue();
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
