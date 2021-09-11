package api.service;

import api.entity.Product;
import api.repository.ProductRepository;
import api.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    @Autowired
    public ProductService( ProductRepository repository ) {
        this.repository = repository;
    }

    public Product findProductById( Long id ) throws RecordNotFoundException {
        Optional< Product > product = repository.findById( id );

        if( repository.existsById( id ) ) {
            return product.get();
        } else {
            throw new RecordNotFoundException( "ID não encontrado." );
        }
    }

    public List< Product > findAllByOrderByNameAsc() {
        return repository.findAllByOrderByNameAsc();
    }

    public Product findByName( String name ) {
        return repository.findByName( name );
    }

    public Product save( Product product ) {
        return repository.save( product );
    }

    public Product update( Long id, Product product ) throws RecordNotFoundException {
        if( repository.existsById( id ) ) {
            product.setId( id );
            product.setName( product.getName() );
            product.setDescription( product.getDescription() );
            product.setStatus( product.getStatus() );
            product = repository.save( product );
        } else {
            throw new RecordNotFoundException( "ID não encontrado." );
        }
        return product;
    }

    public void deleteProductById( Long id ) throws RecordNotFoundException {
        if( repository.existsById( id ) ) {
            repository.deleteById( id );
        } else {
            throw new RecordNotFoundException( "ID não encontrado." );
        }
    }

    public void deleteAllProducts() {
        repository.deleteAll();
    }

    public boolean isProductExist( Product product ) {
        return findByName( product.getName() ) != null;
    }

}
