package raspored;

public class GIndeks extends Exception {
	private int i;
	public GIndeks(int i1) {
		i = i1;
	}
	public int getI() {
		return i;
	}
	@Override
	public String toString() {
		return "GRESKA: Indeks " + i + " je van opsega!";
	}
}
