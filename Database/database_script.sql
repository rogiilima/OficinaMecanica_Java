CREATE DATABASE IF NOT EXISTS oficina;
USE oficina;

-- Tabela de Administradores (Login)
CREATE TABLE IF NOT EXISTS administrador (
    id_adm INT AUTO_INCREMENT PRIMARY KEY,
    email_adm VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

-- Inserir admin padrão para conseguir logar
INSERT INTO administrador (email_adm, senha) VALUES ('admin@oficina.com', 'admin123');

-- Tabela de Clientes
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(11),
    isVip BOOLEAN DEFAULT FALSE
);

-- Tabela de Veículos
CREATE TABLE veiculo (
    id_veiculo INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(100) NOT NULL,
    ano VARCHAR(4) NOT NULL,
    placa VARCHAR(7) NOT NULL UNIQUE,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

-- Tabela de Peças
CREATE TABLE peca (
id_peca INT PRIMARY KEY AUTO_INCREMENT,
nome_peca VARCHAR(100) NOT NULL,
preco_unitario DECIMAL(10,2) NOT NULL,
quantidade_estoque INT NOT NULL DEFAULT 0
);


-- Tabela de Ordem de Serviço
CREATE TABLE ordem_servico (
    id_ordem INT PRIMARY KEY AUTO_INCREMENT,
    id_veiculo INT NOT NULL,
    descricao TEXT NOT NULL,
    valor_mao_obra DECIMAL(10,2) NOT NULL DEFAULT 0,
    status ENUM('Aguardando Peças', 'Em Serviço', 'Pronto para Entrega', 'Finalizado') NOT NULL DEFAULT 'Em Serviço',
    data_abertura DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_finalizacao DATETIME NULL,
    FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo) ON DELETE CASCADE
);

-- Tabela de Relacionamento Ordem <-> Peça (Muitos para Muitos)
CREATE TABLE ordem_peca (
    id_ordem_peca INT PRIMARY KEY AUTO_INCREMENT,
    id_ordem INT NOT NULL,
    id_peca INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_ordem) REFERENCES ordem_servico(id_ordem) ON DELETE CASCADE,
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca) ON DELETE CASCADE
);

-- Tabela de Pagamentos
CREATE TABLE pagamento (
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    id_ordem INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    forma_pagamento ENUM('Dinheiro', 'Cartão Débito', 'Cartão Crédito', 'PIX', 'Boleto') NOT NULL,
    status_pagamento ENUM('Pendente', 'Pago', 'Cancelado') NOT NULL DEFAULT 'Pendente',
    data_pagamento DATETIME NULL,
    FOREIGN KEY (id_ordem) REFERENCES ordem_servico(id_ordem) ON DELETE CASCADE
);