package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.Users;
import com.example.tmdtserver.model.bill.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select a  from Account a join Users  u on u.id=a.users.id where u.email = :email")
    Account findAccountByEmail(@Param("email") String email);

    @Query(value = "select u from Users u join Account a on u.id=a.users.id where a.id = :id")
    Users findUserByAccount(@Param("id") Long id);

    @Query(value = "select a from Account a join Shop s on a.id = s.account.id where s.id = :id")
    Account findAccountByIdShop(@Param("id") Long id);

    // Trả account đã mua hàng trong 1 shop
    @Query(value = "select a from  Account a join  Bill b on a.id = b.account.id" +
            " where b.shop.id = :idShop group by a.id")
    List<Account> findAccountBuyProductOfShop (@Param("idShop")Long idShop);
}
