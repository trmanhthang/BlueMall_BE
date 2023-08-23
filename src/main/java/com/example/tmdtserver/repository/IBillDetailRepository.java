package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.bill.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface IBillDetailRepository  extends JpaRepository<BillDetail, Long> {
    // Truy vấn tất cả các đơn hàng theo idAccount
    @Query(value = "select bd from BillDetail bd join Bill b " +
            "on b.id = bd.bill.id where  b.account.id = :id order by b.date desc ")
    Page<BillDetail> showBillDetail(@Param("id")Long id, Pageable pageable);


    // Hủy đơn hàng của user
    @Modifying
    @Query(value = "update Bill b set b.statusBill.id = 5 where b.id = :id")
    void removeBillDetailUses(@Param("id")Long id);

    //Truy xuất các đơn hàng có trong shop của shop
    @Query(value = "select bd from BillDetail bd join Bill b " +
            "on b.id = bd.bill.id where b.shop.id = :idShop order by b.date desc ")
    Page<BillDetail> showBillOfShop(@Param("idShop")Long id,Pageable pageable);

    // Khi shop xác nhận đơn chuyển trạng thái sang đang vận chuyển
    @Modifying
    @Query(value = "UPDATE Bill b set b.statusBill.id = 2 where b.id = :id")
    void updateStatusBill2(@Param("id")Long id);

    // Khi khách hàng xác nhận đã nhận hàng thì chuyển trạng thái sang trạng thái đã nhận
    @Modifying
    @Query(value = "UPDATE Bill b set b.statusBill.id = 4 where b.id = :id")
    void updateStatusBillById4(@Param("id")Long id);

}
