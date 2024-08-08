package ionuth.patterns;

import java.util.ArrayList;
import java.util.List;

interface Observer {
	void update();
}

class ImplObserverA implements Observer {
	@Override
	public void update() {
		System.out.println("update in Observer A");
	}
}

class ImplObserverB implements Observer {
	@Override
	public void update() {
		System.out.println("update in Observer B");
	}
}

interface Subject {
	void attach(Observer o);
	void detach(Observer o);
	void nofiyUpdate();
}


class Publisher {
	
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer o) {
		observers.add(o);
	}
	
	public void detach(Observer o) {
		observers.remove(o);
	}
	
	public void notifyUpdate() {
		observers.forEach(Observer::update);
	}
	
}

public class P03Observer {
	
	public static void main(String[] args) {
		
		Observer obsA = new ImplObserverA();
		Observer obsB = new ImplObserverB();
		Publisher p = new Publisher();
		p.attach(obsA);
		p.attach(obsB);
		p.notifyUpdate();
		
	}
	
}
