
# 🛒 TechPlus E-commerce - Módulo de Pedidos

> Documento oficial da equipe de desenvolvimento da TechPlus

---

## 📘 Visão Geral

Este repositório contém o módulo responsável pela **gestão de pedidos** dentro da nossa plataforma de e-commerce. Ele trata das regras de negócio relacionadas ao valor total de pedidos, aplicação de descontos promocionais, cupons e regras de frete.

O objetivo principal deste serviço é garantir a **correta aplicação das políticas comerciais da TechPlus** no processo de finalização de compras, promovendo uma boa experiência para o cliente e integridade para os relatórios financeiros.

---

## 🧱 Arquitetura e Componentes

O projeto foi desenvolvido em **Java (versão X)** com foco em **clareza, testabilidade e facilidade de manutenção**. A seguir estão os principais componentes:

### `Pedido.java`
Classe principal que representa um pedido. Contém atributos como valor total, se possui desconto e se há cupom válido.

### `CalculadoraDesconto.java`
Responsável por aplicar a regra de desconto de 10% quando aplicável.

### `Cupom.java`
Classe que representa a existência e validade de um cupom promocional.

### `Cliente.java`
Representa o cliente que realizou o pedido. Pode ter perfil comum ou VIP, o que influencia diretamente em regras como frete e benefícios.

---

## 📋 Regras de Negócio Atuais

As regras abaixo foram aprovadas pelo time de produto e estão implementadas no código deste módulo.

### 📌 Cálculo do Total com Desconto
Se o pedido possuir desconto, o sistema deve aplicar **10% de desconto sobre o valor total**.

```
Ex: valorTotal = 100, possuiDesconto = true
Resultado esperado: 90
```

### 🚚 Frete Grátis
Pedidos com valor **maior ou igual a R$ 200,00** têm direito a frete grátis.

### 🎟️ Cupom Promocional
Se o pedido possuir um cupom válido **e** o valor total for **maior ou igual a R$ 300,00**, aplica-se um desconto fixo de **R$ 20,00** no valor final.

---

## 🚀 Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/techplus/ecommerce-pedidos.git
cd ecommerce-pedidos
```

2. Importe o projeto na sua IDE (Eclipse, IntelliJ ou VSCode com suporte Java).

3. Execute a classe `Main.java` para rodar exemplos manuais.

4. Os testes ainda não estão disponíveis neste módulo.

---

## 🛠️ Próximos Passos (Roadmap)

- Integração com o módulo de Estoque
- Implementação de cálculo de cashback
- Criação de API REST para acesso externo
- Integração com o sistema de pagamento e antifraude

---

## 🤝 Contato com o Time

| Função | Nome | Contato |
|--------|------|---------|
| Product Owner | Carla Martins | carla.martins@techplus.com |
| Tech Lead     | João Henrique | joao.henrique@techplus.com |
| QA            | Ana Paula     | ana.paula@techplus.com |


