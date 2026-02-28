package dev.mateus124.Estoque.repository;
import dev.mateus124.Estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findByProviderId(Long providerId);
}
