package dev.mateus124.Estoque.controller;
import dev.mateus124.Estoque.dto.ProdutoRequest;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> getAll() {
        return produtoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody ProdutoRequest produto) {
        try {
            Produto updated = produtoService.update(id, produto);
            return ResponseEntity.ok(updated);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<Produto> updateQuantidade(@PathVariable Long id, @RequestParam Integer quantity) {
        try {
            Produto updated = produtoService.updateQuanttity(id, quantity);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Produto create(@RequestBody ProdutoRequest product) {
        return produtoService.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}