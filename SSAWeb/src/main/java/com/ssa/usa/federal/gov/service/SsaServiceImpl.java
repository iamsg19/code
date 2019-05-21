package com.ssa.usa.federal.gov.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssa.usa.federal.gov.entity.SsaEntity;
import com.ssa.usa.federal.gov.model.SsaModel;
import com.ssa.usa.federal.gov.repository.SsaRepository;
import com.ssa.usa.federal.gov.rest.controller.SsnRestController;
import com.ssa.usa.federal.gov.rest.exception.StateNotFoundException;

/**
 * This is a service class 
 * this class is used to provide different types of service methods
 * 
 * @author Shivaji Chandra
 *
 */
@Service
public class SsaServiceImpl implements SsaService{
	
	//Logger 
	private static final Logger logger = LoggerFactory.getLogger(SsnRestController.class);
	
	@Autowired
	private SsaRepository ssaRepository;
	
	/**
	 * This method is used to enroll the ssn details
	 * 
	 * @param	SsaModel ssaModel
	 * @return	Long	ssn
	 */
	@Override
	public Long saveSsn(SsaModel ssaModel){
		
		//empty entity
		SsaEntity ssaEntity=new SsaEntity();
		byte[] imageFile=null;
		
		//empty byte array for keeping 
		//image bytes
		
		/*Date date=null;
		SimpleDateFormat dateFrmt=new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			date=dateFrmt.parse(ssaModel.getDob());
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}*/
		//retrieving image from model object
		MultipartFile image=ssaModel.getPhoto();
		try 
		{
			//converting image into bytes
			//and storing into imageFile byte array
			imageFile=image.getBytes();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//copying ssaModel data to ssaEntity
		BeanUtils.copyProperties(ssaModel, ssaEntity);
		
		//setting image into ssaEntity
		ssaEntity.setPhoto(imageFile);
		/*ssaEntity.setDob(date);*/
		
		/*saving ssaEntity into database
		this save method will return the
		ssaEntity with created ssnNo*/
		ssaEntity=ssaRepository.save(ssaEntity);
		
		//returning the ssnNo
		return ssaEntity.getSsn();
	}

	/**
	 * this method is for retrieving all the records
	 *
	 *@param	no parameter
	 *@return	List<SsaModel>
	 *
	 *
	 */
	@Override
	public List<SsaModel> retrieveSsnData() {
		
		//creating empty SsaModel list
		List<SsaModel> ssaModel=null;
		List<SsaEntity> listEntity=null;
		ssaModel=new ArrayList<SsaModel>();
		
		//retrieving all the records from database
		listEntity=ssaRepository.findAll();
		
		//checking listEntity is empty or not
		if(!listEntity.isEmpty())
		{
			//retrieving single-single entity
			//into entity from listEntity
			for(SsaEntity entity:listEntity)
			{
				//creating empty model obj
				SsaModel model=null;
				model=new SsaModel();
				
				//copying single entity to the model
				BeanUtils.copyProperties(entity, model);
				model.setSsn(entity.getSsn().toString());
				//adding model to the
				//list of ssaModel
				ssaModel.add(model);
			}
		}
		//returning ssaModel
		return ssaModel;
	}
	
	/**
	 * This method is for getting 
	 * 	the state based on given ssn
	 * 
	 * @param	Long ssn
	 * @return	SsaModel
	 * 
	 * if the given ssn will not be there in database then 
	 * StateNotFoundException will throw
	 */
	public SsaModel findStateBySsn(Long ssn)
	{
		//Empty SsaModel
		SsaModel ssaModel=new SsaModel();
		
		//Empty SsaEntity
		SsaEntity ssaEntity=null;
		
		//finding state by ssn
		Optional<SsaEntity> optionalEntity=ssaRepository.findById(ssn);
		
		//checking data is present or not
		if(optionalEntity.isPresent())
		{
			//getting data from optionalEntity to SsaEntity
			ssaEntity=optionalEntity.get();
			
			//copying data from ssaEntity to ssaModel
			BeanUtils.copyProperties(ssaEntity, ssaModel);
		}
		else
		{
			//throwing the StateNotFoundException
			//if ssn is invalid or optionalEntity is empty
			throw new StateNotFoundException();
		}
		return ssaModel;
	}
}
