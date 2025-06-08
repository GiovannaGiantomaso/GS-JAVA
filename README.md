# Plataforma de Cadastro e Solicitação de Ajuda Comunitária - SOS GR

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
- **Internacionalização (i18n)**: Suporte a múltiplos idiomas utilizando o Spring Boot e Thymeleaf para traduzir as mensagens da aplicação de acordo com o idioma do usuário.
  

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

## **Antes de iniciar a aplicação, certifique-se de que o RabbitMQ esteja instalado e em execução localmente.**

#### Para garantir o funcionamento da mensageria com RabbitMQ:

Passos para instalação local (Windows):
1. Instale o **Erlang OTP**: https://www.erlang.org/downloads  
2. Instale o **RabbitMQ Server**: https://www.rabbitmq.com/install-windows.html  
3. Após a instalação, abra o `services.msc` e **inicie o serviço RabbitMQ**.

Verifique se o RabbitMQ está rodando:
- Acesse o painel de gerenciamento (se habilitado): http://localhost:15672  
  - Usuário: `guest` | Senha: `guest`
- Confirme se a porta `5672` está ativa

## 5. Execute o projeto:
 
    mvn spring-boot:run


## ROTAS DA APLICAÇÃO:
- Rota inicial para login: http://localhost:8080/public/login
- Rota principal do projeto: http://localhost:8080/principal
- Rota para Acessar a IA solidária: http://localhost:8080/ai/form
- Rota para acessar a página de tipos de ajuda: http://localhost:8080/tipos-ajuda
- Rota para acessar a página da lista de pedidos de ajuda: http://localhost:8080/pedidos
- Rota para acessar a página para o cadastro de pedido de ajuda: http://localhost:8080/pedidos/novo
- Rota para acessar a página de atualizar o perfil do usuário: http://localhost:8080/usuarios/atualizar


## Internacionalização (i18n)

O projeto implementa suporte completo à **internacionalização** utilizando o recurso `messages.properties`, permitindo a tradução da interface e mensagens em diferentes idiomas.

### Idiomas disponíveis:
- 🇧🇷 Português (`messages_pt_BR.properties`)
- 🇺🇸 Inglês (`messages_en.properties`)
- 🇪🇸 Espanhol (`messages_es.properties`)

### Funcionamento:
A tradução é feita através da opção que o usuário escolher na interface.  
As mensagens são exibidas nas views Thymeleaf de forma dinâmica, sem necessidade de alterações no código Java.

### Exemplo de uso no HTML com Thymeleaf:
```html
<h1 th:text="#{msg_bem_vindo}">Bem-vindo</h1>
```

## Mensageria Assíncrona com RabbitMQ

O projeto utiliza o RabbitMQ para realizar comunicação assíncrona entre os serviços da aplicação, enviando e processando mensagens nas operações de **criação**, **atualização** e **exclusão** de dados da entidade `Tipo de Ajuda`.

### Acesso ao painel do RabbitMQ:

- URL: http://localhost:15672  
- Usuário: `guest`  
- Senha: `guest`

Ao acessar o painel, é possível:
- Monitorar o tráfego nas filas
- Ver mensagens pendentes ou consumidas
- Ver como as exchanges estão roteando as mensagens para as filas corretas

  ### Comportamento da mensageria:

1. Uma entidade (Tipo de Ajuda) é criada, atualizada ou excluída via interface web.  
2. O serviço correspondente envia um objeto JSON para a fila vinculada à operação.  
3. O consumidor consome a mensagem da fila, processa e exibe os dados no console em tempo real.

   #### Entidade `Tipo de Ajuda`:
- `tipoajuda.create`: mensagens de tipoajuda recém-cadastrados  
- `tipoajuda.update`: mensagens de tipoajuda atualizados  
- `tipoajuda.delete`: mensagens de tipoajuda excluídos 


Cada ação envia uma mensagem JSON para a fila apropriada. Os consumidores escutam essas filas e realizam o processamento adequado, exibindo os dados no console da aplicação.

### Exemplo de saída no console:

**[TIPO AJUDA CRIADO]**

ID: 13

Nome: Resgate

Descrição: Resgate de pessoas em áreas de risco


**[TIPO AJUDA ATUALIZADO]** 

ID: 13

Nome: Higiene

Descrição: Necessitam de kits de limpeza pessoal

**Essas mensagens são úteis para rastreabilidade, auditoria e integração futura com outros sistemas ou microsserviços.**

## IA Solidária com Spring AI + OpenAI

A aplicação conta com um recurso de **assistente virtual inteligente** desenvolvido com o **Spring AI**, que se conecta à **API GPT-4 da OpenAI**. Essa IA foi treinada para fornecer **orientações rápidas, humanizadas e contextualizadas** para situações de emergência, com foco em **ajuda comunitária**.

### Acesso
A funcionalidade pode ser acessada pela rota:  
**http://localhost:8080/ai/form**

### Funcionamento
- O usuário digita uma dúvida ou situação relacionada à ajuda humanitária.
- O sistema envia essa pergunta para o modelo GPT-4 usando a biblioteca `Spring AI`.
- A IA processa e retorna uma resposta pronta em linguagem natural, exibida no chat da interface.

### Exemplo de uso

**Usuário:**  
> O que fazer em caso de enchente na minha cidade?

**Resposta da IA:**  
> Em caso de enchente na sua cidade, é importante seguir algumas orientações para garantir a sua segurança e a de sua família. Aqui estão algumas medidas que você pode tomar:
>
> 1. **Acompanhe as informações**: Fique atento a boletins meteorológicos e avisos de autoridades locais sobre a situação das enchentes.
>
> 2. **Evacuação**: Se as autoridades recomendarem evacuação, faça isso imediatamente. Tenha um plano de fuga e saiba para onde ir.
>  
> 3. **Prepare um kit de emergência**: Inclua itens essenciais como água, alimentos não perecíveis, medicamentos, lanternas, pilhas, documentos importantes, dinheiro e roupas.
>
> 4. **Desconecte eletrônicos**: Desligue e desconecte aparelhos eletrônicos e eletrodomésticos para evitar danos ou riscos de choque elétrico.
>
> 5. **Evite áreas de risco**: Não tente atravessar áreas alagadas, ruas inundadas ou pontes submersas. Mesmo uma pequena quantidade de água pode ser perigosa.
>    
> 6. **Procure um local seguro**: Se você não puder evacuar, dirija-se a locais mais altos dentro da sua casa e evite o primeiro andar ou porões.
>    
> 7. **Mantenha a calma**: Em situações de emergência, é fundamental manter a calma e seguir as orientações das autoridades.
>    
> 8. **Ajude os outros se possível**: Se você estiver em uma posição segura e puder ajudar vizinhos ou pessoas em necessidade, faça isso, mas sempre priorizando a sua segurança.
>    
> 9. **Após a enchente**: Quando a situação começar a se estabilizar, evite voltar para casa até que as autoridades declare que é seguro. Verifique os danos com cautela, evitando áreas alagadas e estruturas instáveis.
>    
> 10. **Atenção à saúde**: Após a enchente, tenha cuidado com a água contaminada e com possíveis riscos de doenças. Lave as mãos frequentemente e evite consumir alimentos contaminados. Seguir essas orientações pode ajudar a minimizar os riscos e proteger você e sua família durante uma enchente.

Esse recurso torna a plataforma mais acessível e empática, especialmente para pessoas em situação de vulnerabilidade que precisam de instruções rápidas.

---
