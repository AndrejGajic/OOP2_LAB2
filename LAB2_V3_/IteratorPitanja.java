package pitanja;

public class IteratorPitanja {
	private ZbirkaPitanja.Node iter;
	private Pitanje p;
	public IteratorPitanja(ZbirkaPitanja.Node node) {
		iter = node;
		p = iter.p;
	}
	public boolean postoji() {
		if(iter == null) return false;
		else return true;
	}
	public Pitanje dohvati() throws GNemaPitanja{
		if(p == null) throw new GNemaPitanja();
		return p;
	}
	public void sledece() throws GNemaPitanja{
		iter = iter.next;
		if(iter == null) throw new GNemaPitanja();
		this.p = iter.p;
	}
	
	
	
}
