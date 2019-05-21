package com.ssa.usa.federal.gov.rest.exception;

public class StateNotFoundException extends RuntimeException{

	/**
	 * This is class for RestController Exception when the 
	 * state is not found from the id
	 */
	private static final long serialVersionUID = 4865906636125136433L;

	public StateNotFoundException() {

	}
	
	public StateNotFoundException(String msg)
	{
		super(msg);
	}
}
