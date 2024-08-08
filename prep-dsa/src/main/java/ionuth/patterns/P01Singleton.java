package ionuth.patterns;

class MySingleton {
	
	// with eager initalization. we might want to avoid it if the constructor is expensive
	// like it connects to some db or reads from the disk
	// private static MySingleton INSTANCE = new MySingleton();
	
	private static MySingleton INSTANCE;
	
	private MySingleton( ) { }
	
	public static MySingleton getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new MySingleton();
		}
		return INSTANCE;
	}
	
	public static synchronized MySingleton getInstanceSync() {
		if(INSTANCE==null) {
			INSTANCE = new MySingleton();
		}
		return INSTANCE;
	}
	
	// to increase overall performance is constructor is expensive
	public static MySingleton getInstanceSyncOpt() {
		if(INSTANCE==null) {
			synchronized (MySingleton.class) {
				if(INSTANCE==null) {
					INSTANCE = new MySingleton();
				}
			}
		}
		return INSTANCE;
	}
	
	public void doStuff() {
		System.out.println( "perform stuff in Singleton" );
	}
	
}


enum EnumSingleton {

    INSTANCE;
    
    public void doStuff(){
    	System.out.println( "perform stuff in enum Singleton" );
    }
}

public class P01Singleton {
	
	public static void main(String[] args) {
		
		MySingleton sg = MySingleton.getInstance();
		sg.doStuff();
		
		EnumSingleton enumSg = EnumSingleton.INSTANCE;
		enumSg.doStuff();
		
	}
	
}
