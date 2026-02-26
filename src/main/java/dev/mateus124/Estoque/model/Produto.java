package dev.mateus124.Estoque.model;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    private String classe;
    private Integer price;

    public Produto(Long id, String name, Integer quantity, String classe, Integer price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.classe = classe;
        this.price = price;
    }

    public Produto() {

    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer value) {
        this.price = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
