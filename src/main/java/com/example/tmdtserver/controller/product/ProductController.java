package com.example.tmdtserver.controller.product;

import com.example.tmdtserver.model.product.EvaluateDetail;
import com.example.tmdtserver.model.product.Product;
import com.example.tmdtserver.model.Search;
import com.example.tmdtserver.model.shop.Shop;
import com.example.tmdtserver.service.product_service.my_interface.IProductService;
import com.example.tmdtserver.service.shop_service.my_interface.IShopService;
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
@RequestMapping("/home/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IShopService shopService;

    //Hiển thị tất cả sản phẩm trong 1 shop.
    @GetMapping("/shop/{id}")
    public ResponseEntity<Page<Product>> listProductOfShop(@PathVariable("id") Long id,
                                                           @PageableDefault(size = 8) Pageable pageable) {
        Shop shop = shopService.findByIdAccount(id);
        Page<Product> products = productService.showProductOfShop(shop.getId(), pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/search-shop/{id}")
    ResponseEntity<Page<Product>> showSllProductSearch(@PathVariable("id") Long id,
                                                       @PageableDefault(size = 5) Pageable pageable,
                                                       @RequestBody Search search) {
        Shop shop = shopService.findByIdAccount(id);
        Page<Product> products = productService.showProductShopBySearch(shop.getId(),pageable, search);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/shop-crud/{id}")
    public ResponseEntity<Page<Product>> listProductOfShopCrud(@PathVariable("id") Long id,
                                                               @PageableDefault(size = 5) Pageable pageable) {
        Shop shop = shopService.findByIdAccount(id);
        Page<Product> products = productService.showProductOfShop(shop.getId(), pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/shop-crud-all/{id}")
    public ResponseEntity<Page<Product>> listProductOfShopCrudAll(@PathVariable("id") Long id,
                                                               @PageableDefault() Pageable pageable) {
        Shop shop = shopService.findByIdAccount(id);
        Page<Product> products = productService.showProductOfShopAll(shop.getId(), pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // tim kiem san pham theo ten cua shop trong trang crud
    @PostMapping("/shop-crud/{id}")
    public ResponseEntity<Page<Product>> listProductSearchName(@PathVariable("id") Long id,
                                                               @RequestBody Search search,
                                                               @PageableDefault(size = 5) Pageable pageable) {
        Shop shop = shopService.findByIdAccount(id);
        Page<Product> products = productService.findProductShopByName(shop.getId(), search,pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //    Tạo mới 1 sản phẩm
    @PostMapping("/shop/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Shop shop = shopService.findByIdAccount(id);
        product.setShop(shop);
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    //    Hiển thị tất cả sản phẩm trên trang chủ
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(size = 8) Pageable pageable) {
        Page<Product> products = productService.findALl(pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //    Hiển thị chi tiết 1 sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        double total = product.getTotalQuantity();
        product.setTotalQuantity(total);
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }


    @PostMapping(value = "/search")
    ResponseEntity<Page<Product>> showProductBySearch(@PageableDefault(size = 8) Pageable pageable,
                                                      @RequestBody Search search) {
        Page<Product> products = productService.showProductBySearch(pageable, search);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PostMapping("/views")
    public ResponseEntity<Product> updateView(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }
//    @GetMapping(value = "/search-name/{name}")
//    ResponseEntity<Page<Product>> showProductBySearchName(@PageableDefault(size = 8) Pageable pageable,
//                                                      @PathVariable("name") String name) {
//
//        Page<Product> products = productService.showProductBySearchName(pageable, name);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    // Lưu đánh giá của 1 sản phẩm
    @PostMapping("rating")
    public ResponseEntity<EvaluateDetail> saveRating(@RequestBody EvaluateDetail evaluateDetail){
        return new ResponseEntity<>(productService.saveRating(evaluateDetail),HttpStatus.CREATED);
    }

    // Truy xuất tất cả đánh giá của 1 sản phẩm theo id sản phẩm
    @GetMapping("rating/{id}")
    private ResponseEntity<List<EvaluateDetail>> showRatingByIdProduct(@PathVariable("id")Long id){
        List<EvaluateDetail> evaluateDetails = productService.showRating(id);
        if (evaluateDetails.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evaluateDetails,HttpStatus.OK);
    }

    //HIển thị số lượng bán ra của 1 sản phẩm
    @GetMapping("/quantity/{id}")
    private ResponseEntity<Double> totalQuantity(@PathVariable("id")Long id){
        return new ResponseEntity<>(productService.totalQuantity(id),HttpStatus.OK);
    }
}
