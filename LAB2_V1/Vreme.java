package raspored;

public class Vreme {
	
	private int h;
	private int m;
	
	public Vreme(int h1, int m1) throws GVreme {
		if((h1 < 0) || (m1 < 0) || (h1 > 23) || (m1 > 59) || (m1 % 15 != 0)) throw new GVreme();
		h = h1;
		m = m1;
	}
	public Vreme(){
		h = 0;
		m = 0;
	}
	public int dohvMin() {
		return h * 60 + m;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Vreme)) return false;
		Vreme v = (Vreme)o;
		return(this.dohvMin() == v.dohvMin());
	}
	
	public static Vreme saberi(Vreme v1, Vreme v2) throws GVreme{
		int min = v1.dohvMin() + v2.dohvMin();
		return new Vreme(min / 60, min % 60);
		
	}
	
	@Override
	public String toString() {
		return "(" + h + ":" + m + ")";
	}
}
