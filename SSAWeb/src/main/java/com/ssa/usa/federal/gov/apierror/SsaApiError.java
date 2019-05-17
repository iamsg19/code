package com.ssa.usa.federal.gov.apierror;
import java.util.Date;

/**
 * This class is developed to provide the details 
 * like- status code
 * exception name
 * date etc
 * 
 * @author Shivaji Chandra
 *
 */
public class SsaApiError {

	private Integer statusCode;
	private String excepitonName;
	private Date	date;

	public SsaApiError() {
		// TODO Auto-generated constructor stub
	}

	public SsaApiError(Integer statusCode, String excepitonName, Date date) {
		this.statusCode = statusCode;
		this.excepitonName = excepitonName;
		this.date = date;
		System.out.println("Shivaji");
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getExcepitonName() {
		return excepitonName;
	}

	public void setExcepitonName(String excepitonName) {
		this.excepitonName = excepitonName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
