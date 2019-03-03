package www;

public class Www {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriceTable p = new PriceTable();
		Shop s = new Shop();
//		s.addrp("pop", RepairLevel.platinum, 13, 9);
//		s.printrp();
//		s.addrp("probike", RepairLevel.silver, 10, 3);
//		s.addrp("probike", RepairLevel.gold, 20, 6);
//		s.addrp("probike", RepairLevel.platinum, 13, 9);
//		s.printrp();
//		s.addrp("probike", RepairLevel.silver, 1, 90);
//		s.addrp("probike", RepairLevel.platinum, 130, 9);
//		s.printrp();
//		s.addrp("Kraftfahrzeughaftpflichtversicherung", RepairLevel.silver, 1, 1);
//		s.addrp("Kraftfahrzeughaftpflichtversicherung", RepairLevel.gold, 2, 2);
//		s.addrp("Kraftfahrzeughaftpflichtversicherung", RepairLevel.platinum, 3, 3);
//		s.addrp("bop", RepairLevel.silver, 10, 10);
//		s.addrp("bop", RepairLevel.gold, 10, 20);
//		s.addrp("bop", RepairLevel.platinum, 10, 21);
//		s.printrp();
//		Date d = new Date(12, 15, 1999);
//		Payment r = new Payment(d, 24, 100);
////		Order o = new Order(d, 24, p.getRepairPrice("bop", RepairLevel.platinum), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero sapien, venenatis at dictum nec, mattis at risus. Sed hendrerit ");
//		s.addc("gaius", "caesar");
//		s.addp(0, d, 100);
//		s.addo(0, d, "bop", RepairLevel.silver, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero sapien, venenatis at dictum nec, mattis at risus. Sed hendrerit ");
////		o.complete(d);
//		System.out.println(r);
////		System.out.println(o);
//		s.addrp("megabike", RepairLevel.silver, 100, 5);
//		s.addrp("megabike", RepairLevel.gold, 150, 50);
//		s.addrp("megabike", RepairLevel.platinum, 200, 90);
//		s.addrp("bikergang", RepairLevel.silver, 10, 1);
//		s.addrp("bikergang", RepairLevel.gold, 20, 1);
//		s.addrp("bikergang", RepairLevel.platinum, 30, 1);
//		s.addrp("hikergang", RepairLevel.platinum, 0, 0);
//		
//		s.help();
//		s.printrp();
//		System.out.println("Commands to recreate shop:\n" + s.getSavefile());
//		System.out.println(Support.levenshtein("kitten", "kitten"));	//same words, should be 0
//		System.out.println(Support.levenshtein("kitten", "mitten"));	//very similar words, should be 1
//		System.out.println(Support.levenshtein("kitten", "sitting"));	//kinda similar words, should be 3
//		System.out.println(Support.levenshtein("kitten", "britain"));	//not really similar, should be 4
//		System.out.println(Support.levenshtein("abcdefg", "hijklmn"));	//nothing similar about them at all, should be 7
		IO i = new IO();
		i.run();
                s.addrp("Trek", RepairLevel.silver, 40, 4);
                s.addrp("Trek", RepairLevel.gold, 60, 7);
                s.addrp("Trek", RepairLevel.platinum, 100, 10);
                s.addrp("Cannondale", RepairLevel.silver, 40, 4);
                s.addrp("Cannondale", RepairLevel.gold, 60, 7);
                s.addrp("Cannondale", RepairLevel.platinum, 100, 10);
                s.addrp("Salsa", RepairLevel.silver, 50, 4);
                s.addrp("Salsa", RepairLevel.gold, 70, 7);
                s.addrp("Salsa", RepairLevel.platinum, 115, 10);
                s.addrp("Jamis", RepairLevel.silver, 60, 4);
                s.addrp("Jamis", RepairLevel.gold, 75, 7);
                s.addrp("Jamis", RepairLevel.platinum, 120, 10);
                
                s.addc("John", "Smith");
                s.addo(0, new Date(3, 3, 2019), "Trek", RepairLevel.silver, "no comment");
                s.addo(0, new Date(3, 3, 2019), "CannonDale", RepairLevel.gold, "no comment");
                s.addo(0, new Date(3, 3, 2019), "Salsa", RepairLevel.platinum, "no comment");
                s.addc("Susan", "Apples");
                s.addo(1, new Date(3, 3, 2019), "Jamis", RepairLevel.silver, "no comment");
                s.addo(1, new Date(3, 3, 2019), "Trek", RepairLevel.gold, "no comment");
                s.addo(1, new Date(3, 3, 2019), "CannonDale", RepairLevel.platinum, "no comment");
                s.addc("Jack", "Johnson");
                s.addo(2, new Date(3, 3, 2019), "Salsa", RepairLevel.silver, "no comment");
                s.addo(2, new Date(3, 3, 2019), "Jamis", RepairLevel.gold, "no comment");
                s.addo(2, new Date(3, 3, 2019), "Trek", RepairLevel.platinum, "no comment");
                s.addc("Jill", "Johnson");
                s.addo(3, new Date(3, 3, 2019), "Cannondale", RepairLevel.silver, "no comment");
                s.addo(3, new Date(3, 3, 2019), "Salsa", RepairLevel.gold, "no comment");
                s.addo(3, new Date(3, 3, 2019), "Jamis", RepairLevel.platinum, "no comment");
                
                s.printcnum();
                s.printcname();
                s.printo();
                s.printp();
                s.printr();
                s.printt();
                //s.prints();
	}
}
