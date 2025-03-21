package ca.yorku.cmg.lob.stockexchange.events;
import java.util.ArrayList;

import ca.yorku.cmg.lob.stockexchange.tradingagent.INewsObserver;
import ca.yorku.cmg.lob.stockexchange.tradingagent.TradingAgent;

public abstract class Subject {
	//any registration for notifications should happen at object creation time (in the constructor).
	//need to add to obs as object are created
	ArrayList<INewsObserver> obs = new ArrayList<INewsObserver>();
	
	
	//observers added when objected is constructed
	//Used in TradingAgent Inheritance structure
	public void addObserver(INewsObserver x) {
		obs.add(x);
	}
	
	public void removeObserver(INewsObserver x) {
		obs.remove(x);
	}
	
	//used in runEventsList() method
	public void notifyObservers(Event e) {
		for(INewsObserver x: obs) {
			notifySingleObserver(x, e);
		}
	}
	
	public abstract void notifySingleObserver(INewsObserver x, Event e);
	
}
