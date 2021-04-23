package app.service;

import app.entity.Produto;
import app.repository.ProdutoRepository;
import app.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto findProdutoById(Long id) throws RecordNotFoundException {
        Optional<Produto> produto = repository.findById(id);

        if (repository.existsById(id)) {
            return produto.get();
        } else {
            throw new RecordNotFoundException("ID não encontrado.");
        }
    }

    public List<Produto> findAllByOrderByTituloAsc() {
        return repository.findAllByOrderByTituloAsc();
    }
    
    public Produto findByTitulo(String titulo) {
        return repository.findByTitulo(titulo);
    }

    public void save(Produto produto) throws RecordNotFoundException {
        repository.save(produto);
    }

    public Produto update(Long id, Produto produto) throws RecordNotFoundException {
        if (repository.existsById(id)) {
            produto.setId(id);
            produto.setTitulo(produto.getTitulo());
            produto.setDescricao(produto.getDescricao());
            produto.setStatus(produto.getStatus());
            produto = repository.save(produto);
        } else {
            throw new RecordNotFoundException("ID não encontrado.");
        }
        return produto;
    }

    public void deleteProdutoById(Long id) throws RecordNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID não encontrado.");
        }
    }

    public void deleteAllProdutos() {
        repository.deleteAll();
    }

    public boolean isProdutoExist(Produto produto) {
        return findByTitulo(produto.getTitulo()) != null;
    }

}
