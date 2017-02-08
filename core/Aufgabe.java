package easyexam.core;

public class Aufgabe {
	private double maxPunkte;
	private String beschreibung;
	private boolean valid;
	
	public Aufgabe(double maxPunkte, String beschreibung) {
		this.maxPunkte = maxPunkte;
		this.beschreibung = beschreibung;
		
		if (maxPunkte > 0.0) {
			this.valid = true;
		}
	}
	public double getMaxPunkte() {
		return maxPunkte;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public boolean isValid() {
		return valid;
	}
}
