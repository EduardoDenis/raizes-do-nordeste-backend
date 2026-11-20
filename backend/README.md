# 🌵 Projeto Back-End: Raízes do Nordeste

Projeto desenvolvido como requisito académico para o curso de Análise e Desenvolvimento de Sistemas (UNINTER). Trata-se de uma API RESTful para gestão de pedidos de uma lanchonete regional, contemplando arquitetura em camadas, regras de negócio complexas, segurança e documentação interativa.

## 🚀 Tecnologias Utilizadas
* **Linguagem:** Java (JDK 17+)
* **Framework:** Spring Boot 4.1.2
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Segurança:** Spring Security + JWT (Auth0) + BCrypt (Hash de Senhas)
* **Documentação:** Springdoc OpenAPI (Swagger)

## ⚙️ Arquitetura e Padrões Aplicados
* **Domain-Driven Design (DDD):** Separação em camadas `api`, `application`, `domain` e `infrastructure`.
* **Tratamento Global de Exceções:** Implementação de `@ControllerAdvice` para devolução de erros padronizados (JSON) em códigos HTTP 400, 404 e 409.
* **Transações ACID:** Uso de `@Transactional` para garantir a consistência do banco de dados no abatimento de estoque durante a criação de pedidos.
* **Segurança (LGPD):** Senhas armazenadas com algoritmo de hash unidirecional (BCrypt) e rotas protegidas por perfis de acesso (Roles).

## 🛠️ Como Executar o Projeto Localmente

**1. Clone o repositório:**
```bash
git clone https://github.com/EduardoDenis/raizes-do-nordeste-backend.git
```
**2. Configuração do Banco de Dados:**
Certifique-se de ter o MySQL a rodar localmente (porta 3306). No ficheiro `src/main/resources/application.properties`, configure as suas credenciais se forem diferentes:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/raizes_do_nordeste?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=admin
```

### 3. Executar a Aplicação

A forma mais recomendada de rodar o projeto é utilizando a sua IDE (como o **IntelliJ IDEA**):

1. Localize a classe principal `BackendApplication.java`.
2. Clique em **Run** (ícone da seta verde).

#### Alternativa via Terminal
Se preferir rodar pelo terminal, navegue até a pasta do projeto e execute o Maven Wrapper embutido:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

---

### 📖 Documentação e Testes (Swagger)

A API está 100% documentada com **Swagger OpenAPI**. Com a aplicação em execução, acesse no seu navegador:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

### 🔐 Acesso para Testes

A aplicação está protegida por **Spring Security**. Para testar as rotas privadas (como `/pedidos`), siga os passos abaixo:

1. Utilize as credenciais de teste na rota `POST /auth/login` para obter o **Token JWT**:
    * **Email:** `eduardo@email.com`
    * **Senha:** `123`
2. Copie o token gerado e insira-o clicando no botão **Authorize** no topo da página do Swagger.




