package SmallBusinessDiscountSystem;

import java.util.*;
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

    throw new UnsupportedOperationException();
  }

  public void buyProductUsingBalance(
      final Merchant merchant,
      final Customer customer,
      final Product product,
      final Number amount) {

    throw new UnsupportedOperationException();
  }

  public void transfer(final Customer sender, final Customer receiver, final Number value) {

    throw new UnsupportedOperationException();
  }

  public void inviteCustomer(final Customer user, final Customer invitee) {

    throw new UnsupportedOperationException();
  }

  public void inviteMerchant(final Merchant merchant, final Merchant invitee) {

    throw new UnsupportedOperationException();
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
