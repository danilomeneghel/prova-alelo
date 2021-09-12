package api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import api.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository< Product, Long > {

    Product findByName( String name );

    List< Product > findAllByOrderByNameAsc();

}
