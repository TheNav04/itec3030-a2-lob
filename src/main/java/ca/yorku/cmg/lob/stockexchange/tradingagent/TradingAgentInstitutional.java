package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

public class TradingAgentInstitutional extends TradingAgent{

	public TradingAgentInstitutional(Trader t, StockExchange e, NewsBoard n) {
		super(t, e, n);
		//put here to insure all attributes are initialized before adding to news as an observer
		news.addObserver(this);
	}
	


}
