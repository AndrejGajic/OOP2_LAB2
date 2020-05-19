package raspored;

public class GDodaj extends Exception {
	private Sadrzaj s;
	public GDodaj(Sadrzaj s) {
		this.s = s;
	}
	public Sadrzaj dohvSadrzaj() {
		return s;
	}
	@Override
	public String toString() {
		return "GRESKA: sadrzaj " + s + " nije moguce dodati u semu!";
	}
}
