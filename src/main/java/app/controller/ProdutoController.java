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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Api(value = "API REST Produto")
@RestController
@RequestMapping("/api")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@ApiOperation(value = "Lista todos os Produtos")
	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listAllProdutos() {
		List<Produto> produtos = produtoService.findAllByOrderByNomeAsc();

		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@ApiOperation(value = "Pega um Produto")
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduto(@PathVariable("id") Long id) throws RecordNotFoundException {
		Produto produto = produtoService.findProdutoById(id);
		if (produto == null) {
			return new ResponseEntity<Object>(new CustomErrorType("Produto com id " + id + " não encontrado."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@ApiOperation(value = "Cria o Produto")
	@RequestMapping(value = "/produto", method = RequestMethod.POST)
	public ResponseEntity<?> createProduto(@RequestBody Produto produto, UriComponentsBuilder ucBuilder) throws RecordNotFoundException {
		if (produtoService.isProdutoExist(produto)) {
			return new ResponseEntity<Object>(new CustomErrorType("Produto com nome " + produto.getNome() + " já existe."), HttpStatus.CONFLICT);
		}

		Produto produtoNovo = produtoService.save(produto);
		return new ResponseEntity<Produto>(produtoNovo, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza o Produto")
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto) throws RecordNotFoundException {
		if (produtoService.findProdutoById(id) == null) {
			return new ResponseEntity<Object>(new CustomErrorType("Produto com id " + id + " não encontrado."), HttpStatus.NOT_FOUND);
		}

		Produto produtoAlterado = produtoService.update(id, produto);
		return new ResponseEntity<Produto>(produtoAlterado, HttpStatus.OK);
	}

	@ApiOperation(value = "Exclui o Produto")
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id) throws RecordNotFoundException {
		if (produtoService.findProdutoById(id) == null) {
			return new ResponseEntity<Object>(new CustomErrorType("Produto com id " + id + " não encontrado."), HttpStatus.NOT_FOUND);
		}

		produtoService.deleteProdutoById(id);
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

}
