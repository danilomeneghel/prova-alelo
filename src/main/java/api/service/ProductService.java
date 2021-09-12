package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import api.entity.Product;
import api.repository.ProductRepository;
import api.util.RecordNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById( Long id ) throws RecordNotFoundException {
        Optional< Product > product = productRepository.findById( id );

        if( productRepository.existsById( id ) ) {
            return product.get();
        } else {
            throw new RecordNotFoundException( "ID não encontrado." );
        }
    }

    public List< Product > findAllByOrderByNameAsc() {
        return productRepository.findAllByOrderByNameAsc();
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
        } else {
            throw new RecordNotFoundException( "ID não encontrado." );
        }
        return product;
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
