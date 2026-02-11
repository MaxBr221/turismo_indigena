# 🌍 Projeto Turismo API

API REST desenvolvida com Spring Boot para gerenciamento de:

- Usuários  
- Autenticação JWT  
- Pontos turísticos  
- Restaurantes  
- Guias  
- Agendamentos  

Projeto focado em:

- Arquitetura em camadas  
- Boas práticas REST  
- Uso de DTOs  
- Tratamento global de exceções  
- Segurança com Spring Security + JWT  
- Testes unitários com Mockito  
- Documentação com Swagger  

---

# 🚀 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.3.x  
- Spring Security  
- Spring Data JPA  
- Hibernate  
- MySQL  
- Flyway  
- Swagger / Springdoc OpenAPI  
- JUnit 5  
- Mockito  
- AssertJ  
- Maven  

---

# 📂 Estrutura do Projeto

```
src/main/java/com/example/projeto_turismo

├── controller   → Endpoints REST  
├── service      → Regras de negócio  
├── repository   → Acesso a dados  
├── dto          → Objetos de transferência  
├── mapper       → Conversão Entity ↔ DTO  
├── entity       → Entidades JPA  
├── infra        → Tratamento de exceções  
├── security     → Configuração JWT e filtros  
```

Arquitetura tradicional em camadas, com separação clara de responsabilidades.

---

# 🔐 Autenticação

A API utiliza JWT (JSON Web Token).

Fluxo:

- Usuário realiza login  
- API gera o token JWT  
- Token deve ser enviado no header:

```
Authorization: Bearer SEU_TOKEN_AQUI
```

---

# 📘 Documentação da API (Swagger)

Após iniciar a aplicação, acessar:

```
http://localhost:8080/swagger-ui/index.html
```

O Swagger permite:

- Testar endpoints  
- Gerar token  
- Autorizar via botão "Authorize"  
- Visualizar modelos DTO  

---

# 🗄️ Banco de Dados

Banco utilizado:

- MySQL  

Exemplo de configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/projeto_turismo
spring.datasource.username=root
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```

Boas práticas:

- Utilizar Flyway para migrations  
- Evitar `ddl-auto=update` em ambiente produtivo  

---

# 🧱 Principais Entidades

- User  
- PontoTuristico  
- Restaurante  
- Guia  
- Agendamento  

Relacionamentos mapeados com JPA.

---

# 🧠 Regras de Negócio Implementadas

- Login único (case insensitive)  
- Controle de permissões por ROLE  
- Tratamento global de exceções com `@ControllerAdvice`  
- Validações de criação e autenticação  
- Controle de acesso baseado em perfil (USER / ADMIN)  

---

# 🧪 Testes

Testes unitários utilizando:

- Mockito para mock de dependências  
- AssertJ para validações  
- JUnit 5  

Cenários testados:

- Exceção quando login já existe  
- Conversão Entity → DTO  
- Comportamento do Service isolado do banco  

Executar testes:

```
mvn test
```

---

# 🛡️ Segurança

Configuração via `SecurityFilterChain`.

Endpoints públicos:

- `/auth/login`  
- `/auth/register`  
- Swagger  

Endpoints protegidos por ROLE:

- USER  
- ADMIN  

Autenticação stateless com JWT.

---

# ▶️ Como Executar o Projeto

Passos:

- Clonar o repositório  
- Configurar banco MySQL  
- Ajustar `application.properties`  
- Executar:

```
mvn clean install
mvn spring-boot:run
```

---

# 📌 Endpoints Principais

## Autenticação

- `POST /auth/login`  
- `POST /auth/register`  

## Ponto Turístico

- `POST /pontoturistico`  
- `DELETE /pontoturistico`  

## Guia

- `POST /guide`  
- `DELETE /guide`  

## Restaurante

- `POST /restaurantes`  
- `DELETE /restaurantes`  

## Agendamento

- `POST /agendamento`  
- `DELETE /agendamento`  

---

# 📈 Melhorias Futuras

- Paginação  
- Filtros dinâmicos  
- Upload de imagens  
- Cache com Redis  
- Testes de integração  
- Dockerização  
- CI/CD  

---

# 📚 Conceitos Aplicados

- Separação de responsabilidades  
- Injeção de dependência  
- DTO Pattern  
- Mapper Pattern  
- Tratamento centralizado de erros  
- Testes unitários isolados  
- Segurança stateless  

---

# 👨‍💻 Autor

Projeto desenvolvido como prática de backend com Spring Boot, focando em arquitetura limpa, segurança e boas práticas.
