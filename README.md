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

---

### Cadastrar Alimento

`POST` /api/alimento

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
nome | String | sim | Informa o nome do alimento
temperatura_minima | int | sim | Informa a temperatura mínima recomendada
temperatura_maxima | int | sim | Informa a temperatura máxima recomendada
umidade_minima | String | sim | Informa a % de umidade mínima recomendada
umidade_maxima | String | sim | Informa  a % de umidade máxima recomendada

**Exemplo de Campo de Requisição**

```js
    "nome": "Sorvete de morango",
    "temperatura_minima ": -18,
    "temperatura_maxima": -12,
    "umidade_minima": "20%",
    "umidade_maxima": "60%"
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
    "nome": "Sorvete de morango",
    "temperatura_minima ": -18,
    "temperatura_maxima": -12,
    "umidade_minima": "20%",
    "umidade_maxima": "60%"
```

---

### Cadastrar Endereço

`POST` /api/endereco

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
logradouro | String | sim | Informa o nome do logradouro
numero | int | sim | Informa o número do local
cep | int | sim | Informa o número do cep
bairro | String | sim | Informa o bairro
cidade | String | sim | Informa a cidade
estado | String | sim | Informa o estado
sigla_estado | String | sim | Informa a sigla do estado
regiao | String | sim | Informa a região
ponto_referencia | String | não | Informa um ponto de referencia


**Exemplo de Campo de Requisição**

```js
    "logradouro": "Rua Conselheiro Moreira de Barros",
    "numero ": 105,
    "cep": 48400000,
    "bairro": "Santana",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "sigla_estado": "SP",
    "regiao": "Grande São Paulo",
    "ponto_referencia": null
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
    "cep": 48400000,
    "bairro": "Santana",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "sigla_estado": "SP",
    "regiao": "Grande São Paulo",
    "ponto_referencia": null
```

---

### Cadastrar Monitoramento

`POST` /api/monitoramento

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
temperatura | int | sim | Informa a temperatura atual
umidade | String | sim | Informa a % de umidade atual
latitude | String | sim | Informa a latitude atual
longitude | String | sim | Informa o longitude atual
dthr_monitoramento | LocalDateTime | sim | Informa a data e hora


**Exemplo de Campo de Requisição**

```js
    "temperatura": 18,
    "umidade ": "48%",
    "latitude": "23°33'01''",
    "longitude": "46°38'02''",
    "dthr_monitoramento": 2023-04-02-14-35-08
  
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
    "umidade ": "48%",
    "latitude": "23°33'01''",
    "longitude": "46°38'02''",
    "dthr_monitoramento": 2023-04-02-14-35-08
  
```

---

### Cadastrar Viagem

`POST` /api/viagem

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
dthr_partida | LocalDateTime | sim | Informa a data e hora de partida
dthr_chegada | LocalDateTime | sim | Informa a data e hora de chegada


**Exemplo de Campo de Requisição**

```js
    "dthr_partida": 2023-04-02-14-35-08,
    "dthr_chegada": 2023-05-02-18-42-35
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
    "dthr_partida": 2023-04-02-14-35-08,
    "dthr_chegada": 2023-05-02-18-42-35
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