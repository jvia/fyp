package org.bham.aucom.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class AlgorithmFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		String className = record.getSourceClassName().substring(record.getSourceClassName().lastIndexOf(".") + 1);
		return className + ":" + record.getMessage()+ "\n";
	}

}
