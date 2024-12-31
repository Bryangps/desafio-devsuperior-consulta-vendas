# Desafio do curso devsuperior CRUD de clientes

# Sobre o projeto

Trata-se de um sistema de vendas (Sale) e vendedores (Seller). Cada venda está para um vendedor, e um
vendedor pode ter várias vendas. 

Onde são implementado as seguintes consultas : 
- Relatorio de Vendas
- Sumário de vendas por vendedor

Essas consultas tem as seguintes regras: 
  1. O usuário informa, opcionalmente, data inicial, data final e um trecho do nome do vendedor.
  2. O sistema informa uma listagem paginada contendo id, data, quantia vendida e nome do
  vendedor, das vendas que se enquadrem nos dados informados.
  Informações complementares:
  - Se a data final não for informada, considerar a data atual do sistema.
  - Se a data inicial não for informada, considerar a data de 1 ano antes da data final. 
  - Se o nome não for informado, considerar o texto vazio.

## Modelo conceitual
![image](https://github.com/user-attachments/assets/594220c8-70e8-4e2c-9ebb-1d5fe8d5cb5f)

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- H2
# Como executar o projeto

## Back end
Pré-requisitos: Java 21

```bash
# clonar repositório
git clone git@github.com:Bryangps/desafio-devsuperior-consulta-vendas.git

# entrar na pasta do projeto back end
cd D:\Java_Geral\...

# abra o projeto no Intellij

# executar o projeto
./mvnw spring-boot:run
ou
Run da IDE
```
