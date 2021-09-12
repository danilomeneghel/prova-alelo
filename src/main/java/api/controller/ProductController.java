package api.controller;

import api.entity.Product;
import api.service.ProductService;
import api.util.CustomErrorType;
import api.util.RecordNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api( value = "API REST Produtos", tags = "products" )
@RestController
@RequestMapping( "/products" )
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation( value = "Lista todos os Produtos" )
    @GetMapping
    public ResponseEntity< List< Product > > listProducts() {
        List< Product > products = productService.findAllByOrderByNameAsc();

        return new ResponseEntity< List< Product > >( products, HttpStatus.OK );
    }

    @ApiOperation( value = "Pega um Product" )
    @GetMapping( value = "/{id}" )
    public ResponseEntity< Product > getProductById( @PathVariable( "id" ) Long id ) throws RecordNotFoundException {
        Product product = productService.findProductById( id );
        validateProductPorId( id );

        return new ResponseEntity< Product >( product, HttpStatus.OK );
    }

    @ApiOperation( value = "Cria o Product" )
    @PostMapping
    public ResponseEntity< Product > createProduct( @RequestBody Product product ) throws RecordNotFoundException {
        validateProductByName( product );
        Product newProduct = productService.save( product );

        return new ResponseEntity< Product >( newProduct, HttpStatus.CREATED );
    }

    @ApiOperation( value = "Atualiza o Product" )
    @PutMapping( value = "/{id}" )
    public ResponseEntity< Product > updateProduct( @PathVariable( "id" ) Long id, @RequestBody Product product ) throws RecordNotFoundException {
        validateProductPorId( id );
        Product changedProduct = productService.update( id, product );

        return new ResponseEntity< Product >( changedProduct, HttpStatus.OK );
    }

    @ApiOperation( value = "Exclui o Product" )
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

    public ResponseEntity< Object > validateProductByName( Product product ) {
        if( productService.isProductExist( product ) ) {
            return new ResponseEntity< Object >( new CustomErrorType( "Produto com nome " + product.getName() + " já existe." ), HttpStatus.CONFLICT );
        }
        return null;
    }

}
