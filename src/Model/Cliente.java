package Model;

import javafx.beans.property.SimpleStringProperty;

public class Cliente {
    private SimpleStringProperty nome;
    private SimpleStringProperty cpf;
    private SimpleStringProperty idade;
    private SimpleStringProperty telefone;

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