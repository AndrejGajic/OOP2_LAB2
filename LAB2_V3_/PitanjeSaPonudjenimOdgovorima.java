package pitanja;

public class PitanjeSaPonudjenimOdgovorima extends Pitanje {
	private String[] odgovori;
	public PitanjeSaPonudjenimOdgovorima(String tekst1, int poeni1, double tezina1, String[] odgovori1) {
		super(tekst1, poeni1, tezina1);
		odgovori = odgovori1;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\n");
		for(int i = 0; i < odgovori.length; i++) {
			sb.append("\t");
			sb.append(i + 1);
			sb.append(". ");
			sb.append(odgovori[i]);
			if(i != odgovori.length - 1) sb.append("\n");
		}
		return sb.toString();
	}
	
}
