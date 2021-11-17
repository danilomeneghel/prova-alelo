package api.controller;

import api.ApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProductControllerTest extends ApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup( productController ).build();
    }

    @Test
    @DisplayName("Test POST Product")
    public void testPOSTProduct() throws Exception {
        String data = "{\"id\": \"1\", \"name\": \"Produto 01\", \"description\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

        this.mockMvc.perform( MockMvcRequestBuilders.post( "/products" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( data )
                        .accept( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isCreated() );
    }

    @Test
    @DisplayName("Test GET All Products")
    public void testGETProducts() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders.get( "/products" ) )
                .andExpect( MockMvcResultMatchers.status().isOk() );
    }

    @Test
    @DisplayName("Test GET Product")
    public void testGETProduct() throws Exception {
        String data = "{\"id\": \"2\", \"name\": \"Produto 02\", \"description\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

        this.mockMvc.perform( MockMvcRequestBuilders.post( "/products" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( data )
                        .accept( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isCreated() );

        this.mockMvc.perform( MockMvcRequestBuilders.get( "/products/{id}", "2" ) )
                .andExpect( MockMvcResultMatchers.status().isOk() );
    }

    @Test
    @DisplayName("Test PUT Product")
    public void testPUTProduct() throws Exception {
        String created = "{\"id\": \"3\", \"name\": \"Produto 03\", \"description\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

        this.mockMvc.perform( MockMvcRequestBuilders.post( "/products" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( created )
                        .accept( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isCreated() );

        String updated = "{\"id\": \"3\", \"name\": \"Produto 03 Alterado\", \"description\": \"Descrição Teste Alterada\", \"status\": \"ATIVO\"}";

        this.mockMvc.perform( MockMvcRequestBuilders.put( "/products/{id}", "3" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( updated )
                        .accept( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isOk() );
    }

    @Test
    @DisplayName("Test DELETE Product")
    public void testDELETEProduct() throws Exception {
        String data = "{\"id\": \"1\", \"name\": \"Produto 04\", \"description\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

        this.mockMvc.perform( MockMvcRequestBuilders.post( "/products" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( data )
                        .accept( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isCreated() );

        this.mockMvc.perform( MockMvcRequestBuilders.delete( "/products/{id}", "1" ) )
                .andExpect( MockMvcResultMatchers.status().isNoContent() );
    }

}
