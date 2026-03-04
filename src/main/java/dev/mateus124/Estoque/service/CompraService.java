package dev.mateus124.Estoque.service;
import org.springframework.stereotype.Service;
import dev.mateus124.Estoque.dto.CompraRequest;
import dev.mateus124.Estoque.model.Compra;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.repository.CompraRepository;
import dev.mateus124.Estoque.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public CompraService(CompraRepository compraRepository, ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public Optional<Compra> getById(long id) {
        return compraRepository.findById(id);
    }

    public Compra save(CompraRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Integer quantidadeSolicitada = request.getQuantidade();
        Integer estoqueAtual = produto.getQuantity();

        if (quantidadeSolicitada == null || quantidadeSolicitada <= 0) {
            throw new RuntimeException("Quantidade inválida!");
        }

        if (estoqueAtual == null || estoqueAtual <= 0) {
            throw new RuntimeException("Produto sem estoque!");
        }

        Integer quantidadeComprada = Math.min(quantidadeSolicitada, estoqueAtual);
        Double valorTotal = (double) (quantidadeComprada * produto.getPrice());

        Compra compra = new Compra();
        compra.setNomeCliente(request.getNomeCliente());
        compra.setProduto(produto);
        compra.setQuantidade(quantidadeComprada);
        compra.setValorTotal(valorTotal);

        Integer novaQuantidade = estoqueAtual - quantidadeComprada;
        produtoService.updateQuanttity(produto.getId(), novaQuantidade);

        return compraRepository.save(compra);
    }

    public Compra update(long id, CompraRequest novaCompra) {
        Compra compraExistente = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada!"));

        Produto novoProduto = produtoRepository.findById(novaCompra.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Integer quantidadeSolicitada = novaCompra.getQuantidade();
        if (quantidadeSolicitada == null || quantidadeSolicitada <= 0) {
            throw new RuntimeException("Quantidade inválida!");
        }

        if (novoProduto.getPrice() == null) {
            throw new RuntimeException("Produto sem preço definido!");
        }

        Produto produtoAntigo = compraExistente.getProduto();
        Integer quantidadeAntiga = compraExistente.getQuantidade();

        produtoService.updateQuanttity(produtoAntigo.getId(), 
            produtoAntigo.getQuantity() + quantidadeAntiga);

        Produto produtoAtualizado = produtoRepository.findById(novoProduto.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        Integer estoqueDisponivel = produtoAtualizado.getQuantity();

        if (estoqueDisponivel == null || estoqueDisponivel <= 0) {
            throw new RuntimeException("Produto sem estoque!");
        }

        Integer quantidadeComprada = Math.min(quantidadeSolicitada, estoqueDisponivel);
        Double valorTotal = (double) (quantidadeComprada * novoProduto.getPrice());

        produtoService.updateQuanttity(novoProduto.getId(), 
            estoqueDisponivel - quantidadeComprada);

        compraExistente.setNomeCliente(novaCompra.getNomeCliente());
        compraExistente.setProduto(novoProduto);
        compraExistente.setQuantidade(quantidadeComprada);
        compraExistente.setValorTotal(valorTotal);

        return compraRepository.save(compraExistente);
    }

    public void delete(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada!"));

        Produto produto = compra.getProduto();
        Integer quantidadeComprada = compra.getQuantidade();

        produtoService.updateQuanttity(produto.getId(), 
            produto.getQuantity() + quantidadeComprada);

        compraRepository.deleteById(id);
    }
}
