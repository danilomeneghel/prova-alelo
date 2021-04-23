package app.repository;

import app.entity.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    Produto findByTitulo(String titulo);
    List<Produto> findAllByOrderByTituloAsc();

}
