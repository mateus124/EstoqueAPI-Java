package dev.mateus124.Estoque.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import dev.mateus124.Estoque.model.Fornecedor;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.repository.FornecedorRepository;
import dev.mateus124.Estoque.repository.ProdutoRepository;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, ProdutoRepository produtoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Fornecedor> getAll() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> GetById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor save(Fornecedor provider) {
        return fornecedorRepository.save(provider);
    }

    public Fornecedor update(Long id, Fornecedor newProvider) {
        return fornecedorRepository.findById(id)
                .map(provider -> {
                    provider.setName(newProvider.getName());
                    provider.setAddress(newProvider.getAddress());
                    provider.setTel(newProvider.getTel());
                    provider.setCnpj(newProvider.getCnpj());
                    return fornecedorRepository.save(provider);
                })
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));
    }

    public List<Produto> getProdutosByFornecedorId(Long fornecedorId) {
        if (!fornecedorRepository.existsById(fornecedorId)) {
            throw new RuntimeException("Fornecedor não encontrado!");
        }

        return produtoRepository.findByProviderId(fornecedorId);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    } 
}
