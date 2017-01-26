package easyexam.core;

import java.util.ArrayList;
import java.util.HashMap;


public class Arbeit {
	private HashMap<Schueler, HashMap<String, Bewertung>> tabelle = new HashMap<>();
	private ArrayList<Schueler> schuelerList  = new ArrayList<>();
	private ArrayList<String> aufgabenKeyList = new ArrayList<>();
	
	private HashMap<String, Aufgabe> aufgaben = new HashMap<>();
	
	private ArrayList<ArbeitListener> listener = new ArrayList<>();
	
	
	public Aufgabe getAufgabeForKey(String aufgabenKey) {
		return aufgaben.get(aufgabenKey);
	}
	
	public void setAufgabeForKey(String aufgabenKey, Aufgabe a) {
		aufgaben.put(aufgabenKey, a);
	}
	
	public int getNumberOfSchueler() {
		return tabelle.size();
	}
	
	public boolean addSchueler(Schueler s) {
		if (tabelle.containsKey(s)) {
			return false;
		}
		tabelle.put(s, new HashMap<>());
		schuelerList.add(s);
		fireEvent(new ArbeitEvent(this, ArbeitEvent.SCHUELER_CHANGED));
		return true;
	}
	
	public boolean removeSchueler(Schueler s) {
		boolean successful = schuelerList.remove(s);
		if (!successful) {
			return false;
		}
		tabelle.remove(s);
		fireEvent(new ArbeitEvent(this, ArbeitEvent.SCHUELER_CHANGED));
		return true;
	}

	public Schueler removeSchuelerAt(int i) {
		Schueler removedSchueler = schuelerList.remove(i);
		if (removedSchueler == null) {
			return null;
		}
		tabelle.remove(removedSchueler);
		fireEvent(new ArbeitEvent(this, ArbeitEvent.SCHUELER_CHANGED));
		return removedSchueler;
	}
	
	public Schueler getSchuelerAt(int i) {
		return schuelerList.get(i);
	}
	
	public int getNumberOfAufgabenKeys() {
		return aufgabenKeyList.size();
	}
	
	public boolean addAufgabenKey(String str) {
		if (aufgabenKeyList.contains(str)) {
			return false;
		}
		aufgabenKeyList.add(str);
		fireEvent(new ArbeitEvent(this, ArbeitEvent.AUFGABENKEYS_CHANGED));
		return true;
	}
	
	public boolean removeAufgabenKey(String str) {
		if (!aufgabenKeyList.contains(str)) {
			return false;
		}
		aufgabenKeyList.remove(str);
		
		for (HashMap<String, Bewertung> inner : tabelle.values()) {
			inner.remove(str);
		}
		
		fireEvent(new ArbeitEvent(this, ArbeitEvent.AUFGABENKEYS_CHANGED));
		return true;
	}

	public String removeAufgabenKeyAt(int i) {
		String removedAufgabenKey = aufgabenKeyList.remove(i);
		if (removedAufgabenKey == null) {
			return null;
		}
		
		for (HashMap<String, Bewertung> inner : tabelle.values()) {
			inner.remove(removedAufgabenKey);
		}
		
		fireEvent(new ArbeitEvent(this, ArbeitEvent.AUFGABENKEYS_CHANGED));
		return removedAufgabenKey;
	}
	
	public String getAufgabenKeyAt(int i) {
		return aufgabenKeyList.get(i);
	}
	
	
	public Bewertung getBewertungFor(Schueler s, String aKey) {
		HashMap<String, Bewertung> inner = tabelle.get(s);
		if (inner == null) {
			return null;
		}
		
		return inner.get(aKey);
	}
	
	public boolean setBewertungFor(Schueler s, String aKey, Bewertung b) {
		HashMap<String, Bewertung> inner = tabelle.get(s);
		if (inner == null) {
			return false;
		}
		inner.put(aKey, b);
		return true;
	}

	public void addArbeitListener(ArbeitListener l) {
		listener.add(l);
	}
	
	public void removeArbeitListener(ArbeitListener l) {
		listener.remove(l);
	}
	
	private void fireEvent(ArbeitEvent e) {
		for (ArbeitListener l : listener) {
			l.arbeitChanged(e);
		}
	}
}
