package pitanja;



public class ZbirkaPitanja {
	
	static class Node {
		Pitanje p;
		Node next;
		Node(Pitanje p1) {
			p = p1;
			next = null;
		}
	}
	
	private Node head, tail;
	private int n;
	public ZbirkaPitanja() {
		head = null;
		tail = null;
		n = 0;
	}
	public void dodaj(Pitanje p1) {
		n++;
		Node novi = new Node(p1);
		if(head == null) {
			head = tail = novi;
		} else {
			tail.next = novi;
			tail = novi;
		}
	}
	public Pitanje dohvati(int poz) throws GNemaPitanja{
		if((poz < 0) || (poz >=this.dohvBrojPitanja())) throw new GNemaPitanja();
		IteratorPitanja pom = new IteratorPitanja(head);
		int cnt = 0;
		while(cnt < poz) {
			pom.sledece();
			cnt++;
		}
		return pom.dohvati();
	}
	public int dohvBrojPitanja() {
		return n;
	}
	public IteratorPitanja iterator() {
		IteratorPitanja iter = new IteratorPitanja(head);
		return iter;
	}
	@Override
	public String toString() {
		if(head == null) return "Lista je prazna!";
		StringBuilder sb = new StringBuilder();
		IteratorPitanja pom = new IteratorPitanja(head);
		int cnt = 0;
		while(pom.postoji()) {
			try {
			sb.append(pom.dohvati());
			if(++cnt < n) sb.append("\n");
			pom.sledece();
			} catch(GNemaPitanja g) {
				
			}
		}
		
		return sb.toString();
	}
}
