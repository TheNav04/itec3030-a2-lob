package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;

public class AggressiveTrading implements ITradingStrategy{
	//FIXME: exc causing error fix tomorrow
	//we are working with null values here
	private Trader person;
	private StockExchange exc;
	private NewsBoard news;
	 
	
	public AggressiveTrading(Trader p, StockExchange e, NewsBoard n) {
		this.person = p;
		this.exc = e;
		this.news = n;
	}
	
	
	
	public void setTrader(Trader x) {
		this.person = x;
	}
	
	public void setStockExchange(StockExchange x) {
		this.exc = x;
	}
	
	public void setNewsBoard(NewsBoard x) {
		this.news = x;
	}
	
	
	
	
	//What does it mean to be "Aggressive"
	//Just look in TradingAgentAggressiveClass
	
	//(Aggressive) are more ready to buy or sell respectively at a 
	//larger quantities compared to the former (Conservative).
	
	
	//t represents Trader
	//e represents stock exchange from parameter
	//exc represents stockExchange from object
	
	@Override
	public void actOnEvent(Event e, int pos, int price) {
		//copied from TradingAgentAggressiveClass
		IOrder newOrder = null;
		
		if (e instanceof GoodNews) {
            newOrder = new Bid(person,e.getSecrity(),(int) Math.round(price*1.05), (int) Math.round(pos*0.5),e.getTime());
        } else if (e instanceof BadNews) {
        	newOrder = new Ask(person,e.getSecrity(),(int) Math.round(price*0.90), (int) Math.round(pos*0.8),e.getTime());
        } else {
            System.out.println("Unknown event type");
        }
		
		if (newOrder!=null) {
			exc.submitOrder(newOrder,e.getTime());
		}
	}

}
