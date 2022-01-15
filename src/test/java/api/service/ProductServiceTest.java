package api.service;

import api.ApplicationTests;
import api.entity.Product;
import api.enums.ProductStatus;
import api.exception.RecordNotFoundException;
import api.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest extends ApplicationTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("Cria o Produto com sucesso")
    void testSaveProduct() {
        Product mockProduct = Product.builder()
                .name("Produto 01")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

        Product saveProduct = productService.save(mockProduct);

        Assertions.assertNotNull(saveProduct);
        assertEquals(mockProduct, saveProduct);
    }

    @Test
    @DisplayName("Localiza todos os Produtos por Status com sucesso")
    void testFindAllByStatus() {
        List<Product> mockListProducts = Stream.of(
                        Product.builder()
                                .name("Produto 02")
                                .description("Descrição Teste")
                                .status(ProductStatus.ATIVO)
                                .build(),
                        Product.builder()
                                .name("Produto 03")
                                .description("Descrição Teste")
                                .status(ProductStatus.ATIVO)
                                .build())
                .collect(Collectors.toList());

        when(productRepository.findAllByStatusOrderByNameAsc(ProductStatus.ATIVO))
                .thenReturn(mockListProducts);

        List<Product> listProducts = productService.findAllByStatusOrderByNameAsc(ProductStatus.ATIVO);

        Assertions.assertNotNull(listProducts);
        assertEquals(mockListProducts, listProducts);
    }

    @Test
    @DisplayName("Localiza todos os Produtos por Status inválido")
    void testFindAllByStatusInvalid() {
        List<Product> mockListProducts = Stream.of(
                        Product.builder()
                                .name("Produto 04")
                                .description("Descrição Teste")
                                .status(ProductStatus.ATIVO)
                                .build(),
                        Product.builder()
                                .name("Produto 05")
                                .description("Descrição Teste")
                                .status(ProductStatus.ATIVO)
                                .build())
                .collect(Collectors.toList());

        when(productRepository.findAllByStatusOrderByNameAsc(ProductStatus.ATIVO))
                .thenReturn(mockListProducts);

        List<Product> listProducts = productService.findAllByStatusOrderByNameAsc(ProductStatus.INATIVO);

        assertNotEquals(mockListProducts, listProducts);
    }

    @Test
    @DisplayName("Localiza o Produto por ID com sucesso")
    void testFindProductById() throws RecordNotFoundException {
        Product mockProduct = Product.builder()
                .name("Produto 06")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product findProduct = productService.findProductById(1L);

        Assertions.assertNotNull(findProduct);
        assertEquals(mockProduct, findProduct);
    }

    @Test
    @DisplayName("Localiza o Produto por ID inválido")
    void testFindProductByIdInvalid() throws RecordNotFoundException {
        Product mockProduct = Product.builder()
                .name("Produto 07")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        try {
            productService.findProductById(2L);
        } catch (Exception e) {
            assertEquals("ID não encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Altera o Produto por ID com sucesso")
    void testUpdateProduct() throws RecordNotFoundException {
        Product mockProduct = Product.builder()
                .name("Produto 08")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.existsById(1L)).thenReturn(true);

        mockProduct.setName("Produto Alterado");
        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

        Product updateProduct = productService.update(1L, mockProduct);

        Assertions.assertNotNull(updateProduct);
        assertEquals(mockProduct, updateProduct);
    }

    @Test
    @DisplayName("Altera o Produto por ID inválido")
    void testUpdateProductIdInvalid() {
        Product mockProduct = Product.builder()
                .name("Produto 09")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.existsById(1L)).thenReturn(true);

        mockProduct.setName("Produto Alterado");
        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

        try {
            productService.update(2L, mockProduct);
        } catch (Exception e) {
            assertEquals("ID não encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Exclui o Produto por ID com sucesso")
    void testDeleteProductById() throws RecordNotFoundException {
        Product mockProduct = Product.builder()
                .name("Produto 10")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.existsById(1L)).thenReturn(true);

        String deleteProduct = productService.deleteProductById(1L);

        Assertions.assertNotNull(deleteProduct);
        assertEquals("Produto excluído com sucesso.", deleteProduct);
    }

    @Test
    @DisplayName("Exclui o Produto por ID inválido")
    void testDeleteProductByIdInvalid() {
        Product mockProduct = Product.builder()
                .name("Produto 11")
                .description("Descrição Teste")
                .status(ProductStatus.ATIVO)
                .build();

        when(productRepository.existsById(1L)).thenReturn(true);

        try {
            productService.deleteProductById(2L);
        } catch (Exception e) {
            assertEquals("ID não encontrado.", e.getMessage());
        }
    }

}
