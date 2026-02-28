package dev.mateus124.Estoque.service;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getById(long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto product) {
        return produtoRepository.save(product);
    }

    public Produto update(long id, Produto newProduct) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setName(newProduct.getName());
                    produto.setQuantity(newProduct.getQuantity());
                    produto.setPrice(newProduct.getPrice());
                    produto.setClasse(newProduct.getClasse());
                    produto.setProvider(newProduct.getProvider());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public Produto updateQuanttity(Long id, Integer newQuantity) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setQuantity(newQuantity);
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
