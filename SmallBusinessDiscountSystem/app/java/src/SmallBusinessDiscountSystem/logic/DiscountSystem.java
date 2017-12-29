package SmallBusinessDiscountSystem.logic;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DiscountSystem {
  public VDMSet customers = SetUtil.set();
  public VDMMap merchants = MapUtil.map();

  public void cg_init_DiscountSystem_1() {

    return;
  }

  public DiscountSystem() {

    cg_init_DiscountSystem_1();
  }

  public VDMSet GetCustomers() {

    return Utils.copy(customers);
  }

  public VDMMap GetMerchants() {

    return Utils.copy(merchants);
  }

  public void customerJoins(final Customer customer) {

    customers = SetUtil.union(Utils.copy(customers), SetUtil.set(customer));
  }

  public void merchantJoins(final Merchant merchant) {

    merchants =
        MapUtil.munion(
            Utils.copy(merchants), MapUtil.map(new Maplet(merchant.GetName(), merchant)));
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

  public Merchant getMerchantByName(final String name) {

    return ((Merchant) Utils.get(merchants, name));
  }

  public VDMSet getCustomerByName(final String name) {

    VDMSet setCompResult_1 = SetUtil.set();
    VDMSet set_1 = Utils.copy(customers);
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Customer customer = ((Customer) iterator_1.next());
      if (customer.fullTextSearch(name)) {
        setCompResult_1.add(customer);
      }
    }
    return Utils.copy(setCompResult_1);
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
