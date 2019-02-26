
package www;
import java.util.Scanner;
public class IO {
    public Shop shop;
    public String command;
    protected Scanner s = new Scanner(System.in);
    public Date currentdate;
    public void run(){
        while (!command.equals("quit")){
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
                case("printo"): 
                break;
                case("printp"):
                break;
                case("printt"):
                break;
                case("printr"):
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
    }
    public RepairLevel hlevel(String type){
        switch(type){
            case("silver"): return RepairLevel.silver;
            case("gold"): return RepairLevel.gold;
            case("platinum"): return RepairLevel.platinum;
        }
        return null;
    }
    public Date hdate(String date){
        return null;
    }
}
