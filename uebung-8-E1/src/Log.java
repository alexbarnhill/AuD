public class Log {
	private static String trace = "";

	public static void log(String methodName) {
		trace += "[I@" + getCallerName() + "]"; // Import node
	}

	public static void log(int cfgNodeId) {
		trace += "[" + cfgNodeId + "@" + getCallerName() + "]"; // CFG node
	}

	public static void log() {
		trace += "[E@" + getCallerName() + "]"; // Export node
	}

	// ========================================

	public static void logAssert(Object actual) {
		trace += "[A@" + getCallerName() + "<" + String.valueOf(actual) + ">]"; // Assertion invocation
	}

	public static String getTrace() {
		return trace;
	}

	public static void resetTrace() {
		trace = "";
	}

	// ========================================

	private static String getCallerName() {
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		if (st.length > 3) {
			// 0 = Thread.getStackTrace()
			// 1 = Log.getCallerName()
			// 2 = Log.log(*
			return st[3].getClassName() + "." + st[3].getMethodName();
		}
		return "null";
	}
}