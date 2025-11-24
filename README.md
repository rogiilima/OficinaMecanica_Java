# 🚗 Sistema de Gestão para Oficina Mecânica

Sistema desktop completo desenvolvido em **Java** com **JavaFX** e **MySQL** para gerenciamento de oficinas mecânicas. Controle total de clientes, veículos, ordens de serviço, estoque de peças, pagamentos e relatórios financeiros.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Requisitos do Sistema](#requisitos-do-sistema)
- [Instalação](#instalação)
- [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)
- [Como Usar](#como-usar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Capturas de Tela](#capturas-de-tela)
- [Requisitos Atendidos](#requisitos-atendidos)
- [Melhorias Futuras](#melhorias-futuras)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

---

## 🎯 Sobre o Projeto

Sistema desenvolvido para automatizar e facilitar a gestão completa de oficinas mecânicas, incluindo:
- Cadastro de clientes e veículos
- Controle de ordens de serviço
- Gerenciamento de estoque de peças
- Sistema de pagamentos
- Geração de relatórios financeiros
- Histórico completo de manutenções

O sistema foi projetado com foco em **usabilidade**, **segurança** e **eficiência**, atendendo às necessidades reais de pequenas e médias oficinas.

---

## ✨ Funcionalidades

### 👥 **Gestão de Clientes**
- ✅ Cadastro completo (nome, CPF, telefone)
- ✅ Identificação de clientes VIP
- ✅ Edição e exclusão de clientes
- ✅ Busca por nome
- ✅ Validação de CPF

### 🚙 **Gestão de Veículos**
- ✅ Cadastro de veículos (placa, modelo, ano)
- ✅ Vinculação ao proprietário
- ✅ Histórico completo de manutenções
- ✅ Lista de todas as ordens de serviço por veículo
- ✅ Busca por placa ou modelo

### 🔧 **Ordens de Serviço**
- ✅ Criação de ordens detalhadas
- ✅ Adição de peças utilizadas
- ✅ Cálculo automático de valores (peças + mão de obra)
- ✅ Controle de status:
  - Em Serviço
  - Aguardando Peças
  - Pronto para Entrega
  - Finalizado
- ✅ Histórico de serviços por veículo
- ✅ Visualização detalhada de cada ordem

### 📦 **Controle de Estoque**
- ✅ Cadastro de peças
- ✅ Controle de quantidade em estoque
- ✅ Preço unitário
- ✅ Entrada e saída de estoque
- ✅ **Desconto automático** ao criar ordem de serviço
- ✅ **Devolução automática** ao excluir ordem
- ✅ Alertas de estoque baixo (menos de 10 unidades)
- ✅ Valor total investido em estoque

### 💰 **Sistema de Pagamentos**
- ✅ Registro de pagamentos por ordem
- ✅ Múltiplas formas de pagamento:
  - Dinheiro
  - Cartão de Débito
  - Cartão de Crédito
  - PIX
  - Boleto
- ✅ Status de pagamento (Pendente/Pago)
- ✅ Data e hora do pagamento
- ✅ Visualização na tela de detalhes da ordem

### 📊 **Relatórios**
- ✅ Faturamento total (ordens pagas)
- ✅ Total de ordens finalizadas
- ✅ Geração de relatório em arquivo
- ✅ Listagem detalhada de todas as ordens
- ✅ Exportação de dados

---

## 🛠️ Tecnologias Utilizadas

### **Backend**
- **Java 21** - Linguagem de programação
- **JavaFX 21** - Interface gráfica
- **MySQL 8.0** - Banco de dados
- **JDBC** - Conexão com banco de dados
- **PreparedStatement** - Segurança contra SQL Injection

### **Ferramentas de Desenvolvimento**
- **IntelliJ IDEA / Eclipse** - IDE
- **Scene Builder** - Design de interfaces FXML
- **DBeaver** - Gerenciamento de banco de dados
- **Git** - Controle de versão

### **Padrões e Arquitetura**
- **MVC** (Model-View-Controller)
- **DAO** (Data Access Object)
- **JavaFX Properties** para binding de dados
- **ObservableList** para tabelas dinâmicas

---

## 💻 Requisitos do Sistema

### **Software Necessário**
- **Java JDK 21** ou superior
- **MySQL Server 8.0** ou superior
- **JavaFX SDK 21** (se não incluído no JDK)
- **IDE Java** (IntelliJ IDEA, Eclipse ou NetBeans)

### **Hardware Recomendado**
- **Processador:** Intel Core i3 ou superior
- **Memória RAM:** 4GB mínimo (8GB recomendado)
- **Espaço em disco:** 500MB para aplicação + banco de dados
- **Resolução:** 1366x768 ou superior

---

## 📥 Instalação

### **1. Clone o Repositório**
```bash
git clone https://github.com/seu-usuario/sistema-oficina.git
cd sistema-oficina
```

### **2. Configure o Banco de Dados**

#### Crie o banco de dados no MySQL:
```sql
CREATE DATABASE oficina CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE oficina;
```

#### Execute os scripts SQL na seguinte ordem:

**a) Tabela de Administradores:**
```sql
CREATE TABLE administrador (
    id_administrador INT PRIMARY KEY AUTO_INCREMENT,
    email_adm VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

INSERT INTO administrador (email_adm, senha) VALUES ('admin@oficina.com', 'admin123');
```

**b) Tabela de Clientes:**
```sql
CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(11) NOT NULL,
    is_vip BOOLEAN DEFAULT FALSE
);
```

**c) Tabela de Veículos:**
```sql
CREATE TABLE `veiculo` (
    id_veiculo INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(100) NOT NULL,
    ano VARCHAR(4) NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);
```

**d) Tabela de Peças:**
```sql
CREATE TABLE peca (
    id_peca INT PRIMARY KEY AUTO_INCREMENT,
    nome_peca VARCHAR(100) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0
);

-- Inserir peças de exemplo
INSERT INTO peca (nome_peca, preco_unitario, quantidade_estoque) VALUES
('Óleo Motor 5W30', 45.00, 50),
('Filtro de Óleo', 25.00, 30),
('Filtro de Ar', 35.00, 25),
('Filtro de Combustível', 40.00, 20),
('Velas de Ignição', 15.00, 40),
('Pastilha de Freio', 120.00, 15),
('Disco de Freio', 180.00, 10),
('Bateria 60Ah', 350.00, 8);
```

**e) Tabela de Ordens de Serviço:**
```sql
CREATE TABLE ordem_servico (
    id_ordem INT PRIMARY KEY AUTO_INCREMENT,
    id_veiculo INT NOT NULL,
    descricao TEXT NOT NULL,
    valor_mao_obra DECIMAL(10,2) NOT NULL DEFAULT 0,
    status ENUM('Aguardando Peças', 'Em Serviço', 'Pronto para Entrega', 'Finalizado') NOT NULL DEFAULT 'Em Serviço',
    data_abertura DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_finalizacao DATETIME NULL,
    FOREIGN KEY (id_veiculo) REFERENCES `veiculo`(id_veiculo) ON DELETE CASCADE
);
```

**f) Tabela de Peças na Ordem:**
```sql
CREATE TABLE ordem_peca (
    id_ordem_peca INT PRIMARY KEY AUTO_INCREMENT,
    id_ordem INT NOT NULL,
    id_peca INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_ordem) REFERENCES ordem_servico(id_ordem) ON DELETE CASCADE,
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca) ON DELETE CASCADE
);
```

**g) Tabela de Pagamentos:**
```sql
CREATE TABLE pagamento (
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    id_ordem INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    forma_pagamento ENUM('Dinheiro', 'Cartão Débito', 'Cartão Crédito', 'PIX', 'Boleto') NOT NULL,
    status_pagamento ENUM('Pendente', 'Pago', 'Cancelado') NOT NULL DEFAULT 'Pendente',
    data_pagamento DATETIME NULL,
    FOREIGN KEY (id_ordem) REFERENCES ordem_servico(id_ordem) ON DELETE CASCADE
);
```

### **3. Configure a Conexão**

Edite o arquivo `.env`:

```java
DB_URL=jdbc:mysql://localhost:3306/oficina
DB_USER=root
DB_PASSWORD=sua_senha_aqui
```

### **4. Compile e Execute**

**Via IDE:**
1. Abra o projeto na sua IDE
2. Configure o JavaFX SDK (se necessário)
3. Execute a classe `App.java`

**Via Linha de Comando:**
```bash
javac --module-path /caminho/para/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d bin src/**/*.java
java --module-path /caminho/para/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp bin App
```

---

## 🗄️ Estrutura do Banco de Dados

### **Diagrama ER Simplificado**

```
┌─────────────┐
│ administrador│
└─────────────┘

┌──────────┐         ┌──────────┐         ┌────────────────┐
│ cliente  │────────<│ veiculo  │────────<│ ordem_servico  │
└──────────┘         └──────────┘         └────────────────┘
                                                    │
                                                    │
                                           ┌────────┴─────────┐
                                           │                  │
                                           ▼                  ▼
                                    ┌─────────────┐   ┌────────────┐
                                    │ ordem_peca  │   │ pagamento  │
                                    └─────────────┘   └────────────┘
                                           │
                                           ▼
                                    ┌──────────┐
                                    │  peca    │
                                    └──────────┘
```

### **Relacionamentos**
- Um **Cliente** possui vários **Veículos** (1:N)
- Um **Veículo** possui várias **Ordens de Serviço** (1:N)
- Uma **Ordem** possui várias **Peças** (N:N via ordem_peca)
- Uma **Ordem** possui um **Pagamento** (1:1)

---

## 📖 Como Usar

### **1. Login**
- Email padrão: `admin@gmail.com`
- Senha padrão: `1234`

### **2. Fluxo Básico de Trabalho**

#### **📝 Cadastrar Cliente**
1. Menu lateral → **Clientes**
2. Clique em **"Adicionar"**
3. Preencha: Nome, CPF, Telefone
4. Marque **"Cliente VIP"** se aplicável
5. Clique em **"Salvar"**

#### **🚗 Cadastrar Veículo**
1. Menu lateral → **Veículos**
2. Clique em **"Adicionar"**
3. Preencha: Placa, Modelo, Ano
4. Selecione o **Proprietário**
5. Clique em **"Salvar"**

#### **🔧 Criar Ordem de Serviço**
1. Menu lateral → **Ordens de Serviço**
2. Clique em **"+ Nova Ordem"**
3. Selecione **Cliente** e **Veículo**
4. Descreva o serviço
5. Informe o valor da **Mão de Obra**
6. Adicione **Peças** (opcional):
   - Selecione a peça
   - Informe quantidade
   - Clique em "Adicionar Peça"
7. O estoque é **descontado automaticamente**
8. Clique em **"Criar Ordem"**

#### **💰 Registrar Pagamento**
1. Acesse a **Ordem de Serviço**
2. Clique em **"Ver Detalhes"**
3. Clique em **"Registrar Pagamento"**
4. Escolha a **Forma de Pagamento**
5. Confirme

#### **📊 Gerar Relatório**
1. Menu lateral → **Relatórios**
2. Visualize as estatísticas
3. Clique em **"Gerar Relatório em PDF"**
4. Escolha o local para salvar
5. Pronto!

---

## 📁 Estrutura do Projeto

```
sistema-oficina/
│
├── src/
│   ├── Controller/
│   │   ├── LoginController.java
│   │   ├── PainelController.java
│   │   ├── AdicionarClientesController.java
│   │   ├── MudarClientesController.java
│   │   ├── VeiculoController.java
│   │   ├── AdicionarVeiculoController.java
│   │   ├── HistoricoVeiculoController.java
│   │   ├── PecasController.java
│   │   ├── AdicionarPecaController.java
│   │   ├── AjustarEstoqueController.java
│   │   ├── OrdensServicoController.java
│   │   ├── CriarOrdemServicoController.java
│   │   ├── DetalhesOrdemServicoController.java
│   │   └── RelatorioController.java
│   │
│   ├── Model/
│   │   ├── Administrador.java
│   │   ├── Cliente.java
│   │   ├── Veiculo.java
│   │   ├── Peca.java
│   │   ├── ItemPeca.java
│   │   ├── OrdemServico.java
│   │   ├── OrdemDeServico.java
│   │   ├── Pagamento.java
│   │   ├── MudarTela.java
│   │   ├── Validacoes.java
│   │   └── GeradorPDF.java
│   │
│   ├── DB/
│   │   ├── ConexaoComBanco.java
│   │   ├── ClienteDAO.java
│   │   ├── VeiculoDAO.java
│   │   ├── PecaDAO.java
│   │   ├── OrdemServicoDAO.java
│   │   ├── PagamentoDAO.java
│   │   └── RelatorioDAO.java
│   │
│   ├── Templates/
│   │   └── Alertas.java
│   │
│   ├── View/
│   │   ├── TelaDeLoginADM.fxml
│   │   ├── PainelAdministrativo.fxml
│   │   ├── AdicionarClientes.fxml
│   │   ├── EditarCliente.fxml
│   │   ├── PainelVeiculos.fxml
│   │   ├── AdicionarVeiculo.fxml
│   │   ├── HistoricoVeiculo.fxml
│   │   ├── PainelPecas.fxml
│   │   ├── AdicionarPeca.fxml
│   │   ├── AjustarEstoque.fxml
│   │   ├── PainelOrdensServico.fxml
│   │   ├── CriarOrdemServico.fxml
│   │   ├── DetalhesOrdemServico.fxml
│   │   └── PainelRelatorios.fxml
│   │
│   └── App.java
│
├── lib/
│   ├── mysql-connector-java-8.0.x.jar
│   └── javafx-sdk-21/
│
└── README.md
```

## ✅ Requisitos Atendidos

### **Requisitos Funcionais**
| # | Requisito | Status |
|---|-----------|--------|
| 1 | Registro completo de cliente e veículo | ✅ |
| 2 | Histórico de manutenções e peças | ✅ |
| 3 | Criação de ordens de serviço | ✅ |
| 4 | Atualização de status | ✅ |
| 5 | Sistema de agendamento | ⏳ |
| 6 | Benefícios para clientes VIP | ✅ |
| 7 | Controle de estoque de peças | ✅ |
| 8 | Histórico de peças por serviço | ✅ |
| 9 | Controle de pagamentos | ✅ |
| 10 | Emissão de relatórios | ✅ |

**Taxa de Conclusão: 90%** (9 de 10 requisitos implementados)

### **Requisitos Não Funcionais**
| # | Requisito | Status |
|---|-----------|--------|
| 1 | Responsivo (desktop) | ✅ |
| 2 | Segurança (SQL Injection) | ✅ |
| 3 | Usabilidade | ✅ |
| 4 | Performance | ✅ |

**Taxa de Conclusão: 100%** ✨

---

## 🚀 Melhorias Futuras

### **Funcionalidades Planejadas**
- [ ] Sistema de Agendamentos completo
- [ ] Notificações por email/SMS
- [ ] Dashboard com gráficos
- [ ] Controle de funcionários
- [ ] Sistema de backup automático
- [ ] Integração com APIs de pagamento
- [ ] App mobile (Android/iOS)
- [ ] Modo escuro
- [ ] Multi-idioma
- [ ] Impressão de ordens de serviço

### **Melhorias Técnicas**
- [ ] Implementar testes unitários (JUnit)
- [ ] Adicionar logs de auditoria
- [ ] Criptografia de senhas (BCrypt)
- [ ] Otimização de queries
- [ ] Cache de dados
- [ ] PDF real com iText/PDFBox
- [ ] Docker para deploy

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Siga os passos:

1. **Fork** o projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/MinhaFeature`)
3. **Commit** suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. **Push** para a branch (`git push origin feature/MinhaFeature`)
5. Abra um **Pull Request**

### **Diretrizes**
- Mantenha o código limpo e comentado
- Siga os padrões MVC e DAO
- Teste antes de fazer PR
- Documente novas funcionalidades

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

```
MIT License

Copyright (c) 2024 Sistema de Oficina Mecânica

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 👨‍💻 Autores

- João Matheus Ramos Araujo
- Igor Pereira Lima
- Erick Rhuan Carvalho

---

## 🙏 Agradecimentos

- Comunidade JavaFX
- MySQL Documentation
- Stack Overflow
- Todos que contribuíram com feedback

---

## 📞 Suporte

Encontrou um bug? Tem uma sugestão?

- **Email:** joaosobramatheus@gmail.com

---

<div align="center">

**⭐ Se este projeto te ajudou, deixe uma estrela! ⭐**

Made with ❤️ and ☕

</div>
