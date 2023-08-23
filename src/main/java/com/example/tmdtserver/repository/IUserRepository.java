package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);

    //Lấy thông tin của account qua idShop
    @Query(value = "select u from Users u join Account a on u.id=a.users.id " +
            "join Shop s on a.id = s.account.id where s.id = :id")
    Users findByIdShop(@Param("id")Long id);
}
