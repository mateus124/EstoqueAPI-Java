package dev.mateus124.Estoque.controller;
import dev.mateus124.Estoque.dto.EntradaEstoqueRequest;
import dev.mateus124.Estoque.model.EntradaEstoque;
import dev.mateus124.Estoque.service.EntradaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrada-estoque")
public class EntradaEstoqueController {

    @Autowired
    private EntradaEstoqueService entradaEstoqueService;

    @PostMapping
    public ResponseEntity<EntradaEstoque> criar(@RequestBody EntradaEstoqueRequest request) {
        try {
            EntradaEstoque entrada = entradaEstoqueService.criarEntrada(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(entrada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EntradaEstoque>> obterTodas() {
        List<EntradaEstoque> entradas = entradaEstoqueService.obterTodas();
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaEstoque> obterPorId(@PathVariable Long id) {
        Optional<EntradaEstoque> entrada = entradaEstoqueService.obterPorId(id);
        return entrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<EntradaEstoque>> obterPorProduto(@PathVariable Long produtoId) {
        List<EntradaEstoque> entradas = entradaEstoqueService.obterPorProduto(produtoId);
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<EntradaEstoque>> obterPorFornecedor(@PathVariable Long fornecedorId) {
        List<EntradaEstoque> entradas = entradaEstoqueService.obterPorFornecedor(fornecedorId);
        return ResponseEntity.ok(entradas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            entradaEstoqueService.deletarEntrada(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
