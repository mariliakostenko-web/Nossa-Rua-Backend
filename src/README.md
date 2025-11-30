# Nossa Rua - Backend (resumo)

Backend do projeto Nossa Rua — API REST para cadastro e acompanhamento de problemas urbanos reportados por cidadãos.

O que o projeto faz
- Recebe relatos/ocorrências sobre problemas na rua (iluminação, buracos, lixo, etc.).
- Permite criação, edição, listagem e visualização de relatos com fotos/attachments.
- Gerencia usuários e autenticação (login / registro / tokens).
- Mantém status e categorias de cada relato (ex.: aberto, em análise, resolvido).
- Fornece endpoints para dashboards/estatísticas administrativas.
- Versiona a API (ex.: /api/v1/...) e aplica tratamento de erros e logs.

Principais endpoints (exemplos)
- POST /api/v1/auth/login — autenticar usuário
- POST /api/v1/auth/register — criar conta
- GET /api/v1/reports — listar relatos
- POST /api/v1/reports — criar relato (aceita anexos)
- GET /api/v1/reports/{id} — ver relato
- PUT /api/v1/reports/{id} — atualizar relato / status
- POST /api/v1/reports/{id}/attachments — subir imagens
- GET /api/v1/users, GET /api/v1/users/{id} — gestão de usuários
  (Consulte os controllers em src/main/java para os caminhos definitivos.)

Como rodar — rápido
- Clonar:
```bash
git clone https://github.com/mariliakostenko-web/Nossa-Rua-Backend.git
cd Nossa-Rua-Backend
```
- Com Maven (se houver pom.xml):
```bash
mvn spring-boot:run
# ou
mvn clean package
java -jar target/*.jar
```
- Com Docker Compose (exemplo mínimo):
```bash
docker compose up --build
```

Configurações importantes
- Banco de dados: PostgreSQL ou MySQL (defina SPRING_DATASOURCE_* se usar Spring Boot).
- Variáveis típicas: SERVER_PORT, SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD, JWT_SECRET.

Testes
- Maven: mvn test

Notas rápidas
- Verifique se o projeto usa Maven ou Gradle e a versão do Java em pom.xml/build.gradle.
- Se quiser documentação interativa, procure por Swagger/OpenAPI no projeto (ex.: /swagger-ui).

Contribuição
- Abra issues para bugs/funcionalidades, crie branches com nomes descritivos e PRs com instruções de teste.
