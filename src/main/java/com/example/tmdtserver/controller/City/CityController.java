package com.example.tmdtserver.controller.City;

import com.example.tmdtserver.model.Category;
import com.example.tmdtserver.model.shop.City;
import com.example.tmdtserver.service.category_service.ICategoryService;
import com.example.tmdtserver.service.cityService.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/home/city")
public class CityController {
    @Autowired
    private ICityService cityService;
    @GetMapping
    public ResponseEntity<List<City>> findAll(){
        List<City> cityList = cityService.showAllCity();
        if (cityList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cityList,HttpStatus.OK);
    }
}