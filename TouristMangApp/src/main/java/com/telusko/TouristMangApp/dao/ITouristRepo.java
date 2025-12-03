package com.telusko.TouristMangApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.TouristMangApp.model.Tourist;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> 
{

}
