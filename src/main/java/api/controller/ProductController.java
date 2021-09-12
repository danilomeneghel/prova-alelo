package api.controller;

import api.entity.Product;
import api.exception.CustomErrorType;
import api.exception.RecordNotFoundException;
import api.service.ProductService;
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

@RestController
@RequestMapping( "/products" )
@Api( value = "API REST Produtos", tags = "products" )
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation( value = "Lista todos os Produtos" )
    @GetMapping
    public ResponseEntity< List< Product > > listProducts() {
        List< Product > products = productService.findAllByOrderByNameAsc();

        return new ResponseEntity< List< Product > >( products, HttpStatus.OK );
    }

    @ApiOperation( value = "Pega um Produto" )
    @GetMapping( value = "/{id}" )
    public ResponseEntity< Product > getProductById( @PathVariable( "id" ) Long id ) throws RecordNotFoundException {
        Product product = productService.findProductById( id );
        validateProductPorId( id );

        return new ResponseEntity< Product >( product, HttpStatus.OK );
    }

    @ApiOperation( value = "Cria o Produto" )
    @PostMapping
    public ResponseEntity< Object > createProduct( @RequestBody Product product ) throws RecordNotFoundException {
        if( productService.isProductExist( product ) ) {
            return new ResponseEntity< Object >( new CustomErrorType( "Produto com nome " + product.getName() + " já existe." ), HttpStatus.CONFLICT );
        }
        Product newProduct = productService.save( product );

        return new ResponseEntity< Object >( newProduct, HttpStatus.CREATED );
    }

    @ApiOperation( value = "Atualiza o Produto" )
    @PutMapping( value = "/{id}" )
    public ResponseEntity< Product > updateProduct( @PathVariable( "id" ) Long id, @RequestBody Product product ) throws RecordNotFoundException {
        validateProductPorId( id );
        Product changedProduct = productService.update( id, product );

        return new ResponseEntity< Product >( changedProduct, HttpStatus.OK );
    }

    @ApiOperation( value = "Exclui o Produto" )
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