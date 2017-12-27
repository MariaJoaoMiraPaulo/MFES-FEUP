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

    throw new UnsupportedOperationException();
  }

  public Number GetBalance() {

    throw new UnsupportedOperationException();
  }

  public void receiveBonus() {

    throw new UnsupportedOperationException();
  }

  public VDMSet getProductsByName(final String productName) {

    throw new UnsupportedOperationException();
  }

  public void setDiscount(final String productName, final Number discount) {

    throw new UnsupportedOperationException();
  }

  public void addProduct(final Product product) {

    throw new UnsupportedOperationException();
  }

  public void removeProduct(final Product product) {

    throw new UnsupportedOperationException();
  }

  public Number sellProduct(final Product product, final Customer customer, final Number amount) {

    throw new UnsupportedOperationException();
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
