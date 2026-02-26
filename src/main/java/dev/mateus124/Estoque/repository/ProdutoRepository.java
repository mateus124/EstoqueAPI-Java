package dev.mateus124.Estoque.repository;
import dev.mateus124.Estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
