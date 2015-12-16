package com.aaxiscommerce.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToJMSWithCamel {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("file:data/inbox?noop=true").process(new Processor(){

					@Override
					public void process(Exchange pExchange) throws Exception {
						System.out.println("We just downloaded: "+ pExchange.getIn().getHeader("CamelFileName"));
					}
					
				}).
				to("jms:incomingOrders");
			}
		});
		context.start();
		Thread.sleep(1500000);
		context.stop();
	}

}
