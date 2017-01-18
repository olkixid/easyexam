package easyexam.core;
import java.time.LocalDate;

//TODO: getter
public class Schueler implements Comparable<Schueler> {
	private String name;
	private String vorname;
	private LocalDate gebDatum;
	private int hashCode;
	
	public Schueler(String name, String vorname, LocalDate gebDatum) {
		this.name = name;
		this.vorname = vorname;
		this.gebDatum = gebDatum;
	}
	
	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public LocalDate getGebDatum() {
		return gebDatum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Schueler)) {
			return false;
		}
		Schueler otherSchueler = (Schueler)obj;
		if (this.name.equals(otherSchueler.name)
			&& this.vorname.equals(otherSchueler.vorname)
			&& this.gebDatum.equals(otherSchueler.gebDatum)) {
			
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = hashCode;
		if (result == 0) {
			result = 17;
			result = 31 * result + name.hashCode();
			result = 31 * result + vorname.hashCode();
			result = 31 * result + gebDatum.hashCode();
			hashCode = result;
		}
		return result;
	}
	
	public int compareTo(Schueler s) {
		//TODO: datum wird nicht beruecksichtigt
		return (this.name + this.vorname).compareTo(s.name + s.vorname);
	}
}