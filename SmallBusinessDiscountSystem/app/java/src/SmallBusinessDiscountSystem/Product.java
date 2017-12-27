package SmallBusinessDiscountSystem;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Product {
  public String name;
  public Number cost;
  public Number quantity;
  public Number extraDiscount;

  public void cg_init_Product_1(
      final String product_name,
      final Number product_cost,
      final Number product_quantity,
      final Number product_discount) {

    name = product_name;
    cost = product_cost;
    quantity = product_quantity;
    extraDiscount = product_discount;
    return;
  }

  public Product(
      final String product_name,
      final Number product_cost,
      final Number product_quantity,
      final Number product_discount) {

    cg_init_Product_1(product_name, product_cost, product_quantity, product_discount);
  }

  public String getName() {

    throw new UnsupportedOperationException();
  }

  public Number getPrice() {

    throw new UnsupportedOperationException();
  }

  public Number getDiscount() {

    throw new UnsupportedOperationException();
  }

  public Number getQuantity() {

    throw new UnsupportedOperationException();
  }

  public void decreaseQuantity(final Number amount) {

    throw new UnsupportedOperationException();
  }

  public void setDiscount(final Number discount) {

    throw new UnsupportedOperationException();
  }

  public Product() {}

  public String toString() {

    return "Product{"
        + "name := "
        + Utils.toString(name)
        + ", cost := "
        + Utils.toString(cost)
        + ", quantity := "
        + Utils.toString(quantity)
        + ", extraDiscount := "
        + Utils.toString(extraDiscount)
        + "}";
  }
}
