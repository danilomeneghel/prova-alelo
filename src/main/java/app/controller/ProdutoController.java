package app.controller;

import app.entity.Produto;
import app.service.ProdutoService;
import app.util.CustomErrorType;
import app.util.RecordNotFoundException;
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

@Api(value = "API REST Produtos", tags = "produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@ApiOperation(value = "Lista todos os Produtos")
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.findAllByOrderByNomeAsc();

		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@ApiOperation(value = "Pega um Produto")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> listarProdutoPorId(@PathVariable("id") Long id) throws RecordNotFoundException {
		Produto produto = produtoService.findProdutoById(id);
		validarProduto(id);

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@ApiOperation(value = "Cria o Produto")
	@PostMapping
	public ResponseEntity<?> criarProduto(@RequestBody Produto produto) throws RecordNotFoundException {
		if (produtoService.isProdutoExist(produto)) {
			return new ResponseEntity<Object>(new CustomErrorType("Produto com nome " + produto.getNome() + " já existe."), HttpStatus.CONFLICT);
		}
		Produto produtoNovo = produtoService.save(produto);

		return new ResponseEntity<Produto>(produtoNovo, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza o Produto")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable("id") Long id, @RequestBody Produto produto) throws RecordNotFoundException {
		validarProduto(id);
		Produto produtoAlterado = produtoService.update(id, produto);

		return new ResponseEntity<Produto>(produtoAlterado, HttpStatus.OK);
	}

	@ApiOperation(value = "Exclui o Produto")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Produto> excluirProduto(@PathVariable("id") Long id) throws RecordNotFoundException {
		validarProduto(id);
		produtoService.deleteProdutoById(id);

		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Object> validarProduto(Long id) throws RecordNotFoundException {
		if (produtoService.findProdutoById(id) == null) {
			return new ResponseEntity<Object>(new CustomErrorType("Produto com id " + id + " não encontrado."), HttpStatus.NOT_FOUND);
		}
		return null;
	}

}
