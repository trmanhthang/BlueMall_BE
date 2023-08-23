package com.example.tmdtserver.service.shop_service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService<E>{
    Page<E> findALl(Pageable pageable);
    E save (E e);
    E findById (Long id);
    void remove(Long id);
}