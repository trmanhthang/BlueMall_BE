package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.bill.BillDetail;
import com.example.tmdtserver.model.shop.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public interface IShopRepository extends JpaRepository<Shop,Long> {
    // Truy van Shop Theo Account
    @Query(value = "select s from Shop s join Account a on s.account.id = a.id where a.id = :id")
    Shop findShopByAccount(@Param("id")Long id);

    @Query(value = "select s from Shop s join Product p on s.id = p.shop.id where p.id = :id")
    Shop findShopByProduct(@Param("id")Long id);

    // Hiển thị danh sách khách hàng đã mua sản phẩm ở shop
    @Query(value = "select bd from BillDetail  bd join Bill b " +
            "on b.id = bd.bill.id where b.shop.id = :idShop and b.statusBill.id = 4")
    List<BillDetail> findTotalOfAccountBuyShop (@Param("idShop")Long idShop);
}
