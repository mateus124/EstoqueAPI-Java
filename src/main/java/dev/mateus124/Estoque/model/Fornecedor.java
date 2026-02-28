package dev.mateus124.Estoque.model;
import jakarta.persistence.*;

@Entity
@Table(name="providers")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String tel;
    private String cnpj;

    public Fornecedor(String name, String address, String tel, String cnpj) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.cnpj = cnpj;
    }

    public Fornecedor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
