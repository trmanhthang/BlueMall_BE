package com.example.tmdtserver.controller.bill;

import com.example.tmdtserver.model.bill.Bill;
import com.example.tmdtserver.model.bill.BillDetail;
import com.example.tmdtserver.service.cart.my_interface.IBillService;
import com.example.tmdtserver.service.product_service.my_interface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/home/bills")
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IProductService productService;

    @GetMapping("/{id}")
    private  ResponseEntity<Page<Bill>> showBill(@PathVariable("id")Long idAccount,
                                                 @PageableDefault(size = 9)Pageable pageable){
        return new ResponseEntity<>(billService.showAllBill(idAccount,pageable),HttpStatus.OK);
    }

    @GetMapping("/bill-detail/{id}")
    private  ResponseEntity<Page<BillDetail>> showBillDetail(@PathVariable("id")Long idAccount,
                                                             @PageableDefault(size = 5)Pageable pageable){
        return new ResponseEntity<>(billService.showBillDetail(idAccount,pageable),HttpStatus.OK);
    }

//    Tạo mới 1 bill
    @PostMapping("/create")
    private ResponseEntity<Bill> createBill (@RequestBody Bill bill){
        return new ResponseEntity<>(billService.save(bill), HttpStatus.CREATED);
    }

    //    Tạo mới 1 bill detail
    @PostMapping("/bill-detail/create")
    private ResponseEntity<BillDetail> createBillDetail (@RequestBody BillDetail billDetail){
        BillDetail billDetailCreate = billService.createBillDetail(billDetail);
        Bill bill = billDetailCreate.getBill();
        billService.save(bill);
        return new ResponseEntity<>( billDetailCreate, HttpStatus.CREATED);
    }

    //Hủy đơn hàng
    @PostMapping("/delete/{idBill}")
    private ResponseEntity<Bill> removeBillDetailUses(@PathVariable("idBill")Long id){
        Bill bill = billService.findById(id);
        if (bill==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            billService.removeBillDetailUses(id);
            return new ResponseEntity<>(bill,HttpStatus.OK);
        }
    }

    //Hiển thị các đơn hàng mà shop đã bán:
    @GetMapping("/shops/{idShop}")
    private ResponseEntity<Page<BillDetail>> showBillOfShop(@PathVariable("idShop")Long id,
                                                            @PageableDefault(size = 5)Pageable pageable){
        Page<BillDetail> billDetails = billService.showBillOfShop(id, pageable);
        if (billDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(billDetails,HttpStatus.OK);
    }

    //Khi shop xác nhận đặt hàng thành công
    @PostMapping("/update/status-bill/2/{idBill}")
    private ResponseEntity<Bill> updateStatusBill2(@PathVariable("idBill")Long id,
                                                   @RequestBody BillDetail billDetail){
        Bill bill = billService.findById(id);
        if (bill==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            billService.updateStatusBill2(id,billDetail);
            return new ResponseEntity<>(bill,HttpStatus.OK);
        }
    }

    // Khi khách hàng xác nhận đã nhận hàng thì chuyển trạng thái sang trạng thái đã nhận
    @PostMapping("/update/status-bill/4/{idBill}")
    private ResponseEntity<Bill> updateStatusBillById4(@PathVariable("idBill")Long id){
        Bill bill = billService.findById(id);
        if (bill==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            billService.updateStatusBillById4(id);
            return new ResponseEntity<>(bill,HttpStatus.OK);
        }
    }
}

