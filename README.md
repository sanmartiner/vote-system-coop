# Voting System - Coop Company

Voting system for assembly elections by Coop Company

### Requirements

* Java 17
* Maven
* RabbitMQ


## Build the package (From the path of the "pom.xml" root aggregator)
```
mvn clean package -U -pl voting-system -am
```

## Install the application
```
mvn clean install -U -pl voting-system  -am
```

This is an API for managing voting sessions in a cooperative context. It allows the creation of agendas, opening voting sessions, receiving votes from members and recording the results.

## All Features

1. **Register a new topics:**

   - **Endpoint:** `api/v1/votingSystem/topics`

   - Mapping: `save`

   - Parameters:

     - `title` (String) required: Composite id.
     - `description` (String) required: Describes the topic.
     - `votingDate` (Date) required: Specifies a date for the topic voting.

   - **Response:** Returns the newly created Topic with a unique identifier.

     

2. **Get a unique Topic by ID :**

   - **Endpoint:** `api/v1/votingSystem/topics`
   - Mapping: `/getOne/{id}`
   - Parameters:
     - `id` (Long) required: specifies a topic.
   - **Response:** If found, return the Topic .

3. **List all Topics with pagination :**

   - **Endpoint:** `api/v1/votingSystem/topics`

   - Mapping: `/getAll`

   - Parameters:

     - `size` (int) required: Page size per request.
     - `page` (int) required: Page number to show.
     - `direction` (String) required: Direction of sort.
     - `sortField` (int) required: Field sort..

   - **Response:** Returns a specific page of all topics query.

     

4. **Abrir uma sessão de votação em uma pauta:**

   - **Endpoint:** `POST api/v1/votingSystem/session`
   - Mapping: `POST/open/{topicId}`
   - Parâmetros:
     - `end` (Opcional, Int): Time duration of a session.
     - `topicId` (required, Long): The topic correponding the voting session.
   - **Response** No content.

5. **Receber votos dos associados em pautas:**

   - **Endpoint:** `POST api/v1/votingSystem/session`
   - Parâmetros:
     - `pautaId` (Long): Identificador único da pauta.
     - `associateId` (Long): Identificador único do associado.
     - `voto` (Boolean): Voto do associado (true para "Sim" e false para "Não").
   - **Resposta:** Sem conteúdo.

6. **Contabilizar os votos e dar o resultado da votação na pauta:**

   - **Endpoint:** A contabilização é feita automaticamente ao final da sessão de votação.

   - **Resposta:** Retorna o resultado da votação para a pauta especificada.

     

- ## Bonus Tasks

  ### Integration with External Systems

  - Associate Chek ID Service:
     - **Endpoint:** `        "https://run.mocky.io/v3/57f23672-c15f-48f8-90d3-d84ce00250b8/users/" + cpf;` 
     - Parameters:
       - `associadoId` (Long): Unique identifier of the associate.
     - **Response:** Returns whether the member can vote (ABLE_TO_VOTE) or cannot vote (UNABLE_TO_VOTE).

  ### Messaging and Queues

  - Voting results are automatically published in a queue after the voting session closes.

  ### Performance

  - The application was optimized to handle large volumes of votes, undergoing performance tests.

  ### API versioning

  - The API follows a semantic versioning strategy. The current version is v1.
  - To access the current version, use `/api/v1`.

  ## Execution Instructions

  1. Clone the repository.
  2. Configure the database in the `application.properties` or `application.yml` file.
  3. Run the application using Maven or the IDE of your choice.
  4. Access the API documentation using Swagger or go to `http://localhost:8080/swagger-ui.html`.

  

  ## External Dependencies

  - The application uses an external service to verify the eligibility of members. Make sure the service is available.

  ## Tests

  - Run unit and integration tests using Maven or the IDE.

  ## Contributions

  Contributions are welcome! Feel free to report issues, suggest improvements or submit pull requests.

  ## Authors

  - David San Martin