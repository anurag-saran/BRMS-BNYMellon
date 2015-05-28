package com.bny.model;

public class Router {
	private String routetype;
	private String destination;
	private String destinationType;
	
	public Router(String routetype, String destination, String destinationType) {
		//super();
		this.routetype = routetype;
		this.destination = destination;
		this.destinationType = destinationType;
	}
	/**
	 * @return the routetype
	 */
	public String getRoutetype() {
		return routetype;
	}
	/**
	 * @param routetype the routetype to set
	 */
	public void setRoutetype(String routetype) {
		this.routetype = routetype;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the destinationType
	 */
	public String getDestinationType() {
		return destinationType;
	}
	/**
	 * @param destinationType the destinationType to set
	 */
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}

}
