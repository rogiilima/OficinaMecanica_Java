package Model;

import javafx.beans.property.SimpleStringProperty;

public class Veiculo {
    private SimpleStringProperty id;
    private SimpleStringProperty placa;
    private SimpleStringProperty modelo;
    private SimpleStringProperty ano;
    private SimpleStringProperty idCliente;
    private SimpleStringProperty nomeCliente;
    private SimpleStringProperty cpfCliente;

    // Construtor completo
    public Veiculo(String id, String placa, String modelo, String ano, String idCliente, String nomeCliente, String cpfCliente) {
        this.id = new SimpleStringProperty(id);
        this.placa = new SimpleStringProperty(placa);
        this.modelo = new SimpleStringProperty(modelo);
        this.ano = new SimpleStringProperty(ano);
        this.idCliente = new SimpleStringProperty(idCliente);
        this.nomeCliente = new SimpleStringProperty(nomeCliente);
        this.cpfCliente = new SimpleStringProperty(cpfCliente);
    }

    // Construtor sem ID (para novos veículos)
    public Veiculo(String placa, String modelo, String ano, String idCliente) {
        this.id = new SimpleStringProperty("");
        this.placa = new SimpleStringProperty(placa);
        this.modelo = new SimpleStringProperty(modelo);
        this.ano = new SimpleStringProperty(ano);
        this.idCliente = new SimpleStringProperty(idCliente);
        this.nomeCliente = new SimpleStringProperty("");
        this.cpfCliente = new SimpleStringProperty("");
    }

    // Properties para TableView
    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty placaProperty() {
        return placa;
    }

    public SimpleStringProperty modeloProperty() {
        return modelo;
    }

    public SimpleStringProperty anoProperty() {
        return ano;
    }

    public SimpleStringProperty idClienteProperty() {
        return idCliente;
    }

    public SimpleStringProperty nomeClienteProperty() {
        return nomeCliente;
    }

    public SimpleStringProperty cpfClienteProperty() {
        return cpfCliente;
    }

    // Getters e Setters
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getPlaca() {
        return placa.get();
    }

    public void setPlaca(String placa) {
        this.placa.set(placa);
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getAno() {
        return ano.get();
    }

    public void setAno(String ano) {
        this.ano.set(ano);
    }

    public String getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(String idCliente) {
        this.idCliente.set(idCliente);
    }

    public String getNomeCliente() {
        return nomeCliente.get();
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente.set(nomeCliente);
    }

    public String getCpfCliente() {
        return cpfCliente.get();
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente.set(cpfCliente);
    }
}
