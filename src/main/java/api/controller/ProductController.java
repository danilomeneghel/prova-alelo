package api.controller;

import api.entity.Product;
import api.enums.ProductStatus;
import api.exception.CustomErrorType;
import api.exception.RecordNotFoundException;
import api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/products" )
@Validated
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity< List< Product > > listProducts() {
        List< Product > products = productService.findAllByOrderByNameAsc();

        return new ResponseEntity< List< Product > >( products, HttpStatus.OK );
    }

    @GetMapping( value = "/status/{status}" )
    public ResponseEntity< List< Product > > listProductsByStatus( @PathVariable( "status" ) String status ) {
        List< Product > products = productService.findAllByStatusOrderByNameAsc( ProductStatus.fromValue( status ) );

        return new ResponseEntity< List< Product > >( products, HttpStatus.OK );
    }

    @GetMapping( value = "/{id}" )
    public ResponseEntity< Product > getProductById( @PathVariable( "id" ) Long id ) throws RecordNotFoundException {
        Product product = productService.findProductById( id );
        validateProductPorId( id );

        return new ResponseEntity< Product >( product, HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity< Object > createProduct( @Valid @RequestBody Product product ) throws RecordNotFoundException {
        if( productService.isProductExist( product ) ) {
            return new ResponseEntity< Object >( new CustomErrorType( "Produto com nome " + product.getName() + " já existe." ), HttpStatus.CONFLICT );
        }
        Product newProduct = productService.save( product );

        return new ResponseEntity< Object >( newProduct, HttpStatus.CREATED );
    }

    @PutMapping( value = "/{id}" )
    public ResponseEntity< Product > updateProduct( @PathVariable( "id" ) Long id, @Valid @RequestBody Product product ) throws RecordNotFoundException {
        validateProductPorId( id );
        Product changedProduct = productService.update( id, product );

        return new ResponseEntity< Product >( changedProduct, HttpStatus.OK );
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity< Product > deleteProduct( @PathVariable( "id" ) Long id ) throws RecordNotFoundException {
        validateProductPorId( id );
        productService.deleteProductById( id );

        return new ResponseEntity< Product >( HttpStatus.NO_CONTENT );
    }

    public ResponseEntity< Object > validateProductPorId( Long id ) throws RecordNotFoundException {
        if( productService.findProductById( id ) == null ) {
            return new ResponseEntity< Object >( new CustomErrorType( "Produto com id " + id + " não encontrado." ), HttpStatus.NOT_FOUND );
        }
        return null;
    }

}