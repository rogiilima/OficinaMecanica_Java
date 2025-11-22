package Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
    private SimpleStringProperty id;
    private SimpleStringProperty nome;
    private SimpleStringProperty cpf;
    private SimpleStringProperty idade;
    private SimpleStringProperty telefone;
    private SimpleBooleanProperty isVip;



    public SimpleStringProperty getId() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public SimpleStringProperty getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public SimpleStringProperty getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade.set(idade);
    }

    public SimpleStringProperty getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }
}