public class FooBarBazQux {
	public static char BAR = '\uBABE';
	private String qux = "AuD";
	
	private static long instantiations = 0;
	private long fooCalls = 0;
	private static long staticFoo = 0;
	private static long barCalls = 0;
	private static long bazCalls = 0;
	private long quxCalls = 0;
	private static long staticQux = 0;
	
	
	public FooBarBazQux() {
		FooBarBazQux.instantiations++;
	}
	
	public FooBarBazQux(String qux) {
		FooBarBazQux.instantiations++;
		this.qux = qux;
	}
	
	
	
	public static long getInstantiations() {
		return instantiations;
	}
	
	public long getCallsTo(String methodName) {
		long calls = -1;
		switch(methodName.toLowerCase()) {
			case "foo":
				calls = this.fooCalls;
				break;
			case "bar":
				calls = FooBarBazQux.barCalls;
				break;
			case "baz":
				calls = FooBarBazQux.bazCalls;
				break;
			case "qux":
				calls = this.quxCalls;
				break;
			default:
				calls = -1;
				break;
		}
		
		return calls;
	}
	
	public int foo() {
		FooBarBazQux.staticFoo++;
		this.fooCalls++;
		return 0xF00;
	}
	
	public static char bar() {
		FooBarBazQux.barCalls++;
		return BAR;
	}
	
	public static double baz (double baz) {
		FooBarBazQux.bazCalls++;
		return 0.815 * baz;
	}
	
	public String qux(String pre, String post) {
		FooBarBazQux.staticQux++;
		this.quxCalls++;
		return pre + qux + post;
	}

	public static long getTotalCallsTo(String methodName) {
		long calls = -1;
		switch(methodName.toLowerCase()) {
			case "foo":
				calls = FooBarBazQux.staticFoo;
				break;
			case "bar":
				calls = FooBarBazQux.barCalls;
				break;
			case "baz":
				calls = FooBarBazQux.bazCalls;
				break;
			case "qux":
				calls = FooBarBazQux.staticQux;
				break;
			default:
				calls = -1;
				break;
		}
		return calls;
	}
}
