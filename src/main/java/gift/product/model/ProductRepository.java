package gift.product.model;

import gift.product.model.dto.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndIsActiveTrue(Long id);

    List<Product> findAllByIsActiveTrue();

    // 특정 ID의 Product와 관련 Wish 개수 조회
    @Query("SELECT p, COUNT(w) FROM Product p LEFT JOIN Wish w ON w.product = p WHERE p.id = :id AND p.isActive = true GROUP BY p")
    Optional<Object[]> findProductByIdWithWishCount(@Param("id") Long id);

    // 모든 상품의 상품 정보와 Wish 개수 조회
    @Query("SELECT p, COUNT(w) FROM Product p LEFT JOIN Wish w ON w.product = p WHERE p.isActive = true GROUP BY p")
    List<Object[]> findAllActiveProductsWithWishCount();
}
