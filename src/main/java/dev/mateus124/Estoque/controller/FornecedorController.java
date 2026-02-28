package dev.mateus124.Estoque.controller;

import dev.mateus124.Estoque.model.Fornecedor;
import dev.mateus124.Estoque.model.Produto;
import dev.mateus124.Estoque.service.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
	private final FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@GetMapping
	public List<Fornecedor> getAll() {
		return fornecedorService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> getById(@PathVariable Long id) {
		return fornecedorService.GetById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/produtos")
	public ResponseEntity<List<Produto>> getProdutosByFornecedor(@PathVariable Long id) {
		try {
			List<Produto> produtos = fornecedorService.getProdutosByFornecedorId(id);
			return ResponseEntity.ok(produtos);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public Fornecedor create(@RequestBody Fornecedor fornecedor) {
		return fornecedorService.save(fornecedor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
		try {
			Fornecedor updated = fornecedorService.update(id, fornecedor);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		fornecedorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
