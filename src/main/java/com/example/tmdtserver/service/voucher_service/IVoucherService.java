package com.example.tmdtserver.service.voucher_service;

import com.example.tmdtserver.model.Voucher;
import com.example.tmdtserver.service.shop_service.core.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVoucherService extends ICrudService<Voucher> {
    Page<Voucher> showAllVoucher(Pageable pageable, Long id);

    Double percentVoucher(Long id,String voucher);

    Voucher updateVoucher(Long id,String voucher);

}
