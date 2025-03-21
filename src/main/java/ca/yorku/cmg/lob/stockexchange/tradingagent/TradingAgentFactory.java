package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

public class TradingAgentFactory extends AbstractTradingAgentFactory {
	
	public TradingAgentFactory() {
		
	}
	
	
	//where type is one of "Institutional" or "Retail" and style one of "Conservative" 
	//or "Aggressive".
	
	//The type of object created here
	//Behavior added in StockExchange
	@Override
	public TradingAgent createAgent(String type, String style, Trader t, 
		StockExchange e, NewsBoard n) {
		TradingAgent per = null;
		
		
	
		if(type.equals("Institutional") == true) {
			 per = new TradingAgentInstitutional(t, e, n);
		}
		else if(type.equals("Retail") == true) {
			per = new TradingAgentRetail(t, e, n);
		}

		//-------------------
		
		if(style.equals("Conservative") == true) {
			per.setStrategy(new ConservativeTrading(t, e , n));
		}
		else if(style.equals("Aggressive") == true) {
			per.setStrategy(new AggressiveTrading(t, e, n));
		}
		
		return per;
		
	}	
	
	

}
