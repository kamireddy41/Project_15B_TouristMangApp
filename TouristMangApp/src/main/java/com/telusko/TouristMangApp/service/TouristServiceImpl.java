package com.telusko.TouristMangApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.TouristMangApp.dao.ITouristRepo;
import com.telusko.TouristMangApp.exception.TouristNotFoundException;
import com.telusko.TouristMangApp.model.Tourist;
@Service
public class TouristServiceImpl implements ITouristService
{
	@Autowired
	private ITouristRepo repo;

	@Override
	public String registerTourist(Tourist tourist) 
	{
		Tourist touristdb=repo.save(tourist);
		Integer id = touristdb.getId();
		return "Tourist Info is registered successfully with id "+ id;
	}

	@Override
	public Tourist fetchTouristById(Integer id) 
	{
//		Optional<Tourist> optional = repo.findById(id);
//		if(optional.isPresent())
//		{
//			return optional.get();
//		}
//		return null;
		return repo.findById(id).
				orElseThrow(()->new TouristNotFoundException("Tourist with id "+ id+ " Not found in records"));
	}

	@Override
	public List<Tourist> fetchAllTourist() {
		
		List<Tourist> list = repo.findAll();
		return list;
	}

	@Override
	public String updateTouristData(Tourist tourist) {
		Optional<Tourist> optional = repo.findById(tourist.getId());
		if(optional.isPresent())
		{
			repo.save(tourist);
			return "Tourist info with id "+tourist.getId()+ " updated";
			
		}
		else
		{
			throw new TouristNotFoundException("Tourist with id "+ tourist.getId()+ " is not present in records to update");
		}
	}

}
