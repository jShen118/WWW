
package www;
import java.util.*;

public class IO {
	private Shop shop;
	private Scanner s;
	private Date currentdate;
	private ArrayList<String> validCommands;	//a list of valid commands to be used for suggesting alternatives
	
	IO() {
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
	
	//this function takes a command and executes it. If it is restoring a save file, 
	//isRestoreMode is set to true and it can execute the rncn and rnom commands
	//returns true on "quit", false on anything else. This lets it communicate to run() when it is time to exit.
	private boolean executeCommand(String command, boolean isRestoreMode) {
		switch(Support.splitStringIntoParts(command)[0]){
			case("help"): shop.help();
			break;
			case("addrp"): shop.addrp(Support.splitStringIntoParts(command)[1], hlevel(Support.splitStringIntoParts(command)[2]),Integer.parseInt(Support.splitStringIntoParts(command)[3]), Integer.parseInt(Support.splitStringIntoParts(command)[4]));
			break;
			case("addc"): shop.addc(Support.splitStringIntoParts(command)[1], Support.splitStringIntoParts(command)[2]);
			break;
			case("addo"): shop.addo(Integer.parseInt(Support.splitStringIntoParts(command)[1]), Support.splitStringIntoParts(command)[2], Support.splitStringIntoParts(command)[3], Support.splitStringIntoParts(command)[4], Support.splitStringIntoParts(command)[5]);
			break;
			case("addp"): shop.addp(Integer.parseInt(Support.splitStringIntoParts(command)[1]), Support.splitStringIntoParts(command)[2],Integer.parseInt(Support.splitStringIntoParts(command)[3]));
			break;
			case("comp"):
			break;
			case("printrp"): shop.printrp();
			break;
			case("printcnum"): shop.printcnum();
			break;
			case("printcname"): shop.printcname();
			break;
			case("printo"): shop.printo();
			break;
			case("printp"): shop.printp();
			break;
			case("printt"): shop.printt();
			break;
			case("printr"): shop.printr();
			break;
			case("prints"):
			break;
			case("readc"):
			break;
			case("savebs"):
			break;
			case("restorebs"):
			break;
		}
	}
	
	//this function waits for the user to input a command and then returns it
	private String getCommand() {
		System.out.print("\n>");
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
			//if it can't, return null
			return null;
		}
		//else, return the int
		Integer toRet = Integer.parseInt(string);
		return toRet;
	}
}
