package easyexam.core;
public class Aufgabe {
	private double maxPunkte;
	private String beschreibung;
	
	public Aufgabe(double maxPunkte, String beschreibung) {
		this.maxPunkte = maxPunkte;
		this.beschreibung = beschreibung;
	}
	public double getMaxPunkte() {
		return maxPunkte;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
}
