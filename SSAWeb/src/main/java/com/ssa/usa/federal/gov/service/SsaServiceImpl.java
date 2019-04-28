package com.ssa.usa.federal.gov.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssa.usa.federal.gov.entity.SsaEntity;
import com.ssa.usa.federal.gov.model.SsaModel;
import com.ssa.usa.federal.gov.repository.SsaRepository;

@Service
public class SsaServiceImpl implements SsaService{
	
	@Autowired
	private SsaRepository ssaRepository;
	
	/**
	 * this method is for saving ssn objects.
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
			//into entit from listEntity
			for(SsaEntity entity:listEntity)
			{
				//creating empty model obj
				SsaModel model=null;
				model=new SsaModel();
				
				//copying single entity to the model
				BeanUtils.copyProperties(entity, model);
				model.setSsn(entity.getSsn().toString());
				model.setDob(entity.getDob().substring(0,10));
				//adding model to the
				//list of ssaModel
				ssaModel.add(model);
			}
		}
		//returning ssaModel
		return ssaModel;
	}
	
	
}
