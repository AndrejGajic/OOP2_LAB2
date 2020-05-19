package raspored;

public class Sema {
	private static Vreme inkrement;
	static {
		try {
		inkrement = new Vreme(0, 15);
		} catch(GVreme g) {
			
		}
	}
	private static class Node {
		Sadrzaj s;
		Node next;
		public Node(Sadrzaj s1) {
			s = s1;
			next = null;
		}
	}
	private Node head, tail;
	private String naziv;
	private Vreme pocetak, kraj;
	private int n;
	public Sema(String naziv, Vreme pocetak, Vreme kraj) {
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
		head = tail = null;
		n = 0;
	}
	public Sema(String naziv) throws GVreme{
		this(naziv, new Vreme(8, 0), new Vreme(22, 0));
	}
	public Sema dodaj(Sadrzaj s) throws GDodaj, GVreme{
		if(this.kraj.dohvMin() < s.kraj().dohvMin()) throw new GDodaj(s);
		if(this.pocetak.dohvMin() > s.dohvPocetak().dohvMin()) s.postPocetak(this.pocetak);
		Node node = new Node(s);
		if(head == null) {
			head = tail = node;
			n++;
		} else {
			Node prev = null;
			Node temp = head;
			while(temp != null) {
				while(s.dohvPocetak().dohvMin() < temp.s.dohvPocetak().dohvMin()) {
					if(s.preklapaSe(temp.s) != null) {
						s.pomeri(inkrement);
					} 
					else {
						boolean flag = false;
						Node temp2 = head;
						while(temp2 != temp) {
							if(s.preklapaSe(temp2.s) != null) {
								s.pomeri(inkrement);
								flag = true;
								break;
							}
							temp2 = temp2.next;
						}
						if(!flag) {
							if(prev == null) {
								head = node;
							} else {
								prev.next = node;
							}
							node.next = temp;
							n++;
							return this;
						}
					}
				}
				prev = temp;
				temp = temp.next;
			}
			
			while(true) {
				if(this.kraj.dohvMin() < s.kraj().dohvMin()) throw new GDodaj(s);
				temp = head;
				boolean flag = false;
				while(temp != null) {
					if(temp.s.preklapaSe(s) != null) {
						flag = true;
						s.pomeri(inkrement);
						break;
					}
					temp = temp.next;
				}
				if(!flag) {
					tail.next = node;
					tail = node;
					n++;
					return this;
				}
			}
		}
		return this;
	}
	
	public int dohvBroj() {
		return n;
	}
	public double zauzetost() throws GVreme {
		if(head == null) return 0;
		double d = this.kraj.dohvMin() - this.pocetak.dohvMin();
		double sum = 0;
		Node temp = head;
		while(temp != null) {
			sum = sum + (temp.s.dohvTrajanje().dohvMin());
			if(temp.s instanceof Ponavljajuci) {
				Ponavljajuci p = (Ponavljajuci)temp.s;
				Vreme pocetno = p.dohvPocetak();
				Vreme poslednje = p.poslednjePonavljanje();
				while(!pocetno.equals(poslednje)) {
					pocetno = Vreme.saberi(pocetno, p.dohvPonavljanje());
					if(Vreme.saberi(pocetno, p.dohvTrajanje()).dohvMin() > this.kraj.dohvMin()) break;
					sum = sum + (temp.s.dohvTrajanje().dohvMin());
				}
			}
			temp = temp.next;
		}
		return (sum / d) * 100;
	}
	
	public Sadrzaj dohvSadrzaj(int poz) throws GIndeks{
		if((poz < 0) || (poz >= n)) throw new GIndeks(poz);
		Node temp = head;
		for(int i = 0; i < poz; i++) temp = temp.next;
		return temp.s;
	}
	
	@Override
	public String toString() {
		if(head == null) return "Lista je prazna!";
		StringBuilder sb = new StringBuilder(naziv + ":" + pocetak + " - " + kraj + "\n");
		Node temp = head;
		while(temp != null) {
			sb.append(temp.s);
			if(temp != tail) sb.append("\n");
			temp = temp.next;
		}
		return sb.append("\n").toString();
	}
	
	
	
	
	
}
