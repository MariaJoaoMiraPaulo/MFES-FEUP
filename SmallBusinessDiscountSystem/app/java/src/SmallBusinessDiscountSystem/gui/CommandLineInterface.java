package SmallBusinessDiscountSystem.gui;

import SmallBusinessDiscountSystem.logic.Customer;
import SmallBusinessDiscountSystem.logic.DiscountSystem;
import SmallBusinessDiscountSystem.logic.Merchant;
import SmallBusinessDiscountSystem.logic.Product;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.VDMSet;

import java.util.Iterator;
import java.util.Scanner;


public class CommandLineInterface {

	DiscountSystem system = new DiscountSystem();
	Scanner scanner = new Scanner(System.in);

	public CommandLineInterface(){}
	
	public static void main(String[] args){
		CommandLineInterface cli = new CommandLineInterface();

		cli.launchMainMenu();

	}

	public void launchMainMenu(){

		System.out.println("---------------------------------------------");
		System.out.println("            Welcome to Boobonus!");
		System.out.println("---------------------------------------------");
		System.out.println("1. Join System");
		System.out.println("2. Merchants");
		System.out.println("3. Customers");
		System.out.println("4. Start to buy");
		System.out.println("5. Merchant Settings");
		System.out.println("6. Transfer Between Customers");
		System.out.println("7. Exit");
		System.out.println("");


		int option = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		switch (option){
			case 1:
				joinSystem();
				break;
			case 2:
				listMerchants();
				break;
			case 3:
				listCustomers();
				break;
			case 4:
				break;
			case 5:
				merchantSettings();
				break;
			case 6:
				transfer();
				break;
			case 7:
				System.exit(1);
				break;
			default:
				launchMainMenu();
				break;
		}

	}

	public void merchantSettings(){

	}

	public void transfer(){
		int i = printCustomers();
		VDMSet customers = system.GetCustomers();

		System.out.println("Who are you? (Type the number right before your name)");
		int sender = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		System.out.println("Who is the receiver?");
		int receiver = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		System.out.println("How much do you want to transfer?");
		float amount = scanner.nextFloat();
		// Skip the newline
		scanner.nextLine();

		System.out.println("Loading...");

		Customer sender_user = (Customer) customers.toArray()[sender-1];
		Customer receiver_user = (Customer) customers.toArray()[receiver-1];

		system.transfer(sender_user,receiver_user,amount);

		System.out.println("Transferation done with success...");

		back();
	}

	public void listCustomers(){

		int i = printCustomers();
		int exit = i;

		System.out.println(exit + ". Back");

		int index = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		if(index == exit )
			launchMainMenu();
		else openUserInfo(index);
	}

	public void listMerchants(){

		int i = printMerchants();

		int exit = i;
		System.out.println(exit + ". Back");

		int index = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		if(index != exit )
			openMerchantInfo(index);
		else launchMainMenu();

	}

	public void openMerchantInfo(int index){
		VDMSet merchants = system.GetMerchants();
		Merchant merchant = (Merchant) merchants.toArray()[index-1];

		System.out.println("---------------------------------------------");
		System.out.println("                  "+ merchant.GetName());
		System.out.println(" Balance: "+ merchant.GetBalance());
		System.out.println("---------------------------------------------");
		System.out.println("");
		System.out.println(" Products:");

		VDMSet products = merchant.GetProducts();

		//List products
		for (Iterator<Product> iter = products.iterator(); iter.hasNext(); ) {
			Product product = iter.next();
			System.out.println("-> " + product.getName());
		}

		back();
	}

	public void openUserInfo(int index){
		VDMSet customers = system.GetCustomers();
		Customer customer = (Customer) customers.toArray()[index-1];

		System.out.println("---------------------------------------------");
		System.out.println("                  "+ customer.GetName());
		System.out.println(" Card Balance: "+ customer.GetBalance());
		System.out.println(" Card Discount: "+ customer.getDiscount());
		System.out.println("---------------------------------------------");
		System.out.println("");

		back();
	}


	public void joinSystem(){
		System.out.println("---------------------------------------------");
		System.out.println("                 What are you?               ");
		System.out.println("---------------------------------------------");
		System.out.println("1. Merchant");
		System.out.println("2. Costumer");
		System.out.println("3. Back");
		System.out.println("");


		int option = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		switch (option){
			case 1:
				launchMerchantMenu();
				break;
			case 2:
				launchCustomerMenu();
				break;
			case 3:
				launchMainMenu();
				break;
			default:
				launchMainMenu();
				break;
		}

	}

	public void launchMerchantMenu(){

		System.out.println("What is the name of your company?");

		String name = scanner.nextLine();
		Merchant merchant = new Merchant(name);
		system.merchantJoins(merchant);

		System.out.println("Successfully added to system. Press Enter to go back!");

		scanner.nextLine();

		launchMainMenu();
	}

	public void launchCustomerMenu(){

		System.out.println("What is your name?");

		String name = scanner.nextLine();
		Customer customer = new Customer(name);
		system.customerJoins(customer);

		System.out.println("Successfully added to system. Press Enter to go back!");

		scanner.nextLine();

		launchMainMenu();
	}

	public void back(){
		System.out.println("1. Back");

		int input = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		if(input == 1 )
			launchMainMenu();
	}

	public int printCustomers(){
		System.out.println("---------------------------------------------");
		System.out.println("                  Customers                  ");
		System.out.println("---------------------------------------------");
		VDMSet customers = system.GetCustomers();
		int i = 1;

		for (Iterator<Customer> iter = customers.iterator(); iter.hasNext(); ) {
			Customer customer = iter.next();
			System.out.println(i + ". " + customer.GetName());
			i++;
		}
		return i;
	}

	public int printMerchants(){
		System.out.println("---------------------------------------------");
		System.out.println("                  Merchants                  ");
		System.out.println("---------------------------------------------");
		VDMSet merchants = system.GetMerchants();
		int i = 1;

		for (Iterator<Merchant> iter = merchants.iterator(); iter.hasNext(); ) {
			Merchant merchant = iter.next();
			System.out.println(i + ". " + merchant.GetName());
			i++;

		}
		return i;
	}

}