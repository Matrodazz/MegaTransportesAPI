##MegaTransportes
Vitor Moreira dos Santos 93101
João Victor Jales 94673

## Instalação

1. Clone o repositório:

   ```shell
   git clone https://github.com/Matrodazz/MegaTransportesAPI/tree/main
   ```


2. Instale as dependências:

    ```shell
    npm install
    ```

## Vídeo apresentação
Link: https://youtu.be/HfpxS98_4vE

## Enterprise Application Development
### Estado atual da solução
O [FrontEnd](https://github.com/Matrodazz/MegaTransportesApp) já consegue receber e exibir as informações da API na tela via Fetch, o sistema de autenticação da API está funcionando, assim como o registro de dados no banco. As boas práticas para uma API Restful estão sendo mantidas. Não foi possível executar o mapeamento de relacionamentos, já que ao aplicar em apenas duas classes, toda a API parava de funcionar alegando erros na parte de autenticação, que já funcionava perfeitamente.

![Diagrama de Classes](https://i.imgur.com/psPByxB.png)


## Endpoints

* Alimento
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos
    
* Endereço
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos
    
* Monitoramento
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos

* Viagem
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos

* Veículo
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos

* Embalagem
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos

* Motorista
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos

* Empresa
    * Cadastrar
    * Atualizar 
    * Excluir 
    * Listar todos
---

### Cadastrar Alimento

`POST` /api/alimento

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
nome | String | sim | Informa o nome do alimento
temperaturaMinima | int | sim | Informa a temperatura mínima recomendada
temperaturaMaxima | int | sim | Informa a temperatura máxima recomendada
umidadeMinima | int | sim | Informa a % de umidade mínima recomendada
umidadeMaxima | int | sim | Informa  a % de umidade máxima recomendada

**Exemplo de Campo de Requisição**

```js
    "nome": "Morango",
    "temperaturaMinima ": -18,
    "temperaturaMaxima": -12,
    "umidadeMinima": 20,
    "umidadeMaxima": 60
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Alimento cadastrado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Alimento

`GET` /api/alimento/{id}

```js
    "nome": "Morango",
    "temperaturaMinima ": -18,
    "temperaturaMaxima": -12,
    "umidadeMinima": 20,
    "umidadeMaxima": 60
```

---

### Cadastrar Endereço

`POST` /api/endereco

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
logradouro | String | sim | Informa o nome do logradouro
numero | int | sim | Informa o número do local
cep | String | sim | Informa o número do cep
bairro | String | sim | Informa o bairro
cidade | String | sim | Informa a cidade
estado | String | sim | Informa o estado
siglaEstado | String | sim | Informa a sigla do estado
regiao | String | sim | Informa a região
pontoReferencia | String | não | Informa um ponto de referencia


**Exemplo de Campo de Requisição**

```js
    "logradouro": "Rua Conselheiro Moreira de Barros",
    "numero ": 105,
    "cep": "48400-000",
    "bairro": "Santana",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "siglaEstado": "SP",
    "regiao": "Grande São Paulo",
    "pontoReferencia": null
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Endereço cadastrado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Endereço

`GET` /api/endereco/{id}

```js
    "logradouro": "Rua Conselheiro Moreira de Barros",
    "numero ": 105,
    "cep": "48400-000",
    "bairro": "Santana",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "siglaEstado": "SP",
    "regiao": "Sudeste",
    "pontoReferencia": null
```

---

### Cadastrar Monitoramento

`POST` /api/monitoramento

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
temperatura | int | sim | Informa a temperatura atual
umidade | int | sim | Informa a % de umidade atual
latitude | String | sim | Informa a latitude atual
longitude | String | sim | Informa o longitude atual
dthrMonitoramento | LocalDateTime | sim | Informa a data e hora


**Exemplo de Campo de Requisição**

```js
    "temperatura": 18,
    "umidade ": 48,
    "latitude": "23°33'01''",
    "longitude": "46°38'02''",
    "dthrMonitoramento": 2023-04-02-14-35-08
  
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Monitoramento cadastrado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Monitoramento

`GET` /api/monitoramento/{id}

```js
    "temperatura": 18,
    "umidade ": 48,
    "latitude": "23°33'01''",
    "longitude": "46°38'02''",
    "dthrMonitoramento": 2023-04-02-14-35-08
  
```

---

### Cadastrar Viagem

`POST` /api/viagem

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
dthrPartida | LocalDateTime | sim | Informa a data e hora de partida
dthrChegada | LocalDateTime | sim | Informa a data e hora de chegada


**Exemplo de Campo de Requisição**

```js
    "dthrPartida": 2023-04-02-14-35-08,
    "dthrChegada": 2023-05-02-18-42-35
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Viagem cadastrada com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Viagem

`GET` /api/viagem/{id}

```js
    "dthrPartida": 2023-04-02-14-35-08,
    "dthrChegada": 2023-05-02-18-42-35
```

---


### Cadastrar Veículo

`POST` /api/veiculo

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
modelo | String | sim | Informa o modelo do veículo
marca | String | sim | Informa a marca do veículo
placa | String | sim | Informa a placa do veículo
capacidade | String | sim | Informa a capacidade do veículo

**Exemplo de Campo de Requisição**

```js
    "modelo": "Atego",
    "marca": "Mercedes-Benz",
    "placa": "DCG6B67",
    "capacidade": 5000
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Veículo cadastrado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Veículo

`GET` /api/veiculo/{id}

```js
    "modelo": "Atego",
    "marca": "Mercedes-Benz",
    "placa": "DCG6B67",
    "capacidade": 5000
```

---

### Cadastrar Embalagem

`POST` /api/embalagem

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
capacidadeEmbalagem | String | sim | Informa a capacidade da emabalgem
tipo | String | não | Informa o tipo de embalagem
material | String | não | Informa o material da embalagem


**Exemplo de Campo de Requisição**

```js
    "capacidadeEmbalagem": 200.00,
    "tipo": "Tetra Pak",
    "material": "Papelão"
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Embalagem cadastrado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Embalagem

`GET` /api/embalagem/{id}

```js
    "capacidadeEmbalagem": 200.00,
    "tipo": "Tetra Pak",
    "material": "Papelão"
```

---


### Cadastrar Motorista

`POST` /api/motorista

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
nome | String | sim | Informa o nome do motorista
idade | int | sim | Informa a idade do motorista
cpf | String | sim | Informa o cpf do motorista
statusContrato | String | sim | Informa status atual do contrato do motorista


**Exemplo de Campo de Requisição**

```js
    "nome": "Gilberto",
    "idade": 38,
    "cpf": "555.555.555-55",
    "statusContrato": "Ativo"
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Motorista cadastrado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Motorista

`GET` /api/motorista/{id}

```js
    "nome": "Gilberto",
    "idade": 38,
    "cpf": "555.555.555-55",
    "statusContrato": "Ativo"
```

---


### Cadastrar Empresa

`POST` /api/empresa

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
cnpj | String | sim | Informa o cnpj completo da empresa
razaoSocial | String | sim | Informa a razão social da empresa
nome | String | sim | Informa o nome da empresa
statusAtividade | String | sim | Informa o status de atividade da empresa
dataInclusão | LocalDate | sim | Informa a data de entrada da empresa
dataEncerramento | LocalDate | não | Informa a data de saída da empresa


**Exemplo de Campo de Requisição**

```js
    "cnpj": "00.399.214/0001-82",
    "razaoSocial": "Stampda comércios LTDA.",
    "nome": "Stamp Alimentos",
    "statusAtividade": "Ativo",
    "dataInclusão": 2023-04-10,
    "dataEncerramento": null
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Empresa cadastrada com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Empresa

`GET` /api/empresa/{id}

```js
    "cnpj": "00.399.214/0001-82",
    "razaoSocial": "Stampda comércios LTDA.",
    "nome": "Stamp Alimentos",
    "statusAtividade": "Ativo",
    "dataInclusão": 2023-04-10,
    "dataEncerramento": null
```

---
