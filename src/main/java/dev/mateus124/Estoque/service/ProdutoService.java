package dev.mateus124.Estoque.service;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto product) {
        return produtoRepository.save(product);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
