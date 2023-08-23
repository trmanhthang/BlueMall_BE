package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICartRepository extends JpaRepository<Cart,Long> {

    //Truy xuất giỏ hàng theo id account
    @Query(value = "select c from Cart c where c.account.id = :id")
    Cart findByIdAccount(@Param("id")Long id);


}
