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
* **Tratamento Global de Exceções:** Implementação de `@ControllerAdvice` para devolução de erros padronizados (JSON) em códigos HTTP 400, 401, 403, 404, 409 e 422.
* **Transações ACID:** Uso de `@Transactional` para garantir a consistência do banco de dados no abatimento de estoque durante a criação de pedidos.
* **Segurança (LGPD):** Senhas armazenadas com algoritmo de hash unidirecional (BCrypt), rotas protegidas por perfis de acesso (Roles) e log de auditoria no console na criação de pedidos (canal e ID do pedido).

## 🛠️ Como Executar o Projeto Localmente

**Requisitos:** JDK 21, MySQL 8+ (porta 3306) e Git. O Maven não precisa estar instalado (usa-se o `mvnw` do projeto).

**1. Clone o repositório:**
```bash
git clone https://github.com/EduardoDenis/raizes-do-nordeste-backend.git
cd raizes-do-nordeste-backend
```

**2. Configure o banco e as variáveis de ambiente**

A aplicação lê `DB_URL`, `DB_USER`, `DB_PASS` e `JWT_SECRET` do ambiente (modelo em `backend/.env.example`).
Se as variáveis não existirem, vale o padrão de desenvolvimento local: banco `raizes_nordeste`, usuário `root` e senha `admin`.
Para usar outros valores, defina as variáveis antes de iniciar a API. Exemplo (PowerShell):
```powershell
$env:DB_USER="root"; $env:DB_PASS="sua_senha"; $env:JWT_SECRET="uma-chave-longa-e-secreta"
```
> Senhas e chave JWT reais não devem ser versionadas: o `.env.example` traz apenas valores de exemplo.

**3. Crie o banco e a carga inicial (opcional, recomendado)**

Execute `backend/script.sql` no seu cliente MySQL (Workbench, DBeaver ou terminal):
```bash
mysql -u root -p < backend/script.sql
```
O script cria o banco `raizes_nordeste`, todas as tabelas (espelho das entidades JPA) e carrega a **Unidade Matriz (id 1)**,
3 produtos (ids 1 a 3) e o estoque inicial. Ele pode ser executado antes ou depois da primeira execução da API e pode ser repetido sem duplicar dados.
Se preferir, apenas inicie a API: o Hibernate cria as tabelas (`ddl-auto=update`), e então basta rodar o script para a carga inicial.

**4. Inicie a API**

Pela IDE (IntelliJ): execute a classe `BackendApplication.java`. Ou pelo terminal:
```powershell
cd backend
.\mvnw.cmd spring-boot:run        # Linux/macOS: ./mvnw spring-boot:run
```
A API sobe em `http://localhost:8080`.

---

### 📖 Documentação da API (Swagger)

Com a aplicação em execução: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
(especificação OpenAPI em `http://localhost:8080/v3/api-docs`; uma cópia está em `backend/openapi.json`).

---

### 🔐 Autenticação e perfis

As rotas, exceto `/auth/**` e o Swagger, exigem token JWT (`Authorization: Bearer <token>`).

1. Cadastre um usuário: `POST /auth/registrar`
```json
{ "nome": "Gerente Teste", "email": "gerente@email.com", "senha": "Senha@123", "perfil": "GERENTE" }
```
2. Faça login: `POST /auth/login` com `{ "email": "...", "senha": "..." }` e copie o `accessToken`.
3. No Swagger, clique em **Authorize** e cole o token; no Postman, use o cabeçalho `Authorization`.

Autorização por perfil aplicada: `POST /produtos` exige `GERENTE` ou `ADMINISTRADOR` (outros perfis recebem **403**).
Sem token (ou com token inválido), a API responde **401**. Todos os erros seguem o mesmo JSON:
```json
{ "error": "ESTOQUE_INSUFICIENTE", "message": "...", "details": [], "timestamp": "2026-11-22T12:00:00Z", "path": "/pedidos" }
```

---

### 🧪 Plano de testes (Postman)

Importe `backend/postman_collection.json` no Postman (ou Insomnia). A coleção está organizada em pastas e contém 16 cenários
numerados (T01 a T16: 7 positivos e 9 negativos) com testes automáticos (`pm.test`) que verificam status e corpo da resposta.

**Como executar:**
1. Suba a API e aplique o `script.sql` (a coleção usa a unidade 1 e o produto 2 do seed).
2. No Postman, abra a coleção e clique em **Run** (Collection Runner) executando na ordem das pastas. Os usuários são criados
   com e-mails únicos a cada execução e os tokens JWT são guardados automaticamente em variáveis da coleção.
3. Opcional, via terminal: `npx newman run backend/postman_collection.json`

| Pasta | Cenários |
|---|---|
| 1 - Auth | T01 login válido (200 + accessToken) + setup de usuários |
| 2 - Produtos | T02 criar produto como GERENTE (200), T03 listar produtos (200) |
| 3 - Pedidos | T04 criar pedido válido no canal TOTEM (201), T05 consultar pedido (200) |
| 4 - Pagamentos (mock) | T06 aprovado → `PAGO` (200), T07 recusado → `CANCELADO` (200) |
| 5 - Erros e segurança | T08 sem token (401), T09 CLIENTE em rota de GERENTE (403), T10 senha errada (401), T11 sem canalPedido (422), T12 canal inválido (422), T13 quantidade negativa (422), T14 produto inexistente (404), T15 estoque insuficiente (409), T16 pagamento repetido (409) |

**Auditoria:** a criação de pedido gera no console da API a linha `Pedido criado no canal <CANAL> com o ID: <id>` (evidência para o teste T04).
