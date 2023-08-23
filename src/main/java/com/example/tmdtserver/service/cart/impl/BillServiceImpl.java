package com.example.tmdtserver.service.cart.impl;

import com.example.tmdtserver.model.product.Product;
import com.example.tmdtserver.model.bill.Bill;
import com.example.tmdtserver.model.bill.BillDetail;
import com.example.tmdtserver.repository.IBillDetailRepository;
import com.example.tmdtserver.repository.IBillRepository;
import com.example.tmdtserver.service.cart.my_interface.IBillService;
import com.example.tmdtserver.service.product_service.my_interface.IProductService;
import com.example.tmdtserver.service.voucher_service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillDetailRepository billDetailRepository;
    @Autowired
    private IBillRepository billRepository;
    @Autowired
    private IProductService productService;
    @Autowired
    private IVoucherService voucherService;
    @Override
    public Page<Bill> findALl(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public BillDetail createBillDetail(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public Page<BillDetail> showBillDetail(Long id,Pageable pageable) {
        return billDetailRepository.showBillDetail(id, pageable);
    }

    @Override
    public Page<Bill> showAllBill(Long id, Pageable pageable) {
        return billRepository.showAllBill(id, pageable);
    }

    @Override
    public void removeBillDetailUses(Long idBill) {
        billDetailRepository.removeBillDetailUses(idBill);
    }

    @Override
    public Page<BillDetail> showBillOfShop(Long id, Pageable pageable) {
        return billDetailRepository.showBillOfShop(id,pageable);
    }

    @Override
    public void updateStatusBill2(Long id, BillDetail billDetail) {
        Product productUpdate = billDetail.getProduct();
        productUpdate.setQuantity(productUpdate.getQuantity() - billDetail.getQuantity());
        productService.save(productUpdate);
        billDetailRepository.updateStatusBill2(id);
    }

    @Override
    public void updateStatusBillById4(Long id) {
        billDetailRepository.updateStatusBillById4(id);
    }
}
