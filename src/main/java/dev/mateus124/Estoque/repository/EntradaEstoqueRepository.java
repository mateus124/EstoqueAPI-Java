package dev.mateus124.Estoque.repository;

import dev.mateus124.Estoque.model.EntradaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaEstoqueRepository extends JpaRepository<EntradaEstoque, Long> {
    List<EntradaEstoque> findByProdutoId(Long produtoId);
    List<EntradaEstoque> findByFornecedorId(Long fornecedorId);
}
