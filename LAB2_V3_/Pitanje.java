package pitanja;

public class Pitanje implements Cloneable {
	
	private String tekst;
	private int poeni;
	private double tezina;
	private static int staticID = 0;
	private int ID = ++staticID;
	
	public Pitanje(String tekst1, int poeni1, double tezina1) {
		tekst = tekst1;
		poeni = poeni1;
		if(tezina1 < 1) tezina1 = 1;
		if(tezina1 > 10) tezina1 = 10;
		tezina = tezina1;
	}
	public String dohvTekst() {
		return tekst;
	}
	public int dohvPoene() {
		return poeni;
	}
	public double dohvTezinu() {
		return tezina;
	}
	@Override
	public Pitanje clone()  {
		try {
		Pitanje p = (Pitanje)super.clone();
		return p;
		} catch(CloneNotSupportedException e) { return null; }
		
	}
	
	@Override
	public String toString() {
		return "Pitanje " + ID + ": " + tekst;
	}
}
