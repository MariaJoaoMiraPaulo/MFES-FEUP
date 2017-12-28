package SmallBusinessDiscountSystem.logic;

import java.util.*;

import SmallBusinessDiscountSystem.logic.DiscountCard;
import SmallBusinessDiscountSystem.User;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Customer extends User {
  public static final Number defaultDiscount = 5L;
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

    return discountCard.GetBalance();
  }

  public Number getDiscount() {

    return discountCard.getDiscount();
  }

  public DiscountCard GetCard() {

    return discountCard;
  }

  public void deposit(final Number value) {

    discountCard.deposit(value);
  }

  public void withdraw(final Number value) {

    discountCard.withdraw(value);
  }

  public void receiveBonus() {

    discountCard.deposit(Customer.defaultBonus);
  }

  public void increaseBalance(final Number amount) {

    discountCard.increaseBalance(amount);
  }

  public void decreaseBalance(final Number amount) {

    discountCard.decreaseBalance(amount);
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
