package www;

public class Www {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriceTable p = new PriceTable();
		Shop s = new Shop();
		s.addrp("pop", RepairLevel.platinum, 13, 9);
		s.printrp();
		s.addrp("probike", RepairLevel.silver, 10, 3);
		s.addrp("probike", RepairLevel.gold, 20, 6);
		s.addrp("probike", RepairLevel.platinum, 13, 9);
		s.printrp();
		s.addrp("probike", RepairLevel.silver, 1, 90);
		s.addrp("probike", RepairLevel.platinum, 130, 9);
		s.printrp();
		s.addrp("Kraftfahrzeughaftpflichtversicherung", RepairLevel.silver, 1, 1);
		s.addrp("Kraftfahrzeughaftpflichtversicherung", RepairLevel.gold, 2, 2);
		s.addrp("Kraftfahrzeughaftpflichtversicherung", RepairLevel.platinum, 3, 3);
		s.addrp("bop", RepairLevel.silver, 10, 10);
		s.addrp("bop", RepairLevel.gold, 10, 20);
		s.addrp("bop", RepairLevel.platinum, 10, 21);
		s.printrp();
		Date d = new Date(12, 15, 1999);
		Payment r = new Payment(d, 24, 100);
//		Order o = new Order(d, 24, p.getRepairPrice("bop", RepairLevel.platinum), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero sapien, venenatis at dictum nec, mattis at risus. Sed hendrerit ");
//		o.complete(d);
		System.out.println(r);
//		System.out.println(o);
		s.addrp("megabike", RepairLevel.silver, 100, 5);
		s.addrp("megabike", RepairLevel.gold, 150, 50);
		s.addrp("megabike", RepairLevel.platinum, 200, 90);
		s.addrp("bikergang", RepairLevel.silver, 10, 1);
		s.addrp("bikergang", RepairLevel.gold, 20, 1);
		s.addrp("bikergang", RepairLevel.platinum, 30, 1);
		s.addrp("hikergang", RepairLevel.platinum, 0, 0);
		s.help();
		s.printrp();
		System.out.println(s.getSavefile());
	}
}
