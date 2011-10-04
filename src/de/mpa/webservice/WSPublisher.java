package de.mpa.webservice;

import javax.xml.ws.Endpoint;

import de.mpa.webservice.ServerImpl;

public class WSPublisher {
	public static void start(String host, String port) {
		Endpoint.publish("http://" + host + ":" + port + "/WS/Server",new ServerImpl());
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/WS/Server",new ServerImpl());
	}
}
