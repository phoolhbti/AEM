package com.citraining.ws.finance;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectFactory obj=new ObjectFactory();
		//obj.c
	System.out.println("Hello..."+obj.createGetStockQuote().getSymbol().getValue().toUpperCase());
			//			obj.createGetStockQuoteJsonResponse();
	}

}
