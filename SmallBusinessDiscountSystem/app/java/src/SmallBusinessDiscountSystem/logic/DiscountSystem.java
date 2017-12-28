package SmallBusinessDiscountSystem.logic;

import java.util.*;

import SmallBusinessDiscountSystem.logic.Merchant;
import SmallBusinessDiscountSystem.logic.Product;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DiscountSystem {
  public VDMSet customers = SetUtil.set();
  public VDMSet merchants = SetUtil.set();

  public void cg_init_DiscountSystem_1() {

    return;
  }

  public DiscountSystem() {

    cg_init_DiscountSystem_1();
  }

  public VDMSet GetCustomers() {

    return Utils.copy(customers);
  }

  public VDMSet GetMerchants() {

    return Utils.copy(merchants);
  }

  public void customerJoins(final Customer customer) {

    customers = SetUtil.union(Utils.copy(customers), SetUtil.set(customer));
  }

  public void merchantJoins(final Merchant merchant) {

    merchants = SetUtil.union(Utils.copy(merchants), SetUtil.set(merchant));
  }

  public void buyProduct(
      final Merchant merchant,
      final Customer customer,
      final Product product,
      final Number amount) {

    customer.increaseBalance(merchant.sellProduct(product, customer, amount));
  }

  public void buyProductUsingBalance(
      final Merchant merchant,
      final Customer customer,
      final Product product,
      final Number amount) {

    customer.decreaseBalance(product.getPrice());
    customer.increaseBalance(merchant.sellProduct(product, customer, amount));
  }

  public void transfer(final Customer sender, final Customer receiver, final Number value) {

    sender.withdraw(value);
    receiver.deposit(value);
  }

  public void inviteCustomer(final Customer user, final Customer invitee) {

    customerJoins(invitee);
    user.receiveBonus();
  }

  public void inviteMerchant(final Merchant merchant, final Merchant invitee) {

    merchantJoins(invitee);
    merchant.receiveBonus();
  }

  public String toString() {

    return "DiscountSystem{"
        + "customers := "
        + Utils.toString(customers)
        + ", merchants := "
        + Utils.toString(merchants)
        + "}";
  }
}
