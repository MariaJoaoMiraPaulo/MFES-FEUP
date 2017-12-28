package SmallBusinessDiscountSystem.logic;

import java.util.*;

import SmallBusinessDiscountSystem.User;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Merchant extends User {
  public static final Number SystemFee = 3L;
  public VDMSet products = SetUtil.set();
  private Number balance = 0L;

  public void cg_init_Merchant_1(final String merchantName) {

    cg_init_User_1(merchantName);
  }

  public Merchant(final String merchantName) {

    cg_init_Merchant_1(merchantName);
  }

  public VDMSet GetProducts() {

    return Utils.copy(products);
  }

  public Number GetBalance() {

    return balance;
  }

  public void receiveBonus() {

    balance = balance.doubleValue() + 5L;
  }

  public VDMSet getProductsByName(final String productName) {

    VDMSet setCompResult_1 = SetUtil.set();
    VDMSet set_1 = Utils.copy(products);
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Product product = ((Product) iterator_1.next());
      if (Utils.equals(product.name, productName)) {
        setCompResult_1.add(product);
      }
    }
    return Utils.copy(setCompResult_1);
  }

  public void setDiscount(final String productName, final Number discount) {

    for (Iterator iterator_2 = getProductsByName(productName).iterator(); iterator_2.hasNext(); ) {
      Product product = (Product) iterator_2.next();
      product.setDiscount(discount);
    }
  }

  public void addProduct(final Product product) {

    products = SetUtil.union(Utils.copy(products), SetUtil.set(product));
  }

  public void removeProduct(final Product product) {

    products = SetUtil.diff(Utils.copy(products), SetUtil.set(product));
  }

  public Number sellProduct(final Product product, final Customer customer, final Number amount) {

    balance =
        balance.doubleValue()
            + product.getPrice().doubleValue()
                * (1L - Utils.divide(product.getDiscount().doubleValue(), 100L))
                * (1L - Utils.divide(customer.getDiscount().doubleValue(), 100L))
                * Utils.divide(Merchant.SystemFee.doubleValue(), 100L)
                * amount.longValue();
    product.decreaseQuantity(amount);
    return (product.getPrice().doubleValue()
            - product.getPrice().doubleValue()
                * (1L - Utils.divide(product.getDiscount().doubleValue(), 100L))
                * (1L - Utils.divide(customer.getDiscount().doubleValue(), 100L)))
        * amount.longValue();
  }

  public Merchant() {}

  public String toString() {

    return "Merchant{"
        + "SystemFee = "
        + Utils.toString(SystemFee)
        + ", products := "
        + Utils.toString(products)
        + ", balance := "
        + Utils.toString(balance)
        + "}";
  }
}
