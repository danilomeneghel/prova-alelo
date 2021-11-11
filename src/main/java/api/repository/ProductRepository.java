package api.repository;

import api.entity.Product;
import api.enums.ProductStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository< Product, Long > {

    Product findByName( String name );

    List< Product > findAllByOrderByNameAsc();

    List< Product > findAllByStatusOrderByNameAsc( ProductStatus status );

}
