package SmallBusinessDiscountSystem.logic;

import java.util.*;
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

    VDMSet setCompResult_2 = SetUtil.set();
    VDMSet set_2 = Utils.copy(products);
    for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext(); ) {
      Product product = ((Product) iterator_2.next());
      if (Utils.equals(product.name, productName)) {
        setCompResult_2.add(product);
      }
    }
    return Utils.copy(setCompResult_2);
  }

  public void setDiscount(final String productName, final Number discount) {

    for (Iterator iterator_4 = getProductsByName(productName).iterator(); iterator_4.hasNext(); ) {
      Product product = (Product) iterator_4.next();
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
