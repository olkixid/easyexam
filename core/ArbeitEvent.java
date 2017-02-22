package easyexam.core;

import java.util.EventObject;

@SuppressWarnings("serial")
public class ArbeitEvent extends EventObject {
	static final int SCHUELER_CHANGED = 1;
	static final int AUFGABENKEYS_CHANGED = 2;
	static final int AUFGABEN_CHANGED = 4;
	static final int BEWERTUNGEN_CHANGED = 8;
	
	private int flags;
	
	public ArbeitEvent(Arbeit source, int flags) {
		super(source);
		this.flags = flags;
	}
	
	public boolean schuelerDidChange() {
		return (flags & SCHUELER_CHANGED) != 0;
	}
	
	public boolean aufgabeKeysDidChange() {
		return (flags & AUFGABENKEYS_CHANGED) != 0;
	}
	
	public boolean aufgabenDidChange() {
		return (flags & AUFGABEN_CHANGED) != 0;
	}
	
	public boolean bewertungenDidChange() {
		return (flags & BEWERTUNGEN_CHANGED) != 0;
	}
}
