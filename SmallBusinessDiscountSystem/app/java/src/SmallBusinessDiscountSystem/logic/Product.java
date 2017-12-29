package SmallBusinessDiscountSystem.logic;

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

    return name;
  }

  public Number getPrice() {

    return cost;
  }

  public Number getDiscount() {

    return extraDiscount;
  }

  public Number getQuantity() {

    return quantity;
  }

  public void decreaseQuantity(final Number amount) {

    quantity = quantity.longValue() - amount.longValue();
  }

  public void setDiscount(final Number discount) {

    extraDiscount = discount;
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
