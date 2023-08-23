package com.example.tmdtserver.controller.shop;

import com.example.tmdtserver.model.*;
import com.example.tmdtserver.model.product.Product;
import com.example.tmdtserver.model.shop.Shop;
import com.example.tmdtserver.service.category_service.ICategoryService;
import com.example.tmdtserver.service.product_service.my_interface.IProductService;
import com.example.tmdtserver.service.shop_service.my_interface.IShopService;
import com.example.tmdtserver.service.voucher_service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/home/shops")
public class ShopController {
    @Autowired
    private IShopService shopService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IVoucherService voucherService;

    @Autowired
    private ICategoryService categoryService;

    //    Hiển thị tất cả các shop đang có
    @GetMapping
    public ResponseEntity<Page<Shop>> findAllShop(@PageableDefault(size = 3)Pageable pageable){
        Page<Shop> shops = shopService.findALl(pageable);
        if (shops.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shops,HttpStatus.OK);
    }

//    Tạo mới 1 shop

    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop){
        return new ResponseEntity<>(shopService.save(shop),HttpStatus.CREATED);
    }

    //Truy xuat 1 shop
    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable("id")Long id){
        Shop shop = shopService.findByIdAccount(id);
        if (shop==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shop,HttpStatus.OK);
    }

//    Tạo voucher của shop
    @PostMapping("/{id}/voucher")
    public ResponseEntity<Voucher> saveVoucher(@PathVariable("id")Long id,
                                               @RequestBody Voucher voucher){
        Shop shop = shopService.findByIdAccount(id);
        voucher.setShop(shop);
        return new ResponseEntity<>(voucherService.save(voucher),HttpStatus.CREATED);
    }

//    Show all voucher id là id của shop
    @GetMapping("/voucher/{id}")
    public ResponseEntity<Page<Voucher>> findAllVoucher(@PathVariable("id")Long id,
                                                        @PageableDefault(size = 5)Pageable pageable){
        Page<Voucher> vouchers = voucherService.showAllVoucher(pageable, id);
        return new ResponseEntity<>(vouchers, HttpStatus.OK);

    }


    // Truy xuất Category của 1 shop
    @GetMapping("/{id}/categories")
    public ResponseEntity<List<Category>> findCategoryOfShop(@PathVariable("id")Long id){
        Shop shop = shopService.findByIdAccount(id);
        List<Category> categories = categoryService.findCategoryOfShop(shop.getId());
        if (categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    //Truy xuất thông tin chi tiết của 1 shop theo id product
    @GetMapping("/product/{id}")
    public ResponseEntity<Shop> findByIdProduct(@PathVariable("id")Long id){
        Product product = productService.findById(id);
        Shop shop = shopService.findById(product.getShop().getId());
        if (shop == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shop,HttpStatus.OK);
    }
    //Truy xuất thông tin chi tiết của 1 shop theo id shop
    @GetMapping("/information/{id}")
    public ResponseEntity<Shop> findByIdShop(@PathVariable("id")Long id){
        Shop shop = shopService.findById(id);
        if (shop == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shop,HttpStatus.OK);
    }

    // Hủy sản phẩm trên shop của mình (id của product)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id")Long id){
        Product product = productService.findById(id);
        if (product!=null){
            productService.remove(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Hiển thị danh sách các khách hàng đã mua ở shop
    @GetMapping("/users/{idShop}")
    public ResponseEntity<List<AccountConvert>> findTotalOfAccountBuyShop(@PathVariable("idShop")Long idShop){
        Map<Account,Double> mapAccount = shopService.findTotalOfAccountBuyShop(idShop);
        List<AccountConvert> accountConverts = shopService.convertMapToList(mapAccount);
        return new ResponseEntity<>(accountConverts,HttpStatus.OK);
    }
}

