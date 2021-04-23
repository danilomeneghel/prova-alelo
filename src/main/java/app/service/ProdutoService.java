package app.service;

import app.entity.Produto;
import app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto findProdutoById(Long id) {
        return repository.findById(id).orElse(new Produto());
    }

    public List<Produto> findAllByOrderByTituloAsc() {
        return repository.findAllByOrderByTituloAsc();
    }
    
    public Produto findByTitulo(String titulo) {
        return repository.findByTitulo(titulo);
    }

    public void save(Produto produto) {
        repository.save(produto);
    }

    public void deleteProdutoById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllProdutos() {
        repository.deleteAll();
    }

    public boolean isProdutoExist(Produto produto) {
        return findByTitulo(produto.getTitulo()) != null;
    }

}
