package dev.mateus124.Estoque.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.mateus124.Estoque.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    
}
