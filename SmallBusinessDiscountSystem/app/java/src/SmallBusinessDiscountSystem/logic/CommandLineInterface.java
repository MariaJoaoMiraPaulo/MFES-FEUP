package SmallBusinessDiscountSystem.logic;

import SmallBusinessDiscountSystem.gui.DiscountSystem;
import SmallBusinessDiscountSystem.gui.Merchant;


public class CommandLineInterface {

	public CommandLineInterface(){}
	
	public static void main(String[] args){
		CommandLineInterface cli = new CommandLineInterface();

		launchMainMenu();

	}

	public static void launchMainMenu(){

		System.out.println("---------------------------------------------");
		System.out.println("            Welcome to Boobonus!");
		System.out.println("---------------------------------------------");
		System.out.println("What are you?");
		System.out.println("1. Merchant");
		System.out.println("2. Costumer");
		System.out.println("3. Exit");

		int option = Integer.parseInt(System.console().readLine());

		switch (option){
			case 1:
				launchMerchantMenu();
				break;
			case 2:
				//launchCustomerMenu();
				break;
			case 3:
				System.exit(1);
				break;
			default:
				launchMainMenu();
				break;
		}

	}

	public static void launchMerchantMenu(){

		DiscountSystem system = new DiscountSystem();

		System.out.println("What is the name of your company?");
		String name = System.console().readLine();

		Merchant merchant = new Merchant(name);


	}

}