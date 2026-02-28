package dev.mateus124.Estoque.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.mateus124.Estoque.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
