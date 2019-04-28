package com.ssa.usa.federal.gov.exception;

public class CouldNotSaveException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5078547823872624684L;
	public CouldNotSaveException()
	{
		//non-param constructor
	}
	public CouldNotSaveException(String msg)
	{
		super(msg);
	}
}
