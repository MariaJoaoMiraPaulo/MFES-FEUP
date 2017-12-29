package SmallBusinessDiscountSystem.logic;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DiscountSystemTest {
  private Customer customer1 = new Customer("Rui");
  private Customer customer2 = new Customer("Joana");
  private Merchant merchant1 = new Merchant("zara");
  private Product product = new Product("botas", 24.99, 5L, 5L);

  private void Assert(final Boolean cond) {

    return;
  }

  private void AssertEqual(final Object expected, final Object actual) {

    if (!(Utils.equals(expected, actual))) {
      IO.print("Actual value (");
      IO.print(((Object) actual));
      IO.print(") different from expected (");
      IO.print(((Object) expected));
      IO.println(")\n");
    }
  }

  private void testCreateCustomer() {

    Customer customer = new Customer("Rui");
    Assert(Utils.equals(customer.GetName(), "Rui"));
    Assert(Utils.equals(customer.GetBalance(), 0L));
  }

  private void testCreateMerchant() {

    Merchant merchant = new Merchant("zara");
    Assert(Utils.equals(merchant.GetName(), "zara"));
    Assert(Utils.empty(merchant.GetProducts()));
    Assert(Utils.equals(merchant.GetBalance(), 0L));
  }

  private void testCustomerJoins() {

    DiscountSystem system = new DiscountSystem();
    Assert(!(SetUtil.inSet(customer1, system.customers)));
    system.customerJoins(customer1);
    Assert(SetUtil.inSet(customer1, system.customers));
  }

  private void testMerchantJoins() {

    DiscountSystem system = new DiscountSystem();
    Assert(!(SetUtil.inSet(merchant1.GetName(), MapUtil.dom(system.merchants))));
    system.merchantJoins(merchant1);
    Assert(SetUtil.inSet(merchant1.GetName(), MapUtil.dom(system.merchants)));
  }

  private void testAddProduct() {

    Assert(!(SetUtil.inSet(product, merchant1.GetProducts())));
    merchant1.addProduct(product);
    Assert(SetUtil.inSet(product, merchant1.GetProducts()));
    Assert(Utils.equals(product.getName(), "botas"));
    Assert(Utils.equals(product.getPrice(), 24.99));
    Assert(Utils.equals(product.getQuantity(), 5L));
    Assert(Utils.equals(product.getDiscount(), 5L));
  }

  private void testRemoveProduct() {

    if (!(SetUtil.inSet(product, merchant1.products))) {
      merchant1.addProduct(product);
    }

    merchant1.removeProduct(product);
    Assert(!(SetUtil.inSet(product, merchant1.products)));
  }

  private void testSetProductDiscount() {

    Assert(Utils.equals(product.getDiscount(), 5L));
    merchant1.addProduct(product);
    merchant1.setDiscount(product.getName(), 10L);
    Assert(Utils.equals(product.getDiscount(), 10L));
  }

  private void testSearchProductByName() {

    Merchant merchant = new Merchant("new merchant");
    AssertEqual(SetUtil.set(), merchant.getProductsByName(product.getName()));
    merchant.addProduct(product);
    Assert(SetUtil.inSet(product, merchant.getProductsByName(product.getName())));
  }

  private void testTransferBetweenCustomers() {

    DiscountSystem system = new DiscountSystem();
    system.customerJoins(customer1);
    system.customerJoins(customer2);
    AssertEqual(0L, customer1.GetBalance());
    customer1.deposit(10L);
    AssertEqual(10L, customer1.GetBalance());
    AssertEqual(0L, customer2.GetBalance());
    system.transfer(customer1, customer2, 10L);
    AssertEqual(0L, customer1.GetBalance());
    AssertEqual(10L, customer2.GetBalance());
  }

  public void testInviteCustomer() {

    Customer invitee = new Customer("Invitee User");
    Number balance = customer1.GetBalance();
    DiscountSystem system = new DiscountSystem();
    system.customerJoins(customer1);
    Assert(SetUtil.inSet(customer1, system.customers));
    Assert(!(SetUtil.inSet(invitee, system.customers)));
    system.inviteCustomer(customer1, invitee);
    Assert(SetUtil.inSet(invitee, system.customers));
    AssertEqual(balance.doubleValue() + 5L, customer1.GetBalance());
  }

  public void testInviteMerchant() {

    DiscountSystem system = new DiscountSystem();
    Merchant invitee = new Merchant("Invitee Merchant");
    Number balance = merchant1.GetBalance();
    system.merchantJoins(merchant1);
    Assert(SetUtil.inSet(merchant1.GetName(), MapUtil.dom(system.merchants)));
    Assert(!(SetUtil.inSet(invitee.GetName(), MapUtil.dom(system.merchants))));
    system.inviteMerchant(merchant1, invitee);
    Assert(SetUtil.inSet(invitee.GetName(), MapUtil.dom(system.merchants)));
    AssertEqual(balance.doubleValue() + 5L, merchant1.GetBalance());
  }

  public void testBuyProductWithoutBalance() {

    DiscountSystem system = new DiscountSystem();
    Number customerBalance = customer1.GetBalance();
    Number merchantBalance = merchant1.GetBalance();
    Product perfume = new Product("perfume", 13.5, 10L, 5L);
    Number quantity = perfume.getQuantity();
    Number amount = 1L;
    Number merchantReturn =
        perfume.getPrice().doubleValue()
            * (1L - Utils.divide(perfume.getDiscount().doubleValue(), 100L))
            * (1L - Utils.divide(customer1.getDiscount().doubleValue(), 100L))
            * Utils.divide(merchant1.SystemFee.doubleValue(), 100L)
            * amount.longValue();
    Number customerReturn =
        (perfume.getPrice().doubleValue()
                - perfume.getPrice().doubleValue()
                    * (1L - Utils.divide(perfume.getDiscount().doubleValue(), 100L))
                    * (1L - Utils.divide(customer1.getDiscount().doubleValue(), 100L)))
            * amount.longValue();
    system.merchantJoins(merchant1);
    system.customerJoins(customer1);
    merchant1.addProduct(perfume);
    system.buyProduct(merchant1, customer1, perfume, amount);
    AssertEqual(quantity.longValue() - 1L, perfume.getQuantity());
    AssertEqual(
        merchantBalance.doubleValue() + merchantReturn.doubleValue(), merchant1.GetBalance());
    AssertEqual(
        customerBalance.doubleValue() + customerReturn.doubleValue(), customer1.GetBalance());
  }

  public void testBuyProductUsingBalance() {

    DiscountSystem system = new DiscountSystem();
    Number customerBalance = customer1.GetBalance();
    Number merchantBalance = merchant1.GetBalance();
    Product perfume = new Product("perfume", 100L, 20L, 50L);
    Number quantity = perfume.getQuantity();
    Number amount = 1L;
    Number merchantReturn =
        perfume.getPrice().doubleValue()
            * (1L - Utils.divide(perfume.getDiscount().doubleValue(), 100L))
            * (1L - Utils.divide(customer1.getDiscount().doubleValue(), 100L))
            * Utils.divide(merchant1.SystemFee.doubleValue(), 100L)
            * amount.longValue();
    Number customerReturn =
        (perfume.getPrice().doubleValue()
                - perfume.getPrice().doubleValue()
                    * (1L - Utils.divide(perfume.getDiscount().doubleValue(), 100L))
                    * (1L - Utils.divide(customer1.getDiscount().doubleValue(), 100L)))
            * amount.longValue();
    system.merchantJoins(merchant1);
    system.customerJoins(customer1);
    merchant1.addProduct(perfume);
    system.buyProduct(merchant1, customer1, perfume, amount);
    AssertEqual(quantity.longValue() - 1L, perfume.getQuantity());
    AssertEqual(
        merchantBalance.doubleValue() + merchantReturn.doubleValue(), merchant1.GetBalance());
    AssertEqual(
        customerBalance.doubleValue() + customerReturn.doubleValue(), customer1.GetBalance());
    system.buyProductUsingBalance(merchant1, customer1, perfume, amount);
    AssertEqual(customerReturn, customer1.GetBalance());
    system.buyProduct(merchant1, customer1, perfume, 5L);
    system.buyProductUsingBalance(merchant1, customer1, perfume, amount);
    Assert(customer1.GetBalance().doubleValue() > customerReturn.doubleValue());
  }

  public void testSearchMerchantByName() {

    DiscountSystem system = new DiscountSystem();
    system.merchantJoins(merchant1);
    AssertEqual(system.getMerchantByName("zara").GetName(), merchant1.GetName());
  }

  public void testSearchCustomerByName() {

    DiscountSystem system = new DiscountSystem();
    system.customerJoins(customer1);
    system.customerJoins(customer2);
    for (Iterator iterator_3 = system.getCustomerByName("Rui").iterator(); iterator_3.hasNext(); ) {
      Customer customer = (Customer) iterator_3.next();
      AssertEqual(customer.GetName(), customer1.GetName());
    }
  }

  public static void main() {

    DiscountSystemTest systemTest = new DiscountSystemTest();
    IO.print("Create Customer: ");
    systemTest.testCreateCustomer();
    IO.println("Success");
    IO.print("Create Merchant: ");
    systemTest.testCreateMerchant();
    IO.println("Success");
    IO.print("Customer Joins: ");
    systemTest.testCustomerJoins();
    IO.println("Success");
    IO.print("Merchant Joins: ");
    systemTest.testMerchantJoins();
    IO.println("Success");
    IO.print("Add Product: ");
    systemTest.testAddProduct();
    IO.println("Success");
    IO.print("Remove Product: ");
    systemTest.testRemoveProduct();
    IO.println("Success");
    IO.print("Set Product Discount: ");
    systemTest.testSetProductDiscount();
    IO.println("Success");
    IO.print("Search Product By Name: ");
    systemTest.testSearchProductByName();
    IO.println("Success");
    IO.print("Transfer to Customer: ");
    systemTest.testTransferBetweenCustomers();
    IO.println("Success");
    IO.print("Customer invites Customer and receives a bonus: ");
    systemTest.testInviteCustomer();
    IO.println("Success");
    IO.print("Merchant invites Merchant and receives a bonus: ");
    systemTest.testInviteMerchant();
    IO.println("Success");
    IO.print("Buy Product without discount balance: ");
    systemTest.testBuyProductWithoutBalance();
    IO.println("Success");
    IO.print("Buy Product using current balance: ");
    systemTest.testBuyProductUsingBalance();
    IO.println("Success");
    IO.print("Search Merchant by name: ");
    systemTest.testSearchMerchantByName();
    IO.println("Success");
    IO.print("Search Customer by name: ");
    systemTest.testSearchCustomerByName();
    IO.println("Success");
  }

  public DiscountSystemTest() {}

  public String toString() {

    return "DiscountSystemTest{"
        + "customer1 := "
        + Utils.toString(customer1)
        + ", customer2 := "
        + Utils.toString(customer2)
        + ", merchant1 := "
        + Utils.toString(merchant1)
        + ", product := "
        + Utils.toString(product)
        + "}";
  }
}
