package app.controller;

import app.ApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProdutoControllerTest extends ApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private ProdutoController produtoController;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
	}
	
	@Test
	public void testPOSTProcesso() throws Exception {
		String data = "{\"titulo\": \"Produto 001\", \"descricao\": \"Descrição Teste\", \"status\": \"ATIVO\"}";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/produto")
					.contentType(MediaType.APPLICATION_JSON)
					.content(data)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testGETprodutos() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/produto"))
					.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGETproduto() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/produto/{id}", "1"))
					.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPUTproduto() throws Exception {
		String data = "{\"titulo\": \"Produto 002\", \"descricao\": \"Descrição Teste2\", \"status\": \"ATIVO\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/produto/{id}", "1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(data)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDELETEproduto() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/produto/{id}", "1"))
					.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
}