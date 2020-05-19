package raspored;

public class Ponavljajuci extends Sadrzaj {
	private Vreme ponavljanje;
	private static char oznaka = 'P';
	
	Vreme poslednjePonavljanje() {
		try {
		Vreme v = this.dohvPocetak();
		Vreme kr = this.kraj();
		while((kr.dohvMin() + ponavljanje.dohvMin()) < 1440) {
			v = Vreme.saberi(v, ponavljanje);
			kr = Vreme.saberi(kr, ponavljanje);
		}
		return v;
		} catch(GVreme g) {return this.dohvPocetak(); }
	}
	
	
	public Ponavljajuci(String naziv1, Vreme trajanje1, Vreme ponavljanje1) {
		super(naziv1, trajanje1);
		ponavljanje = ponavljanje1;
	}
	
	public Vreme dohvPonavljanje() {
		return ponavljanje;
	}

	@Override
	public Vreme preklapaSe(Sadrzaj s) {
		try {
			if(!(s instanceof Ponavljajuci)) return null;
			Ponavljajuci p = (Ponavljajuci)s;
			Vreme poc1 = this.dohvPocetak();
			Vreme kraj1 = this.kraj();
			Vreme poc2 = p.dohvPocetak();
			Vreme kraj2 = p.kraj();
			
			while(poc1.dohvMin() <= this.poslednjePonavljanje().dohvMin()) {
				while(poc2.dohvMin() <= p.poslednjePonavljanje().dohvMin()) {
		
					if(poc1.dohvMin() < kraj2.dohvMin() && poc2.dohvMin() < kraj1.dohvMin()) {
						if(poc1.dohvMin() > poc2.dohvMin() && poc1.dohvMin() < kraj1.dohvMin()) {
							return poc1;
						} else return poc2;
					}
					if(poc2.dohvMin() != p.poslednjePonavljanje().dohvMin()) {
						poc2 = Vreme.saberi(poc2, p.ponavljanje);
						kraj2 = Vreme.saberi(kraj2, p.ponavljanje);
					} else break;
				}
				poc2 = p.dohvPocetak();
				kraj2 = p.kraj();
				if(poc1.dohvMin() != this.poslednjePonavljanje().dohvMin()) {
					poc1 = Vreme.saberi(poc1, this.ponavljanje);
					kraj1 = Vreme.saberi(kraj1, this.ponavljanje);
				} else break;
			}
			return null;
			
		} catch(GVreme g) { return null; }
	}
	
	
	@Override
	public char dohvOznaku() {
		return Ponavljajuci.oznaka;
	}
	@Override
	public String toString() {
		return super.toString() + " T" + ponavljanje;
	}
}
