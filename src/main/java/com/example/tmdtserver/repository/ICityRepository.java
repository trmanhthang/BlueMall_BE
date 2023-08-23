package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.shop.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City,Long> {

}
