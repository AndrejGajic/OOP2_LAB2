package karting;

public class Vozilo {

	private double maksBrzina;
	private double ubrzanje;
	private double upravljivost;
	private String ime;
	private double trenutnaBrzina;
	private double pomMaksBrzina;
	
	
	private double duzinaPuta(double V0, double t, double a) {
		return (V0 * t) + (a * Math.pow(t, 2)) / 2;
	}
	private double brzinaPut(double V0, double a, double s) {
		return Math.sqrt(Math.pow(V0, 2) + 2 * a * s);
	}
	private double brzinaVreme(double V0, double a, double t) {
		return V0 + a * t;
	}
	private double izrVreme(double Vmax, double Vtren, double a) {
		return (Vmax - Vtren) / a;
	}
	
	public Vozilo(double maksBrzina, double ubrzanje, double upravljivost, String ime) {
		this.maksBrzina = maksBrzina;
		this.ubrzanje = ubrzanje;
		if(upravljivost < 0) upravljivost = 0;
		if(upravljivost > 1) upravljivost = 1;
		this.upravljivost = upravljivost;
		this.ime = ime;
		trenutnaBrzina = 0;
	}
	
	public double dohvMaksBrzinu() {
		return maksBrzina;
	}
	public double dohvUbrzanje() {
		return ubrzanje;
	}
	public double dohvUpravljivost() {
		return upravljivost;
	}
	public String dohvIme() {
		return ime;
	}
	public double dohvTrenBrzinu() {
		return trenutnaBrzina;
	}
	
	public double dohvPomMaxBrzinu() {
		return pomMaksBrzina;
	}
	
	public void postMaksBrzinu(double maksBrzina) {
		this.maksBrzina = maksBrzina;
		if(this.trenutnaBrzina > this.maksBrzina) this.trenutnaBrzina = this.maksBrzina;
	}
	public void postUbrzanje(double ubrzanje) {
		this.ubrzanje = ubrzanje;
	}
	public void postUpravljivost(double upravljivost) {
		if(upravljivost < 0) upravljivost = 0;
		if(upravljivost > 1) upravljivost = 1;
		this.upravljivost = upravljivost;
	}
	public void postIme(String ime) {
		this.ime = ime;
	}
	public void postTrenBrzinu(double trenutnaBrzina) {
		if(trenutnaBrzina > this.maksBrzina) trenutnaBrzina = this.maksBrzina;
		this.trenutnaBrzina = trenutnaBrzina;
	}
	public void postPomMaksBrzinu(double v) {
		pomMaksBrzina = v;
	}
	
	public double pomeriVozilo(double t) {
		double vreme = this.izrVreme(maksBrzina, trenutnaBrzina, ubrzanje);
		double s = 0;
		if(vreme > t) {
			s = this.duzinaPuta(trenutnaBrzina, t, ubrzanje);
			this.postTrenBrzinu(this.brzinaVreme(trenutnaBrzina, ubrzanje, t));
		} else {
			double s1 = this.duzinaPuta(trenutnaBrzina, vreme, ubrzanje);
			double t2 = t - vreme;
			double s2 = t2 * maksBrzina;
			this.postTrenBrzinu(maksBrzina);
			s = s1 + s2;
		}
		return s;
	}
	
	public double izracunajVreme(double s) {
		double t = 0;
		double t1 = this.izrVreme(maksBrzina, trenutnaBrzina, ubrzanje);
		double s1 = this.duzinaPuta(trenutnaBrzina, t1, ubrzanje);
		if(s1 < s) {
			double s2 = s - s1;
			double t2 = s2 / maksBrzina;
			t = t1 + t2;
		} else {
			double x = this.brzinaPut(trenutnaBrzina, ubrzanje, s);
			t = (x - trenutnaBrzina) / ubrzanje;
		}
		return t;
	}
	
	@Override
	public String toString() {
		return this.ime + " [" + this.maksBrzina + ", " + this.ubrzanje + ", " + this.upravljivost + "]";
	}
	
}
