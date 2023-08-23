package com.example.tmdtserver.service.account.icore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrud<E> {
    Page<E> listAll(Pageable pageable);

    void save( E e);

    void delete(Long id);

    String randomCode(String email);
    E findByEmail(String email);

}
