package SmallBusinessDiscountSystem.gui;

import SmallBusinessDiscountSystem.logic.Customer;
import SmallBusinessDiscountSystem.logic.DiscountSystem;
import SmallBusinessDiscountSystem.logic.Merchant;
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
		System.out.println("6. Exit");
		System.out.println("");


		int option = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		switch (option){
			case 1:
				joinSystemMenu();
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
				break;
			case 6:
				System.exit(1);
				break;
			default:
				launchMainMenu();
				break;
		}

	}

	public void listCustomers(){
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
	}

	public void listMerchants(){
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
	}

	public void joinSystemMenu(){
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

}