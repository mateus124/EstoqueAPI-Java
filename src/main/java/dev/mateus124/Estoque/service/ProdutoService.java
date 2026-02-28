package dev.mateus124.Estoque.service;
import dev.mateus124.Estoque.dto.ProdutoRequest;
import dev.mateus124.Estoque.model.Fornecedor;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.repository.FornecedorRepository;
import dev.mateus124.Estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getById(long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(ProdutoRequest request) {
        Fornecedor fornecedor = fornecedorRepository.findById(request.getProviderId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));

        Produto product = new Produto();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setClasse(request.getClasse());
        product.setProvider(fornecedor);

        return produtoRepository.save(product);
    }

    public Produto update(long id, ProdutoRequest newProduct) {
        Fornecedor fornecedor = fornecedorRepository.findById(newProduct.getProviderId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));

        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setName(newProduct.getName());
                    produto.setQuantity(newProduct.getQuantity());
                    produto.setPrice(newProduct.getPrice());
                    produto.setClasse(newProduct.getClasse());
                    produto.setProvider(fornecedor);
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
