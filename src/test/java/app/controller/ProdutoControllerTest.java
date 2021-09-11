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
	public void testPOSTProduto() throws Exception {
		String data = "{\"id\": \"1\", \"nome\": \"Produto 01\", \"descricao\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(data)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testGETProdutos() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/produtos"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETProduto() throws Exception {
		String data = "{\"id\": \"2\", \"nome\": \"Produto 02\", \"descricao\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(data)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/produtos/{id}", "2"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPUTProduto() throws Exception {
		String created = "{\"id\": \"3\", \"nome\": \"Produto 03\", \"descricao\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(created)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		String updated = "{\"id\": \"3\", \"nome\": \"Produto 03 Alterado\", \"descricao\": \"Descrição Teste Alterada\", \"status\": \"ATIVO\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/produtos/{id}", "3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updated)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDELETEProduto() throws Exception {
		String data = "{\"id\": \"1\", \"nome\": \"Produto 04\", \"descricao\": \"Descrição Teste\", \"status\": \"ATIVO\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(data)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", "1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
