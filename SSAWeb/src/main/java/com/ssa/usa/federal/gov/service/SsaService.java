package com.ssa.usa.federal.gov.service;

import java.util.List;

import com.ssa.usa.federal.gov.model.SsaModel;

public interface SsaService {
	
	public Long saveSsn(SsaModel ssaModel);
	public List<SsaModel> retrieveSsnData();
	public SsaModel findStateBySsn(Long ssn);
}
