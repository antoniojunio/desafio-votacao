# Desafio de Votação

Este é um projeto de API RESTful desenvolvido em Java com Spring Boot para gerenciar votações em uma cooperativa. A API permite cadastrar pautas, abrir sessões de votação, registrar votos e contabilizar o resultado das votações.

## Funcionalidades

- **Cadastro de Pauta**: Permite cadastrar novas pautas para votação.
- **Abertura de Sessão**: Inicia uma sessão de votação para uma pauta específica.
- **Registro de Votos**: Registra votos dos associados em uma pauta aberta para votação.
- **Consulta de Resultados**: Exibe o resultado da votação de uma pauta, contabilizando votos "Sim" e "Não".

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database
- OpenAPI (Swagger) para documentação
- JPA/Hibernate

## Pré-requisitos

- **Java 17** ou superior
- **Maven** para gerenciamento de dependências

## Configuração

### Clonando o Repositório

```bash
git clone git@github.com:antoniojunio/desafio-votacao.git
cd desafio-votacao
```

### Executando a Aplicação

1. Navegue até a pasta do projeto e execute o comando abaixo para compilar e rodar o projeto:

   ```bash
   mvn spring-boot:run
   ```

2. A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API

A documentação da API está disponível através do Swagger. Após iniciar a aplicação, acesse a URL abaixo para visualizar a documentação e testar os endpoints:

```
http://localhost:8080/swagger-ui/index.html
```

## Endpoints Principais

### Cadastrar uma Pauta
- **URL**: `POST /api/pautas`
- **Body**:
  ```json
  {
    "nome": "Assembleia Geral",
    "descricao": "Discussão sobre novos membros"
  }
  ```

### Abrir Sessão de Votação
- **URL**: `POST /api/sessoes`
- **Body**:
  ```json
  {
    "pautaId": 1,
    "duracao": 15
  }
  ```

### Registrar Voto
- **URL**: `POST /api/votos`
- **Body**:
  ```json
  {
    "associadoId": 123,
    "sessaoId": 1,
    "voto": true,
    "cpf": "12345678900"
  }
  ```

### Obter Resultado da Votação
- **URL**: `GET /api/pautas/{pautaId}/resultado`

## Estrutura de Pastas

- `controller` - Contém os controladores da API.
- `service` - Camada de serviço para lógica de negócios.
- `model` - Entidades representando o modelo de dados.
- `repository` - Repositórios JPA para acesso ao banco de dados.
- `client` - Cliente para integração com serviços externos de validação de CPF.

## Observações

Este projeto foi desenvolvido para o desafio de backend da cooperativa. Todos os dados são persistidos em um banco de dados H2 em memória, portanto, serão perdidos após a aplicação ser finalizada.