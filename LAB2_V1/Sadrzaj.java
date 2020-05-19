package raspored;

public abstract class Sadrzaj {
	
	private String naziv;
	private Vreme trajanje;
	private Vreme pocetak;
	
	Vreme kraj() throws GVreme{
		Vreme v = Vreme.saberi(pocetak, trajanje);
		return v;
	}
	
	public Sadrzaj(String naziv1, Vreme trajanje1) {
		naziv = naziv1;
		trajanje = trajanje1;
		pocetak = new Vreme();
	}
	
	public String dohvNaziv() {
		return naziv;
	}
	public Vreme dohvTrajanje() {
		return trajanje;
	}
	public Vreme dohvPocetak() {
		return pocetak;
	}
	public void postPocetak(Vreme v) {
		pocetak = v;
	}
	
	public Vreme preklapaSe(Sadrzaj s) throws GVreme{
		if(pocetak.dohvMin() < s.kraj().dohvMin() && s.pocetak.dohvMin() < kraj().dohvMin()) {
			if(pocetak.dohvMin() > s.pocetak.dohvMin() && pocetak.dohvMin() < s.kraj().dohvMin()) {
				return this.pocetak;
			} else return s.pocetak;
		}
		return null;
	}
	
	
	public void pomeri(Vreme v) throws GVreme {
		pocetak = Vreme.saberi(pocetak, v);
	}
	
	public abstract char dohvOznaku();
	@Override
	public String toString() {
		try {
		return dohvOznaku() + ", " + dohvNaziv() + " | " + dohvPocetak() + " - " + kraj();
		} catch(GVreme g) {return ""; }
	}
	
	
}
