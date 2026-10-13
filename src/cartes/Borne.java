package cartes;

public class Borne extends Carte {
	private Integer km;

	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km + " km";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Borne borne) {
			return this.km.equals(borne.km);
		}
		return false;
	}
}
