/**
 * on arrows
 * > marks user input (>addrp brand gold 2 3)
 * → marks non-error output (→added customer Joe Johnson #2512)
 * » marks error (»invalid command)
 */


package www;
import java.util.*;

public class IO {
	private Shop shop;
	private Scanner s;
	private Date currentdate;
	private ArrayList<String> validCommands;	//a list of valid commands to be used for suggesting alternatives
	
	IO() {
		shop = new Shop();
		s = new Scanner(System.in);
		validCommands = new ArrayList<>();
		validCommands.add("quit");
		validCommands.add("help");
		validCommands.add("addrp");
		validCommands.add("addc");
		validCommands.add("addo");
		validCommands.add("addp");
		validCommands.add("comp");
		validCommands.add("printrp");
		validCommands.add("printcnum");
		validCommands.add("printcname");
		validCommands.add("printo");
		validCommands.add("printp");
		validCommands.add("printt");
		validCommands.add("printr");
		validCommands.add("prints");
		validCommands.add("readc");
		validCommands.add("savebs");
		validCommands.add("restorebs");
	}
	
	//ensures that the correct number of args is passed
	//canHaveMore = true will make it so that you can have more than length (for comments)
	//prints an error message if bad
	private boolean checkForNumArgs(String[] args, int length, boolean canHaveMore) {
		boolean result = (args.length == length) || (args.length > length && canHaveMore);
		if (!result) {
			String manyOrFew = args.length > length ? "many" : "few";
			System.out.println("»Too " + manyOrFew + " parameters provided (expected " + (length - 1) + ", received " + (args.length - 1) + ")");
		}
		return result;
	}
	//suggests strings similar to "string" from "compareWith", with similarity being defined by "maxLev"
	//the higher the maxLev, the less picky the method will be
	private String getSimilarStrings(String string, ArrayList<String> compareWith, int maxLev) {
		ArrayList<String> brands = compareWith;
		ArrayList<String> similarBrands = new ArrayList<>();
		//loop through the shop's brands, note all the similar ones
		for (int i = 0; i < brands.size(); ++i) {
			if (Support.levenshtein(string, brands.get(i)) <= maxLev) {
				similarBrands.add(brands.get(i));
			}
		}
		//construct into a readable string
		if (similarBrands.isEmpty()) {
			return "";
		}
		String toRet = "Did you mean ";
		for (int i = 0; i < similarBrands.size(); ++i) {
			toRet += "\"" + similarBrands.get(i) + "\"";
			if (i == similarBrands.size() - 1) {
				toRet += "?";
			} else if (i == similarBrands.size() - 2) {
				toRet += " or ";
			} else {
				toRet += ", ";
			}
		}
		return toRet;
	}
	//this takes a brand and returns a warning if it does not exist in the shop.
	private String getBrandAdditionWarning(String brand, boolean add) {
		if (!shop.doesTableContainBrand(brand)) {
			String initialMessage = (!add ? "brand \"" + brand + "\" does not exist. " : "you are adding a new brand, \"" + brand + "\". ");
			String warning = initialMessage + getSimilarStrings(brand, shop.getAllBrands(), 3);
			return warning;
		}
		return "";
	}
	
	//this function takes a command and executes it. If it is restoring a save file, 
	//isRestoreMode is set to true and it can execute the rncn and rnom commands
	//returns true on "quit", false on anything else. This lets it communicate to run() when it is time to exit.
	private boolean executeCommand(String command, boolean isRestoreMode) {
		String[] args = Support.splitStringIntoParts(command);	//args[0] is the name of the command, rest is arguments
		switch(args[0]){
			case("quit"):
				return true;
			case("help"):
				shop.help();
				break;
			case("addrp"):
				//check for correct number of arguments
				if (!checkForNumArgs(args, 5, false)) {
					break;
				}
				//retrieve brand, cancel and print warning if it is invalid
				String brand = args[1];
				String brandWarning = getBrandAdditionWarning(brand, true);
				if (!brandWarning.equals("")) {
					System.out.println("→note: " + brandWarning);
					//break;	//there are situations where you want to break for an invalid brand but this is CERTAINLY not one of them...
				}
				//retrieve level, check if it is correct
				RepairLevel level = stringToLevel(args[2]);
				if (level == null) {
					break;
				}
				//retrieve price, check if it is correct
				Integer price = stringToInt(args[3]);
				if (price == null) {
					break;
				}
				//retrieve days, check if it is correct
				Integer days = stringToInt(args[4]);
				if (days == null) {
					break;
				}
				shop.addrp(brand, level, price, days);
				break;
			case("addc"):
				if (!checkForNumArgs(args, 3, false)) {
					break;
				}
				shop.addc(args[1], args[2]);
				break;
			case("addo"): 
				System.out.println("UNIMPLEMENTED");
//				shop.addo(Integer.parseInt(Support.splitStringIntoParts(command)[1]), Support.splitStringIntoParts(command)[2], Support.splitStringIntoParts(command)[3], Support.splitStringIntoParts(command)[4], Support.splitStringIntoParts(command)[5]);
				break;
			case("addp"): 
				System.out.println("UNIMPLEMENTED");
//				shop.addp(Integer.parseInt(Support.splitStringIntoParts(command)[1]), Support.splitStringIntoParts(command)[2],Integer.parseInt(Support.splitStringIntoParts(command)[3]));
				break;
			case("comp"):
				System.out.println("UNIMPLEMENTED");
				break;
			case("printrp"): 
				shop.printrp();
				break;
			case("printcnum"): 
				shop.printcnum();
				break;
			case("printcname"): 
				shop.printcname();
				break;
			case("printo"): 
				shop.printo();
				break;
			case("printp"): 
				shop.printp();
				break;
			case("printt"): 
				shop.printt();
				break;
			case("printr"): 
				shop.printr();
				break;
			case("prints"):
				System.out.println("UNIMPLEMENTED");
				break;
			case("readc"):
				System.out.println("UNIMPLEMENTED");
				break;
			case("savebs"):
				System.out.println("UNIMPLEMENTED");
				break;
			case("restorebs"):
				System.out.println("UNIMPLEMENTED");
				break;
			default:
				System.out.println("»unknown command. Type \"help\" for help. " + getSimilarStrings(args[0], validCommands, 2));
				break;
		}
		return false;
	}
	
	//this function waits for the user to input a command and then returns it
	private String getCommand() {
		System.out.print(">");
		return s.nextLine();
	}
	
	public void run(){
		boolean quit = false;	//quit flag, set to true when "quit" is entered
		while (!quit){
			String command = getCommand();	//wait for user to input a command and set command to that
			quit = executeCommand(command, false);	//execute the command, set 'quit' flag to true if executeCommand() returns true (which happens when user inputs "quit")
		}
		System.out.println("\nQuitting WWW...");
	}
	//converts a string to a repair level, returns null and prints error if invalid
	private RepairLevel stringToLevel(String type){
		switch(type){
			case("silver"): return RepairLevel.silver;
			case("gold"): return RepairLevel.gold;
			case("platinum"): return RepairLevel.platinum;
		}
		System.out.println("»\"" + type + "\" is not a valid level (valid levels are \"silver\", \"gold\", or \"platinum\")");
		return null;
	}
	//converts a string to a date, returns null and prints error if invalid
	private Date stringToDate(String date){
		//if the string is of the wrong length, error
		if (date.length() != 8) {
			System.out.println("»\"" + date + "\" is not a valid date (a date has 8 digits, you entered " + date.length() + ")");
			return null;
		}
		//split string into month, day, year
		String sMM = date.substring(0, 2);	//MM is first two chars
		String sDD = date.substring(2, 4);	//DD is second two chars
		String sYYYY = date.substring(3);	//YYYY is last 4 chars
		//convert substrings to ints
		Integer MM = stringToInt(sMM);
		Integer DD = stringToInt(sDD);
		Integer YYYY = stringToInt(sYYYY);
		//if those are invalid ints, error
		if (MM == null || DD == null || YYYY == null) {
			System.out.println("»\"" + date + "\" is not a valid date (a date contains only numbers)");
			return null;
		}
		//return the date
		Date d = new Date(MM, DD, YYYY);
		return d;
	}
	//converts int to string. returns null if string is not convertable (like "iaga")
	private Integer stringToInt(String string) {
		//see if string can be parsed to an int
		try {
			Integer.parseInt(string);
		} catch(NumberFormatException | NullPointerException e) {
			//if it can't, return null and warn
			System.out.println("»\"" + string + "\" is not a valid number");
			return null;
		}
		//else, return the int
		Integer toRet = Integer.parseInt(string);
		return toRet;
	}
}
