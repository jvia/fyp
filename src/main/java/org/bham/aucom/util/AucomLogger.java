package org.bham.aucom.util;

import java.util.logging.Logger;

public class AucomLogger {
    public static void info(Object o, String msg) {
        Logger.getLogger(o.getClass().getCanonicalName()).info(msg);
    }

    public static void severe(Object o, String msg) {
        Logger.getLogger(o.getClass().getCanonicalName()).severe(msg);
    }
}
