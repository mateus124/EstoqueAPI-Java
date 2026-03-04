package dev.mateus124.Estoque.service;
import dev.mateus124.Estoque.dto.EntradaEstoqueRequest;
import dev.mateus124.Estoque.model.EntradaEstoque;
import dev.mateus124.Estoque.model.Fornecedor;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.repository.EntradaEstoqueRepository;
import dev.mateus124.Estoque.repository.FornecedorRepository;
import dev.mateus124.Estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaEstoqueService {

    @Autowired
    private EntradaEstoqueRepository entradaEstoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional
    public EntradaEstoque criarEntrada(EntradaEstoqueRequest request) {
        Optional<Produto> produtoOpt = produtoRepository.findById(request.getProdutoId());
        if (!produtoOpt.isPresent()) {
            throw new IllegalArgumentException("Produto com ID não encontrado");
        }

        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(request.getFornecedorId());
        if (!fornecedorOpt.isPresent()) {
            throw new IllegalArgumentException("Fornecedor com não encontrado");
        }

        Produto produto = produtoOpt.get();
        Fornecedor fornecedor = fornecedorOpt.get();

        if (request.getQuantidade() == null || request.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        EntradaEstoque entrada = new EntradaEstoque(
                request.getDataEntrada(),
                request.getQuantidade(),
                produto,
                fornecedor,
                request.getDescricao()
        );

        produto.setQuantity(produto.getQuantity() + request.getQuantidade());
        produtoRepository.save(produto);

        return entradaEstoqueRepository.save(entrada);
    }

    public List<EntradaEstoque> obterTodas() {
        return entradaEstoqueRepository.findAll();
    }

    public Optional<EntradaEstoque> obterPorId(Long id) {
        return entradaEstoqueRepository.findById(id);
    }

    public List<EntradaEstoque> obterPorProduto(Long produtoId) {
        return entradaEstoqueRepository.findByProdutoId(produtoId);
    }

    public List<EntradaEstoque> obterPorFornecedor(Long fornecedorId) {
        return entradaEstoqueRepository.findByFornecedorId(fornecedorId);
    }

    @Transactional
    public void deletarEntrada(Long id) {
        Optional<EntradaEstoque> entradaOpt = entradaEstoqueRepository.findById(id);

        if (!entradaOpt.isPresent()) {
            throw new IllegalArgumentException("Entrada de estoque não encontrada!");
        }

        EntradaEstoque entrada = entradaOpt.get();
        Produto produto = entrada.getProduto();

        Integer novaQuantidade = produto.getQuantity() - entrada.getQuantidade();
        
        if (novaQuantidade < 0) {
            throw new IllegalStateException("Não é possível remover esta entrada. A quantidade do produto ficaria negativa.");
        }

        produto.setQuantity(novaQuantidade);
        produtoRepository.save(produto);

        entradaEstoqueRepository.deleteById(id);
    }
}
