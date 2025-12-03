package com.telusko.TouristMangApp.service;

import java.util.List;

import com.telusko.TouristMangApp.exception.TouristNotFoundException;
import com.telusko.TouristMangApp.model.Tourist;

public interface ITouristService 
{
	String registerTourist(Tourist tourist);
	Tourist fetchTouristById(Integer id) ;
	List<Tourist> fetchAllTourist();
	String updateTouristData(Tourist tourist);
	

}
