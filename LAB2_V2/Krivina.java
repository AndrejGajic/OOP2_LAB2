package karting;

public class Krivina extends Specificnost implements Cloneable {
	
	private double maksBrzina;
	
	public Krivina(double V) {
		maksBrzina = V;
	}
	
	public double dohvMaksBrzinu() {
		return maksBrzina;
	}

	@Override
	public void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if(!(o instanceof Vozilo)) throw new GNeodgovarajuciObjekat();
		Vozilo v = (Vozilo)o;
		v.postPomMaksBrzinu(v.dohvMaksBrzinu());
		double x = v.dohvUpravljivost() * this.maksBrzina;
		if(x < v.dohvMaksBrzinu()) {
			v.postMaksBrzinu(x);
		}
	}

	@Override
	public void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if(!(o instanceof Vozilo)) throw new GNeodgovarajuciObjekat();
		Vozilo v = (Vozilo)o;
		v.postMaksBrzinu(v.dohvPomMaxBrzinu());
	}
	
	@Override
	public Krivina clone(){
		Krivina kopija = (Krivina)super.clone();
		return kopija;
	}
	@Override
	public String toString() {
		return "K" + maksBrzina;
	}

}
