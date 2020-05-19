package karting;

public abstract class Specificnost implements Cloneable{

	protected static int staticID = 0;
	protected int ID = ++staticID;
	
	
	public int dohvatiId() {
		return ID;
	}
	public abstract void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat;
	public abstract void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat;
	
	public Specificnost clone() {
		try {
			Specificnost kopija = (Specificnost)super.clone();
			kopija.ID = ++staticID;
			return kopija;
		} catch(CloneNotSupportedException e) {e.printStackTrace(); return null; }
	} 
	
}
