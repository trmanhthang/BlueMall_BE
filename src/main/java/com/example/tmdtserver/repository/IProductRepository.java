package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Long> {
    //Hiển thị tất cả các sản phẩm có trong shop
    @Query(value = "select  p from Product p where p.shop.id = :id and p.status = true order by p.date desc ")
    Page<Product> showProductOfShop(@Param("id") Long id, Pageable pageable);
    @Query(value = "select  p from Product p where p.shop.id = :id ")
    Page<Product> showProductOfShopAll(@Param("id") Long id, Pageable pageable);

    // tìm kiếm sản phẩm trong 1 shop
    @Query(value = "select p from Product as p where p.shop.id = :id and p.category.id=:idCategory and p.status=true ")
    Page<Product> findProductShopCategory(@Param("id") Long id,
                                          @Param("idCategory") Long idCategory,
                                          Pageable pageable);

    //tìm kiếm sản phẩm trong 1 shop có tên không có category
    @Query(value = "select p from Product as p where p.shop.id = :id and p.name like :name and p.status=true ")
    Page<Product> findProductShopByNameNoCategory(@Param("id") Long id,
                                          @Param("name") String name,
                                          Pageable pageable);

    //tìm kiếm sản phẩm trong 1 shop có tên có category
    @Query(value = "select p from Product as p where p.shop.id = :id and p.name like :name and p.category.id = :idCategory and p.status=true ")
    Page<Product> findProductShopByNameCategory(@Param("id") Long id,
                                                  @Param("name") String name,
                                                  @Param("idCategory") Long idCategory,
                                                  Pageable pageable);

    //Hiển thị tất cả sản phẩm của shop trên trang chủ:
    @Query(value = "select p from Product p where p.status =true order by p.date desc ")
    Page<Product> showAllProduct(Pageable pageable);


    //    Xóa sản phẩm có trong shop
    @Modifying
    @Query(value = "UPDATE Product p  set p.status = false where p.id = :id")
    void deleteProductByIdProduct(@Param("id") Long id);

    // FindByAll
    @Query(value = "select p from Product as p inner join Category c on p.category.id = c.id inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name " +
            "and p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoCity(
            Pageable pageable,
            @Param("name") String name,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p where p.name like :name " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoCategory(Pageable pageable,
                                      @Param("name") String name,
                                      @Param("priceMin") Double priceMin,
                                      @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as  p where p.name like :name and p.status=true ")
    Page<Product> showProductBySearchName(Pageable pageable,
                                          @Param("name") String name);

    @Query(value = "select p from Product as p inner join Category c on p.category.id = c.id inner join Shop s on p.shop.id = s.id " +
            "where p.shop.city.name=:nameCity1 " +
            "or p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllCity1(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p inner join Category c on p.category.id = c.id inner join Shop s on p.shop.id = s.id " +
            "where p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 " +
            "or p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllCity2(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p inner join Category c on p.category.id = c.id inner join Shop s on p.shop.id = s.id " +
            "where p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or p.shop.city.name=:nameCity3 " +
            "or p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllCity3(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p inner join Category c on p.category.id = c.id inner join Shop s on p.shop.id = s.id " +
            "where p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or p.shop.city.name=:nameCity3 or p.shop.city.name=:nameCity4 " +
            "or p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllCity4(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("nameCity4") String nameCity4,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p inner join Category c on p.category.id = c.id inner join Shop s on p.shop.id = s.id " +
            "where p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllCity5(
            Pageable pageable,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    // update search


    // no name no category
    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.shop.city.name=:nameCity1 " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameNoCategoryCity1(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2) " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameNoCategoryCity2(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or  p.shop.city.name=:nameCity3) " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameNoCategoryCity3(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or  p.shop.city.name=:nameCity3  or  p.shop.city.name=:nameCity4) " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameNoCategoryCity4(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("nameCity4") String nameCity4,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameNoCategoryCity5(
            Pageable pageable,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    // no name - category

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.shop.city.name=:nameCity1 " +
            "and p.category.id= :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameCategoryCity1(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2) " +
            "and p.category.id= :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameCategoryCity2(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or  p.shop.city.name=:nameCity3) " +
            "and p.category.id= :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameCategoryCity3(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or  p.shop.city.name=:nameCity3  or  p.shop.city.name=:nameCity4) " +
            "and p.category.id= :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNoNameCategoryCity4(
            Pageable pageable,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("nameCity4") String nameCity4,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.price between :priceMin and :priceMax " +
            "and p.category.id= :idCategory " +
            "and p.status=true ")
    Page<Product> findByAllNoNameCategoryCity5(
            Pageable pageable,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    //search all name - no category

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  " +
            "and p.shop.city.name=:nameCity1 " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameNoCategoryCity1(
            Pageable pageable,
            @Param("name") String name,
            @Param("nameCity1") String nameCity1,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  " +
            "and (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2)" +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameNoCategoryCity2(
            Pageable pageable,
            @Param("name") String name,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  " +
            "and (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or p.shop.city.name=:nameCity3)" +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameNoCategoryCity3(
            Pageable pageable,
            @Param("name") String name,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  " +
            "and (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or p.shop.city.name=:nameCity3 or p.shop.city.name=:nameCity4)" +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameNoCategoryCity4(
            Pageable pageable,
            @Param("name") String name,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("nameCity4") String nameCity4,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            "where p.name like :name  " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameNoCategoryNoCity(
            Pageable pageable,
            @Param("name") String name,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);


    //  findByAllNameCategoryCity1

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  and p.category.id = :idCategory " +
            "and p.shop.city.name=:nameCity1 " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameCategoryCity1(
            Pageable pageable,
            @Param("name") String name,
            @Param("idCategory") Long idCategory,
            @Param("nameCity1") String nameCity1,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  and p.category.id = :idCategory " +
            "and (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2)" +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameCategoryCity2(
            Pageable pageable,
            @Param("name") String name,
            @Param("idCategory") Long idCategory,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  and p.category.id = :idCategory " +
            "and (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or p.shop.city.name=:nameCity3)" +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameCategoryCity3(
            Pageable pageable,
            @Param("name") String name,
            @Param("idCategory") Long idCategory,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            " inner join Shop s on p.shop.id = s.id " +
            "where p.name like :name  and p.category.id = :idCategory " +
            "and (p.shop.city.name=:nameCity1 or p.shop.city.name=:nameCity2 or p.shop.city.name=:nameCity3 or p.shop.city.name=:nameCity4)" +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameCategoryCity4(
            Pageable pageable,
            @Param("name") String name,
            @Param("idCategory") Long idCategory,
            @Param("nameCity1") String nameCity1,
            @Param("nameCity2") String nameCity2,
            @Param("nameCity3") String nameCity3,
            @Param("nameCity4") String nameCity4,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p " +
            "where p.name like :name  and p.category.id = :idCategory " +
            "and p.price between :priceMin and :priceMax " +
            "and p.status=true ")
    Page<Product> findByAllNameCategoryNoCity(
            Pageable pageable,
            @Param("name") String name,
            @Param("idCategory") Long idCategory,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

    @Query(value = "select p from Product as p where p.price between :priceMin and :priceMax")
    Page<Product> findByPriceBetween(
            Pageable pageable,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax);

//    HIển thị số lượng sản phẩm bán ra
    @Query(value = "select sum(bd.quantity) from BillDetail bd where bd.product.id = :id")
    Double totalQuantity(@Param("id")Long id);
}
