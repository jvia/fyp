package org.bham.aucom.util;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AucomFilter implements Filter {

	@Override
	public boolean isLoggable(LogRecord lr) {
		String msg = lr.getMessage();
		if (msg.startsWith("ALG")) {
			return false;
		}
		return true;

	}

}
