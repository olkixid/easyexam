package easyexam.core;

public class Bewertung {
	private double punkte;
	private String hinweis;
	private boolean valid;
	
	public Bewertung(double punkte, String hinweis) {
		this.punkte = punkte;
		this.hinweis = hinweis;
		
		if (punkte > 0.0) {
			this.valid = true;
		}
	}
	
	public double getPunkte() {
		return punkte;
	}

	public String getHinweis() {
		return hinweis;
	}

	public boolean isValid() {
		return valid;
	}
}
