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

public class ConservativeTrading implements ITradingStrategy {
	//FIXME: exc causing error fix tomorrow
	//we are working with null values here
	private Trader person;
	private StockExchange exc;
	private NewsBoard news;
	
	public ConservativeTrading(Trader p, StockExchange e, NewsBoard n) {
		this.person = p;
		this.exc = e;
		this.news = n;
	}
	
	/*
	public void setTrader(Trader x) {
		this.person = x;
	}
	
	public void setStockExchange(StockExchange x) {
		this.exc = x;
	}
	
	public void setNewsBoard(NewsBoard x) {
		this.news = x;
	}
	*/
	
	@Override
	public void actOnEvent(Event e, int pos, int price) {
		//copied from TradingAgentConservativeClass that needs to be deleted
		IOrder newOrder = null;
		
		if (e instanceof GoodNews) {
            newOrder = new Bid(person,e.getSecrity(),(int) Math.round(price*1.05), (int) Math.round(pos*0.2),e.getTime());
        } else if (e instanceof BadNews) {
        	newOrder = new Ask(person,e.getSecrity(),(int) Math.round(price*0.95), (int) Math.round(pos*0.2),e.getTime());
        } else {
            System.out.println("Unknown event type");
        }
		
		if (newOrder!=null) {
			exc.submitOrder(newOrder,e.getTime());
		}
		
	}

}
