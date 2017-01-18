package easyexam.core;

import java.util.EventObject;

@SuppressWarnings("serial")
public class ArbeitEvent extends EventObject {
	static final int SCHUELER_CHANGED = 1;
	
	private int flags;
	
	public ArbeitEvent(Arbeit source, int flags) {
		super(source);
		this.flags = flags;
	}
	
	public boolean schuelerDidChange() {
		return (flags & SCHUELER_CHANGED) != 0;
	}
}
