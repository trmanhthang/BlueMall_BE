package com.example.tmdtserver.service.cityService;

import com.example.tmdtserver.model.Category;
import com.example.tmdtserver.model.shop.City;
import com.example.tmdtserver.repository.ICategoryRepository;
import com.example.tmdtserver.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements ICityService{
    @Autowired
    private ICityRepository cityRepository;
    @Override
    public List<City> showAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public Page<City> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public City findById(Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
