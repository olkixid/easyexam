package easyexam.core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


//TODO:observer pattern implementieren (util event oder so?)
//vielleicht zum bearbeiten views die list und set implementiernen?
public class Arbeit {
	private HashMap<Schueler, HashMap<String, Bewertung>> tabelle = new HashMap<>();
	private HashMap<String, Aufgabe> aufgaben = new HashMap<>();
	private ArrayList<String> enthalteneAufgabenKeys = new ArrayList<>();
	
	private ArrayList<ArbeitListener> listener = new ArrayList<>();
	
	public boolean addAufgabenKey(String aKey) {
		if (enthalteneAufgabenKeys.contains(aKey)) {
			return false;
		}
		enthalteneAufgabenKeys.add(aKey);
		return true;
	}
	
	public boolean removeAufgabenKey(String aKey) {
		if (!enthalteneAufgabenKeys.remove(aKey)) {
			return false;
		}
		aufgaben.remove(aKey);
		
		for (HashMap<String, Bewertung> sDaten : tabelle.values()) {
			sDaten.remove(aKey);
		}
		
		return true;
	}
	
	public List<String> getAllAufgabenKeys() {
		return Collections.unmodifiableList(enthalteneAufgabenKeys);
	}
	
	public Aufgabe getAufgabeForKey(String aKey) {
		if ( !enthalteneAufgabenKeys.contains(aKey) ) {
			return null;
		}
		return aufgaben.get(aKey);
	}
	
	public boolean setAufgabeForKey(Aufgabe a, String aKey) {
		if (!enthalteneAufgabenKeys.contains(aKey)) {
			return false;
		}
		aufgaben.put(aKey, a);
		return true;
	}
	
	public boolean addSchueler(Schueler s) {
		if (tabelle.containsKey(s)) {
			return false;
		}
		
		tabelle.put(s, new HashMap<>());
		fireEvent(new ArbeitEvent(this, ArbeitEvent.SCHUELER_CHANGED));
		return true;
	}
	
	public boolean removeSchueler(Schueler s) {
		if(tabelle.remove(s) == null) {
			return false;
		}

		fireEvent(new ArbeitEvent(this, ArbeitEvent.SCHUELER_CHANGED));
		return true;
	}
	
	public Set<Schueler> getAllSchueler() {
		return Collections.unmodifiableSet(tabelle.keySet());
	}
	
	public Bewertung getBewertungFor(Schueler s, String aKey) {
		if ( !tabelle.containsKey(s) || !enthalteneAufgabenKeys.contains(aKey) ) {
			return null;
		}
		
		return tabelle.get(s).get(aKey);
	}
	
	public boolean setBewertungFor(Schueler s, String aKey, Bewertung b) {
		if ( !tabelle.containsKey(s) || !enthalteneAufgabenKeys.contains(aKey) ) {
			return false;
		}
		
		tabelle.get(s).put(aKey, b);
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
