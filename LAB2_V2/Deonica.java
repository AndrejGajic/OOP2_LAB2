package karting;

public class Deonica implements Cloneable {
	private class Node {
		Specificnost s;
		Node next;
		public Node(Specificnost s1) {
			s = s1;
			next = null;
		}
	}
	private int n;
	private Node head, tail;
	private double duzina;
	public Deonica(double duzina) {
		this.duzina = duzina;
		n = 0;
		head = null;
		tail = null;
	}
	
	public double dohvDuzinu() {
		return duzina;
	}
	
	public Deonica dodajSpecificnost(Specificnost spec) {
		n++;
		Node novi = new Node(spec);
		if(head == null) {
			head = tail = novi;
		} else {
			tail.next = novi;
			tail = novi;
		}
		return this;
	}
	
	public Specificnost izbaciSpecificnost(int ID) {
		if(head == null) return null;
		if(head.s.dohvatiId() == ID) {
			n--;
			Specificnost s = head.s;
			head = head.next;
			if(head == null) tail = null;
			return s;
		}
		Node pom = head;
		Node prev = null;
		while(pom.s.dohvatiId() != ID) {
			prev = pom;
			pom = pom.next;
			if(pom == null) return null;
		}
		prev.next = pom.next;
		if(tail == pom) tail = prev;
		Specificnost s = pom.s;
		n--;
		return s;
	}
	
	public Specificnost dohvSpecificnost(int index) {
		if((index < 0) || (index >= n)) return null;
		Node pom = head;
		for(int i = 0; i < index; i++) pom = pom.next;
		return pom.s;
	}
	public int dohvBrojSpecificnosti() {
		return n;
	}
	
	@Override
	public Deonica clone() {
		try {
			Deonica kopija = (Deonica)super.clone();
			kopija.head = new Node(this.head.s.clone());
			kopija.tail = kopija.head;
			Node iter = this.head.next;
			while(iter != null) {
				kopija.tail.next = new Node(iter.s.clone());
				kopija.tail = kopija.tail.next;
				iter = iter.next;
			}
			return kopija;
		} catch(CloneNotSupportedException e) {e.printStackTrace(); return null; }
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("deonica(" + this.duzina + "m)");
		if(n != 0) {
			Node pom = head;
			while(pom != null) {
				sb.append(pom.s);
				pom = pom.next;
			}
		}
		
		return sb.toString();
	}
	
	
	
}