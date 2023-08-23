package com.example.tmdtserver.service.cityService;

import com.example.tmdtserver.model.Category;
import com.example.tmdtserver.model.shop.City;
import com.example.tmdtserver.service.shop_service.core.ICrudService;

import java.util.List;

public interface ICityService extends ICrudService<City> {
    List<City> showAllCity();
}
