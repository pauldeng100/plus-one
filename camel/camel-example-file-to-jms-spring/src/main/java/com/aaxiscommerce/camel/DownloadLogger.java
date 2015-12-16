package com.aaxiscommerce.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DownloadLogger implements Processor {

	@Override
	public void process(Exchange pExchange) throws Exception {
		System.out.println("We just downloaded: "
				+ pExchange.getIn().getHeader("CamelFileName"));		
	}
 

}
