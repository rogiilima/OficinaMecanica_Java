package Model;

import javafx.beans.property.SimpleStringProperty;

public class Peca {
    private SimpleStringProperty idPeca;
    private SimpleStringProperty nomePeca;
    private SimpleStringProperty precoUnitario;
    private SimpleStringProperty quantidadeEstoque;

    public Peca(String idPeca, String nomePeca, String precoUnitario, String quantidadeEstoque) {
        this.idPeca = new SimpleStringProperty(idPeca);
        this.nomePeca = new SimpleStringProperty(nomePeca);
        this.precoUnitario = new SimpleStringProperty(precoUnitario);
        this.quantidadeEstoque = new SimpleStringProperty(quantidadeEstoque);
    }

    public SimpleStringProperty idPecaProperty() {
        return idPeca;
    }

    public SimpleStringProperty nomePecaProperty() {
        return nomePeca;
    }

    public SimpleStringProperty precoUnitarioProperty() {
        return precoUnitario;
    }

    public SimpleStringProperty quantidadeEstoqueProperty() {
        return quantidadeEstoque;
    }

    public String getIdPeca() {
        return idPeca.get();
    }

    public void setIdPeca(String idPeca) {
        this.idPeca.set(idPeca);
    }

    public String getNomePeca() {
        return nomePeca.get();
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca.set(nomePeca);
    }

    public String getPrecoUnitario() {
        return precoUnitario.get();
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario.set(precoUnitario);
    }

    public String getQuantidadeEstoque() {
        return quantidadeEstoque.get();
    }

    public void setQuantidadeEstoque(String quantidadeEstoque) {
        this.quantidadeEstoque.set(quantidadeEstoque);
    }

    @Override
    public String toString() {
        return nomePeca.get() + " - R$ " + precoUnitario.get();
    }
}