# 🌵 Projeto Back-End: Raízes do Nordeste

Projeto desenvolvido como requisito académico para o curso de Análise e Desenvolvimento de Sistemas (UNINTER). Trata-se de uma API RESTful para gestão de pedidos de uma lanchonete regional, contemplando arquitetura em camadas, regras de negócio complexas, segurança e documentação interativa.

## 🚀 Tecnologias Utilizadas
* **Linguagem:** Java (JDK 21)
* **Framework:** Spring Boot 4.1.2
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Segurança:** Spring Security + JWT (Auth0) + BCrypt (Hash de Senhas)
* **Documentação:** Springdoc OpenAPI (Swagger)

## ⚙️ Arquitetura e Padrões Aplicados
* **Domain-Driven Design (DDD):** Separação em camadas `api`, `application`, `domain` e `infrastructure`.
* **Tratamento Global de Exceções:** Implementação de `@ControllerAdvice` para devolução de erros padronizados (JSON) em códigos HTTP 400, 404 e 409.
* **Transações ACID:** Uso de `@Transactional` para garantir a consistência do banco de dados no abatimento de estoque durante a criação de pedidos.
* **Segurança (LGPD):** Senhas armazenadas com algoritmo de hash unidirecional (BCrypt), rotas protegidas por perfis de acesso (Roles) e logs de auditoria no console para rastreabilidade de criação de pedidos.

## 🛠️ Como Executar o Projeto Localmente

**1. Clone o repositório:**
```bash
git clone https://github.com/EduardoDenis/raizes-do-nordeste-backend.git
```
**2. Configuração do Banco de Dados:**
Certifique-se de ter o MySQL a rodar localmente (porta 3306).
* **O banco de dados utilizado é o `raizes_nordeste`.**
* **As credenciais devem ser ajustadas no seu ficheiro application.properties baseando-se no modelo `.env.example` (aplicando as boas práticas de segurança, senhas reais não são expostas no repositório. No ambiente local de desenvolvimento foi utilizada a senha `admin`).**
* **Para iniciar a aplicação com a Unidade Matriz e os Produtos base já cadastrados, execute o script de carga inicial `script.sql` (presente na raiz do repositório) no seu SGBD.**

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

Adicionalmente, na raiz do projeto encontra-se o ficheiro postman_collection.json. 
Pode importá-lo diretamente para o Postman (ou Insomnia) para ter acesso imediato a todas as rotas e testar os cenários descritos no Plano de Testes.

---

### 🔐 Acesso para Testes

A aplicação está protegida por **Spring Security**. Para testar as rotas privadas (como `/pedidos`), siga os passos abaixo:

1. Crie um utilizador de teste realizando um envio `POST` na rota pública `/auth/registrar`(pode cadastrar os dados de exemplo abaixo ou usar os seus próprios).
2. Utilize as credenciais de teste na rota `POST /auth/login` para obter o **Token JWT**:
    * **Email:** `eduardo@email.com`
    * **Senha:** `123`
3. Copie o token gerado e insira-o clicando no botão `Authorize` no topo da página do Swagger ou no cabeçalho `Authorization` do Postman.




