package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Cliente {
    private SimpleStringProperty id;
    private SimpleStringProperty nome;
    private SimpleStringProperty cpf;
    private SimpleStringProperty telefone;
    private SimpleBooleanProperty isVip;

    public Cliente(String id, String nome, String cpf, String telefone, boolean isVip) {
        this.id = new SimpleStringProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.telefone = new SimpleStringProperty(telefone);
        this.isVip = new SimpleBooleanProperty(isVip);
    }

    public Cliente(String nome, String cpf, String telefone, boolean isVip) {
        this.id = new SimpleStringProperty("");
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.telefone = new SimpleStringProperty(telefone);
        this.isVip = new SimpleBooleanProperty(isVip);
    }

    public Cliente() {
        this.id = new SimpleStringProperty("");
        this.nome = new SimpleStringProperty("");
        this.cpf = new SimpleStringProperty("");
        this.telefone = new SimpleStringProperty("");
        this.isVip = new SimpleBooleanProperty(false);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public SimpleStringProperty cpfProperty() {
        return cpf;
    }

    public SimpleStringProperty telefoneProperty() {
        return telefone;
    }

    public SimpleBooleanProperty isVipProperty() {
        return isVip;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public boolean isVip() {
        return isVip.get();
    }

    public void setVip(boolean vip) {
        isVip.set(vip);
    }

}