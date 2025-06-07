# Plataforma de Cadastro e Solicitação de Ajuda Comunitária

Este projeto visa criar uma plataforma digital para gerenciar solicitações de ajuda em situações de emergência, como desastres naturais. 
A plataforma permite aos usuários cadastrarem seus perfis e criarem pedidos de ajuda e tipos de ajuda, facilitando a comunicação entre a população e as autoridades responsáveis.


# INTEGRANTES:

Giovanna Lima Giantomaso|RM553369

Rebeca Silva Lopes|RM553764

## LINK DO VÍDEO DEMOSNTRANDO O FUNCIONAMENTO DO PROJETO:

...

## LINK DO PITCH:

...

## Tecnologias Utilizadas


- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção da aplicação backend.
- **Spring Security**: Para autenticação e controle de acesso.
- **OAuth2**: Para autenticação via provedores externos (ex: Google).
- **Spring Data JPA**: Para interação com o banco de dados.
- **Thymeleaf**: Motor de templates para renderização de páginas HTML.
- **Apache Maven**: Ferramenta de automação de build.
- **Banco de Dados**: Utilização de um banco de dados Oracle.
- **RabbitMQ**: Para comunicação assíncrona entre os serviços.
- **OpenAI GPT-4**: Para integração de IA na plataforma.
- 

## Requisitos

1. **Java 17 ou superior**
2. **Apache Maven**
3. **Banco de dados Oracle** (ou equivalente)
4. **RabbitMQ** (para fila de mensagens)
5. **API do OpenAI GPT-4** (para funcionalidades de IA)


## Passos para Configuração

## 1. Clone o repositório:

    git clone https://github.com/GiovannaGiantomaso/GS-JAVA.git


## 2. Navegue até o diretório do projeto:

    cd ajuda


## 3. Compile o projeto usando Maven:

    mvn clean install


## 4. Configure o banco de dados e a string de conexão no arquivo `application.properties`.

### Configuração do nome da aplicação
spring.application.name=ajuda

### Configuração do banco Oracle
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
spring.datasource.username=rm553369
spring.datasource.password=120505
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

### JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

### Thymeleaf
spring.thymeleaf.cache=false

### Configuração de mensagens
spring.messages.encoding=UTF-8

### OAuth2 (preencher os campos com as credenciais de OAuth2)
spring.security.oauth2.client.registration.google.client-id=seu-client-id-aqui
spring.security.oauth2.client.registration.google.client-secret=seu-client-secret-aqui
spring.security.oauth2.client.registration.google.scope=openid,email,profile
spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo

### Configuração do RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

### Configuração do OpenAI (preencher o token de API)
spring.ai.openai.api-key=seu-token-aqui
spring.ai.openai.chat.model=gpt-4
spring.ai.openai.base-url=https://models.inference.ai.azure.com
spring.ai.openai.chat.completions-path=/chat/completions

### Desabilitar funções do Spring Cloud Function
spring.cloud.function.enabled=false

5. Execute o projeto:
    ```
    mvn spring-boot:run
    ```

## ROTAS DA APLICAÇÃO:
- Rota inicial para login: http://localhost:8080/public/login
- Rota principal do projeto: http://localhost:8080/principal
- Rota para Acessar a IA solidária: http://localhost:8080/ai/form
- Rota para acessar a página de tipos de ajuda: http://localhost:8080/tipos-ajuda
- Rota para acessar a página da lista de pedidos de ajuda: http://localhost:8080/pedidos
- Rota para acessar a página para o cadastro de pedido de ajuda: http://localhost:8080/pedidos/novo
- Rota para acessar a página de atualizar o perfil do usuário: http://localhost:8080/usuarios/atualizar

