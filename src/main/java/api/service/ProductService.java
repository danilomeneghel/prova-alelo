package api.service;

import api.entity.Product;
import api.enums.ProductStatus;
import api.exception.RecordNotFoundException;
import api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById( Long id ) throws RecordNotFoundException {
        Optional< Product > product = productRepository.findById( id );

        if( product.isPresent() ) {
            return product.get();
        }
        throw new RecordNotFoundException( "ID não encontrado." );
    }

    public List< Product > findAllByOrderByNameAsc() {
        return productRepository.findAllByOrderByNameAsc();
    }

    public List< Product > findAllByStatusOrderByNameAsc( ProductStatus status ) {
        return productRepository.findAllByStatusOrderByNameAsc( status );
    }

    public Product findByName( String name ) {
        return productRepository.findByName( name );
    }

    public Product save( Product product ) {
        return productRepository.save( product );
    }

    public Product update( Long id, Product product ) throws RecordNotFoundException {
        if( productRepository.existsById( id ) ) {
            product.setId( id );
            product.setName( product.getName() );
            product.setDescription( product.getDescription() );
            product.setStatus( product.getStatus() );
            product = productRepository.save( product );
            return product;
        }
        throw new RecordNotFoundException( "ID não encontrado." );
    }

    public void deleteProductById( Long id ) throws RecordNotFoundException {
        if( productRepository.existsById( id ) ) {
            productRepository.deleteById( id );
        } else {
            throw new RecordNotFoundException( "ID não encontrado." );
        }
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public boolean isProductExist( Product product ) {
        return findByName( product.getName() ) != null;
    }

}