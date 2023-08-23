package com.example.tmdtserver.service.cart.my_interface;

import com.example.tmdtserver.model.bill.Bill;
import com.example.tmdtserver.model.bill.BillDetail;
import com.example.tmdtserver.service.shop_service.core.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBillService extends ICrudService<Bill> {
    BillDetail createBillDetail(BillDetail billDetail);
    Page<BillDetail> showBillDetail(Long id,Pageable pageable);
    Page<Bill> showAllBill(Long id,Pageable pageable);
    void removeBillDetailUses(Long idBill);

    Page<BillDetail> showBillOfShop(Long id,Pageable pageable);

    void updateStatusBill2(Long id, BillDetail billDetail);
    void updateStatusBillById4(Long id);
}
