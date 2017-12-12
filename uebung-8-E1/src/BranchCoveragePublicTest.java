import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import org.junit.*;

public class BranchCoveragePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME = "BranchCoverage-Test";

	// ========== SUT is the test class itself #-) ==========
	protected static final Class<SchiffVersenkenZweigueberdeckungsTest> testClass = SchiffVersenkenZweigueberdeckungsTest.class;
	// ========== the SUT of the SUT is... #-) ==========
	public static final String SUT_SUT_CLASS_NAME = "SchiffVersenken";
	public static final String SUT_SUT_CONSTRUCTOR = "<init>";
	public static final String SUT_SUT_METHOD_neuesSpielfeld = "neuesSpielfeld";
	public static final String SUT_SUT_METHOD_positioniereSchiff = "positioniereSchiff";
	public static final String SUT_SUT_METHOD_feldAusgeben = "feldAusgeben";
	public static final String SUT_SUT_METHOD_spielzugAusfuehren = "spielzugAusfuehren";

	// ========== Test the test #-) ==========
	@org.junit.Test(timeout = 1666)
	public void pubTest__BrachCoverage_of__neuesSpielfeld__PUBLIC_TEST() {
		scan_test_case_methods();
		String[][] edges = { { "I", "1" }, { "1", "2" }, { "2", "3" }, { "3", "2" }, { "2", "4" }, { "4", "1" }, { "1", "5" }, { "5", "E" }, };
		BranchCoveragePublicTest.runTestsAndCheckTrace(testCaseMethods, true, BranchCoveragePublicTest.SUT_SUT_CLASS_NAME, BranchCoveragePublicTest.SUT_SUT_METHOD_neuesSpielfeld, edges, null);
	}

	@org.junit.Test(timeout = 1666)
	public void pubTest__use_of_Assert__in__positioniereSchiff__PUBLIC_TEST() {
		scan_test_case_methods();
		BranchCoveragePublicTest.runTestsAndCheckTrace(testCaseMethods, true, BranchCoveragePublicTest.SUT_SUT_CLASS_NAME, null, null, BranchCoveragePublicTest.SUT_SUT_METHOD_positioniereSchiff);
	}

	// ========== AuD-fake-runner and coverage checker ==========
	private List<Method> testCaseMethods = new LinkedList<>();

	@org.junit.Test(timeout = 1666)
	@Ignore // DO NOT DELETE ME ;)...
	public void scan_test_case_methods() {
		testCaseMethods = new LinkedList<>();
		for (Method testCaseMethod : testClass.getMethods()) {
			if (testCaseMethod.isAnnotationPresent(Test.class) && testCaseMethod.getParameterTypes().length == 0) {
				testCaseMethods.add(testCaseMethod);
			}
		}
		org.junit.Assert.assertNotEquals("You should provide at least one test case (test method)!", 0, testCaseMethods.size());
	}

	protected static void runTestsAndCheckTrace(List<Method> testCaseMethods, boolean noFailedTest, String className, String methodNameForEdgeCoverageCheck, String[][] edges, String methodNameForAssertCheck) {
		Log.resetTrace();
		int testsRun = 0, testsFailed = 0;
		String result = "";
		Constructor<?> constructor = null;
		try {
			constructor = testClass.getDeclaredConstructor();
			org.junit.Assert.assertTrue("Test class should have exactly one public zero-argument constructor", Modifier.isPublic(constructor.getModifiers()));
		} catch (NoSuchMethodException nsme) {
			org.junit.Assert.fail("Test class should have exactly one public zero-argument constructor");
		}
		for (Method testCaseMethod : testCaseMethods) {
			try {
				testsRun++;
				testCaseMethod.invoke(constructor.newInstance());
			} catch (Throwable t) {
				testsFailed++;
				result += testsFailed + ") " + testCaseMethod.getName() + "(" + testClass.getName() + ")" + "\n";
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				t.getCause().printStackTrace(pw);
				result += sw.toString();
			}
		}
		org.junit.Assert.assertNotEquals("You should provide at least one test case (test method)!", 0, testsRun);
		if (noFailedTest) {
			org.junit.Assert.assertEquals("Your test cases had failures, although nothing should have gone wrong here...", "", result);
		} else {
			org.junit.Assert.assertNotEquals("Your test cases should have had failures due to your asserts, but it doesn't seem that you detected them...", "", result);
		}
		String trace = Log.getTrace();
		if (methodNameForEdgeCoverageCheck != null && edges != null) {
			String[] expectedTraceEntries = new String[edges.length];
			for (int i = 0; i < edges.length; i++) {
				expectedTraceEntries[i] = "[" + edges[i][0] + "@" + className + "." + methodNameForEdgeCoverageCheck + "][" + edges[i][1] + "@" + className + "." + methodNameForEdgeCoverageCheck + "]";
			}
			for (String expectedTraceEntry : expectedTraceEntries) {
				org.junit.Assert.assertTrue("Edge not covered: " + expectedTraceEntry, trace.contains(expectedTraceEntry));
			}
		}
		if (methodNameForAssertCheck != null) {
			String assertLogString = "[A@Assert.assertEquals<";
			String lastLogEntry = "[E@" + className + "." + methodNameForAssertCheck + "]";
			int assertCalls = 0;
			int pos = 0;
			while (pos >= 0) {
				pos = trace.indexOf(lastLogEntry, pos);
				if (pos >= 0) {
					pos += lastLogEntry.length();
					org.junit.Assert.assertTrue("You should have called Assert.assertEquals(...) immediately after " + methodNameForAssertCheck, trace.startsWith(assertLogString, pos));
					assertCalls++;
					if (!noFailedTest && methodNameForAssertCheck.endsWith("Schiff]")) {
						org.junit.Assert.assertTrue("I am missing the real actual value in the assert message after " + methodNameForAssertCheck, trace.startsWith("true", pos + assertLogString.length()) || trace.startsWith("false", pos + assertLogString.length()));
					} else if (!noFailedTest) {
						org.junit.Assert.assertTrue("I am missing the real actual value in the assert message after " + methodNameForAssertCheck, trace.startsWith("47110815", pos + assertLogString.length()));
					}
				}
				pos = trace.indexOf(lastLogEntry, pos);
			}
			org.junit.Assert.assertNotEquals("You should have called Assert.assertEquals(...) at least once per test call to each of the methods returning a value! But you missed a call to: " + methodNameForAssertCheck, 0, assertCalls);
		}
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		if (args.length == 0) {
			// starts junit runner - don't try to understand!
			org.junit.runner.JUnitCore.main(new Object() {
			}.getClass().getEnclosingClass().getSimpleName());
			System.out.println("HINT: Run me with exactly one argument denoting the name of your test class (e.g. \"TestSuite\")...");
		} else if (args.length == 1) {
			try {
				Class<?> testClass = ClassLoader.getSystemClassLoader().loadClass(args[0]);
				System.out.println("JUnit AuD-fake-version 0.1");
				Constructor<?> constructor = null;
				int testsRun = 0, testsFailed = 0;
				String result = "";
				long startTime = System.currentTimeMillis();
				System.setErr(new PrintStream(new ByteArrayOutputStream()));
				try {
					constructor = testClass.getDeclaredConstructor();
					if (!Modifier.isPublic(constructor.getModifiers())) {
						System.out.println("Test class should have exactly one public constructor");
					}
					Object target = constructor.newInstance();
					for (Method testCaseMethod : testClass.getMethods()) {
						if (testCaseMethod.isAnnotationPresent(Test.class) && testCaseMethod.getParameterTypes().length == 0) {
							try {
								testsRun++;
								testCaseMethod.invoke(target);
								System.out.print(".");
							} catch (Throwable t) {
								testsFailed++;
								System.out.print("E");
								result += testsFailed + ") " + testCaseMethod.getName() + "(" + testClass.getName() + ")" + "\n";
								StringWriter sw = new StringWriter();
								PrintWriter pw = new PrintWriter(sw);
								t.getCause().printStackTrace(pw);
								result += sw.toString();
							}
						}
					}
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException nsme) {
					System.out.println("Test class should have exactly one public zero-argument constructor");
				}
				long endTime = System.currentTimeMillis();
				System.out.println();
				System.out.println("Time: " + (endTime - startTime) / 1000d);
				if (testsFailed != 0) {
					System.out.println("There " + (testsFailed == 1 ? "was " : "were ") + testsFailed + " failure" + (testsFailed == 1 ? "" : "s") + ":");
					System.out.println(result);
					System.out.println("FAILURES!!!");
					System.out.println("Tests run: " + testsRun + ",  Failures: " + testsFailed);
				} else {
					System.out.println();
					System.out.println("OK (" + testsRun + " test" + (testsRun == 1 ? "" : "s") + ")");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error: Could not find or load main class " + args[0]);
			}
		} else {
			System.out.println("HINT: Run me with NO or EXACTLY ONE argument denoting the name of your test class (e.g. \"TestSuite\")...");
		}
	}
}