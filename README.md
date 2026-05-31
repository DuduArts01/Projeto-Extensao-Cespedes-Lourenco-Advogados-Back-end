# ⚖️ API Calculadora Penal Jurídica - Backend

> **Projeto de Extensão Universitária** em parceria com **Cespedes Lourenço Advogados**

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Ktor](https://img.shields.io/badge/Ktor-2.3+-087CFA?logo=ktor&logoColor=white)](https://ktor.io/)
[![JDK](https://img.shields.io/badge/JDK-21-orange?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

## 📋 Índice

- [Sobre a Empresa Parceira](#-sobre-a-empresa-parceira)
- [Introdução ao Projeto](#-introdução-ao-projeto)
- [Motivação e Impacto Social](#-motivação-e-impacto-social)
- [Documentação da API](#-documentação-da-api)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Endpoint Principal](#endpoint-principal)
  - [Parâmetros de Entrada](#parâmetros-de-entrada)
  - [Resposta da API](#resposta-da-api)
  - [Exemplos de Requisição](#exemplos-de-requisição)
- [Instalação e Configuração](#-instalação-e-configuração)
- [Como Executar o Projeto](#-como-executar-o-projeto)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Testes](#-testes)
- [Equipe](#-equipe)
- [Referências Legais](#-referências-legais)
- [Licença](#-licença)

---

## 🏢 Sobre a Empresa Parceira

### **Cespedes Lourenço Advogados**

A **Cespedes Lourenço Advogados** é um escritório especializado em Direito Penal com mais de 15 anos de experiência, oferecendo soluções jurídicas personalizadas e comprometidas com a defesa dos direitos de seus clientes. 

O escritório atua em diversas áreas do Direito Penal, incluindo:
- Execução Penal
- Defesa Criminal
- Recursos em Tribunais Superiores
- Habeas Corpus
- Revisão Criminal

**Missão:** Proporcionar assessoria jurídica de excelência, buscando sempre a melhor solução para cada caso, com ética, transparência e compromisso com a justiça.

**Contato:**
- 📧 Email: contato@cespedeslourenco.com.br
- 📱 WhatsApp: [+55 11 98949-8044](https://api.whatsapp.com/send/?phone=5511989498044&text=Ol%C3%A1%2C+sou+advogado%2C+como+posso+ajudar%3F&type=phone_number&app_absent=0)
- 🌐 Website: [cespedeslourencoadvogados.com.br](https://cespedeslourencoadvogados.com.br)

---

## 📖 Introdução ao Projeto

A **API Calculadora Penal Jurídica** é uma solução tecnológica desenvolvida para **automatizar o cálculo de progressão de regime penal** no sistema de execução penal brasileiro, determinando com precisão matemática as datas de transição entre os regimes **fechado**, **semiaberto** e **aberto**, além do **livramento condicional** e **término da pena**.

Este projeto foi idealizado pela **Cespedes Lourenço Advogados** como parte de um **Projeto de Extensão Universitária** do **Instituto Mauá de Tecnologia**, com o objetivo de:

1. **Facilitar o acesso à informação jurídica** para familiares de apenados, cidadãos leigos e estudantes de Direito
2. **Reduzir a complexidade** dos cálculos estabelecidos pela Lei de Execução Penal (LEP)
3. **Automatizar processos** do escritório de advocacia, aumentando a eficiência e precisão dos cálculos

### **Utilidade da API**

A API serve como **motor de cálculos** para aplicações frontend (web e mobile), recebendo os dados do apenado e retornando um cronograma completo de progressão de regime baseado na legislação brasileira vigente, especialmente após as alterações do **Pacote Anticrime (Lei nº 13.964/2019)**.

---

## 🎯 Motivação e Impacto Social

### **O Problema**

O sistema de execução penal brasileiro é regido por uma **legislação complexa**, recentemente alterada pela Lei 13.964/19 (Pacote Anticrime). Para o cidadão leigo, especialmente **familiares de apenados**, é extremamente difícil compreender e calcular os prazos para obtenção de benefícios como a progressão de regime.

Essa **desinformação** resulta em:
- 😰 Angústia e ansiedade
- ❌ Dificuldade no planejamento da ressocialização
- 📉 Lacuna significativa no acesso à informação jurídica

### **A Solução**

Esta API preenche essa lacuna, oferecendo:
- ✅ Cálculos automatizados e precisos
- ✅ Conformidade com a legislação vigente
- ✅ Acesso democrático à informação jurídica
- ✅ Ferramenta de apoio para advogados e defensores públicos

### **Impacto Social**

Este projeto caracteriza-se como **extensão universitária** pois:
- 🌍 Promove acesso à informação para a comunidade
- 🤝 Aproxima o ambiente acadêmico à sociedade
- 💡 Desenvolve solução tecnológica com impacto social
- 👨‍🎓 Envolve alunos em problemas reais

---

## 📡 Documentação da API

### Tecnologias Utilizadas

Esta API foi desenvolvida utilizando as seguintes tecnologias:

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Kotlin** | 1.9.22 | Linguagem de programação moderna e concisa |
| **Ktor** | 2.3.7 | Framework assíncrono para aplicações server-side |
| **JDK** | 21 | Java Development Kit |
| **Kotlinx Serialization** | - | Biblioteca de serialização JSON nativa do Kotlin |
| **Logback** | - | Framework de logging |

### Endpoint Principal

A API expõe um único endpoint responsável por todo o processamento de cálculo:

```
POST /calculate
```

**URL Base (Produção):**
```
https://calculadora-penal-api.onrender.com
```

**URL Base (Desenvolvimento Local):**
```
http://localhost:8080
```

---

### Parâmetros de Entrada

A API aceita os seguintes parâmetros no corpo da requisição (formato JSON):

#### **Request Body (JSON)**

```json
{
  "penaltyYears": Integer,
  "penaltyMonths": Integer,
  "penaltyDays": Integer,
  "baseDate": String (ISO 8601),
  "detractionDays": Integer,
  "crimeType": String (Enum),
  "inmateStatus": String (Enum),
  "daysWorked": Integer,
  "studyHours": Integer,
  "booksRead": Integer
}
```

#### **Descrição dos Parâmetros**

| Campo | Tipo | Obrigatório | Descrição | Valores Aceitos |
|-------|------|-------------|-----------|-----------------|
| `penaltyYears` | Integer | ✅ Sim | Anos da pena aplicada | >= 0 |
| `penaltyMonths` | Integer | ✅ Sim | Meses da pena aplicada | 0-11 |
| `penaltyDays` | Integer | ✅ Sim | Dias da pena aplicada | 0-30 |
| `baseDate` | String | ✅ Sim | Data de início do cumprimento da pena | Formato: `YYYY-MM-DD` |
| `detractionDays` | Integer | ❌ Não | Dias já cumpridos (detração) | >= 0 (padrão: 0) |
| `crimeType` | String | ✅ Sim | Tipo de crime cometido | `COMMON`, `HEINOUS`, `EQUIVALENT` |
| `inmateStatus` | String | ✅ Sim | Status do apenado | `PRIMARY`, `RECIDIVIST` |
| `daysWorked` | Integer | ❌ Não | Dias trabalhados para remição | >= 0 (padrão: 0) |
| `studyHours` | Integer | ❌ Não | Horas de estudo para remição | >= 0 (padrão: 0) |
| `booksRead` | Integer | ❌ Não | Livros lidos para remição (máx. 12/ano) | 0-12 (padrão: 0) |

#### **Detalhamento dos Enums**

##### **crimeType** - Tipo de Crime

| Valor | Descrição | Impacto |
|-------|-----------|---------|
| `COMMON` | Crimes comuns (não hediondos) | Progressão mais branda (16%-20%) |
| `HEINOUS` | Crimes hediondos | Progressão mais rígida (40%-60%) |
| `EQUIVALENT` | Crimes equiparados a hediondos | Progressão mais rígida (40%-60%) |

**Exemplos de crimes hediondos:**
- Homicídio qualificado
- Latrocínio
- Estupro
- Tráfico de drogas (equiparado)

##### **inmateStatus** - Status do Apenado

| Valor | Descrição | Definição Legal |
|-------|-----------|-----------------|
| `PRIMARY` | Réu primário | Nunca foi condenado definitivamente antes |
| `RECIDIVIST` | Reincidente | Cometeu novo crime após condenação definitiva anterior |

---

### Resposta da API

#### **Response (200 OK)**

```json
{
  "metrics": {
    "totalPenaltyDays": Integer,
    "totalRemittedDays": Integer,
    "remainingPenaltyDays": Integer
  },
  "schedule": {
    "closedRegimeStartDate": String (ISO 8601),
    "semiOpenEligibilityDate": String (ISO 8601),
    "openEligibilityDate": String (ISO 8601),
    "conditionalReleaseDate": String (ISO 8601),
    "penaltyEndDate": String (ISO 8601)
  },
  "notes": String
}
```

#### **Estrutura da Resposta**

##### **metrics** - Métricas de Cálculo

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `totalPenaltyDays` | Integer | Total de dias da pena original |
| `totalRemittedDays` | Integer | Total de dias remidos (trabalho, estudo, leitura) |
| `remainingPenaltyDays` | Integer | Dias efetivos a cumprir após remições |

##### **schedule** - Cronograma de Progressão

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `closedRegimeStartDate` | String | Data de início do regime fechado |
| `semiOpenEligibilityDate` | String | Data elegível para regime semiaberto |
| `openEligibilityDate` | String | Data elegível para regime aberto |
| `conditionalReleaseDate` | String | Data elegível para livramento condicional |
| `penaltyEndDate` | String | Data de término total da pena |

##### **notes** - Observações

Mensagem explicativa sobre o cálculo realizado e conformidade com a legislação.

---

### Exemplos de Requisição

#### **Exemplo 1: Crime Comum - Réu Primário**

**Request:**

```bash
curl -X POST https://projeto-extensao-cespedes-lourenco.onrender.com/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "penaltyYears": 4,
    "penaltyMonths": 0,
    "penaltyDays": 0,
    "baseDate": "2026-05-24",
    "detractionDays": 0,
    "crimeType": "COMMON",
    "inmateStatus": "PRIMARY",
    "daysWorked": 90,
    "studyHours": 0,
    "booksRead": 2
  }'
```

**Response:**

```json
{
  "metrics": {
    "totalPenaltyDays": 1460,
    "totalRemittedDays": 38,
    "remainingPenaltyDays": 1422
  },
  "schedule": {
    "closedRegimeStartDate": "2026-05-24",
    "semiOpenEligibilityDate": "2026-12-06",
    "openEligibilityDate": "2027-07-28",
    "conditionalReleaseDate": "2027-08-16",
    "penaltyEndDate": "2030-04-15"
  },
  "notes": "Calculation successfully performed in compliance with the Anti-Crime Package (Law No. 13.964/2019)."
}
```

**Análise do Cálculo:**
- **Pena total:** 4 anos = 1.460 dias
- **Remição por trabalho:** 90 dias ÷ 3 = 30 dias remidos
- **Remição por leitura:** 2 livros × 4 dias = 8 dias remidos
- **Total remido:** 38 dias
- **Pena efetiva:** 1.422 dias
- **Progressão para semiaberto:** 16% da pena (crime comum + primário)

---

#### **Exemplo 2: Crime Hediondo - Reincidente**

**Request:**

```bash
curl -X POST https://projeto-extensao-cespedes-lourenco.onrender.com/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "penaltyYears": 8,
    "penaltyMonths": 6,
    "penaltyDays": 15,
    "baseDate": "2024-01-10",
    "detractionDays": 180,
    "crimeType": "HEINOUS",
    "inmateStatus": "RECIDIVIST",
    "daysWorked": 365,
    "studyHours": 144,
    "booksRead": 12
  }'
```

**Response:**

```json
{
  "metrics": {
    "totalPenaltyDays": 3107,
    "totalRemittedDays": 181,
    "remainingPenaltyDays": 2926
  },
  "schedule": {
    "closedRegimeStartDate": "2024-01-10",
    "semiOpenEligibilityDate": "2028-11-02",
    "openEligibilityDate": "2031-08-15",
    "conditionalReleaseDate": "2032-01-28",
    "penaltyEndDate": "2032-01-28"
  },
  "notes": "Calculation successfully performed in compliance with the Anti-Crime Package (Law No. 13.964/2019)."
}
```

**Análise do Cálculo:**
- **Pena total:** 8 anos, 6 meses, 15 dias = 3.107 dias
- **Detração:** 180 dias descontados
- **Remição por trabalho:** 365 dias ÷ 3 = 121 dias remidos
- **Remição por estudo:** 144 horas ÷ 12 = 12 dias remidos
- **Remição por leitura:** 12 livros × 4 dias = 48 dias remidos
- **Total remido:** 181 dias
- **Pena efetiva:** 2.926 dias
- **Progressão para semiaberto:** 60% da pena (crime hediondo + reincidente)

---

#### **Exemplo 3: JavaScript (Fetch API)**

```javascript
const calculatePenalty = async () => {
  try {
    const response = await fetch('https://projeto-extensao-cespedes-lourenco.onrender.com/calculate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        penaltyYears: 4,
        penaltyMonths: 0,
        penaltyDays: 0,
        baseDate: '2026-05-24',
        detractionDays: 0,
        crimeType: 'COMMON',
        inmateStatus: 'PRIMARY',
        daysWorked: 90,
        studyHours: 0,
        booksRead: 2
      })
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data = await response.json();
    console.log('Cronograma de Progressão:', data);
    
    // Acessar dados específicos
    console.log('Data de progressão para semiaberto:', data.schedule.semiOpenEligibilityDate);
    console.log('Data de término da pena:', data.schedule.penaltyEndDate);
    
  } catch (error) {
    console.error('Erro ao calcular:', error);
  }
};

calculatePenalty();
```

---

#### **Exemplo 4: Python (Requests)**

```python
import requests
import json

url = "https://projeto-extensao-cespedes-lourenco.onrender.com/calculate"

payload = {
    "penaltyYears": 4,
    "penaltyMonths": 0,
    "penaltyDays": 0,
    "baseDate": "2026-05-24",
    "detractionDays": 0,
    "crimeType": "COMMON",
    "inmateStatus": "PRIMARY",
    "daysWorked": 90,
    "studyHours": 0,
    "booksRead": 2
}

headers = {
    "Content-Type": "application/json"
}

try:
    response = requests.post(url, json=payload, headers=headers)
    response.raise_for_status()
    
    data = response.json()
    print("Cronograma de Progressão:")
    print(json.dumps(data, indent=2, ensure_ascii=False))
    
except requests.exceptions.HTTPError as err:
    print(f"Erro HTTP: {err}")
except Exception as err:
    print(f"Erro: {err}")
```

---

#### **Exemplo 5: React (Frontend Integration)**

```jsx
import React, { useState } from 'react';

const PenaltyCalculator = () => {
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const calculatePenalty = async (formData) => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch('https://projeto-extensao-cespedes-lourenco.onrender.com/calculate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(`Erro: ${response.status}`);
      }

      const data = await response.json();
      setResult(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = {
      penaltyYears: parseInt(e.target.years.value),
      penaltyMonths: parseInt(e.target.months.value),
      penaltyDays: parseInt(e.target.days.value),
      baseDate: e.target.baseDate.value,
      detractionDays: parseInt(e.target.detraction.value) || 0,
      crimeType: e.target.crimeType.value,
      inmateStatus: e.target.status.value,
      daysWorked: parseInt(e.target.worked.value) || 0,
      studyHours: parseInt(e.target.study.value) || 0,
      booksRead: parseInt(e.target.books.value) || 0,
    };
    calculatePenalty(formData);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        {/* Campos do formulário */}
        <button type="submit" disabled={loading}>
          {loading ? 'Calculando...' : 'Calcular'}
        </button>
      </form>

      {error && <p style={{color: 'red'}}>Erro: {error}</p>}

      {result && (
        <div>
          <h3>Resultados:</h3>
          <p>Progressão para Semiaberto: {result.schedule.semiOpenEligibilityDate}</p>
          <p>Progressão para Aberto: {result.schedule.openEligibilityDate}</p>
          <p>Livramento Condicional: {result.schedule.conditionalReleaseDate}</p>
          <p>Término da Pena: {result.schedule.penaltyEndDate}</p>
        </div>
      )}
    </div>
  );
};

export default PenaltyCalculator;
```

---

### Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| `200 OK` | Cálculo realizado com sucesso |
| `400 Bad Request` | Parâmetros inválidos ou ausentes |
| `500 Internal Server Error` | Erro interno no servidor |

---

## 🚀 Instalação e Configuração

### Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **JDK 21** ou superior ([Download](https://openjdk.org/))
- **Gradle 8.5** ou superior (incluído via Gradle Wrapper)
- **Git** para clonar o repositório

### Passo 1: Clonar o Repositório

```bash
git clone https://github.com/DuduArts01/Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end.git
cd Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end
```

### Passo 2: Verificar Permissões do Gradle Wrapper

No Linux/Mac:
```bash
chmod +x gradlew
```

No Windows (PowerShell):
```powershell
git update-index --chmod=+x gradlew
```

### Passo 3: Configurar Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto (opcional):

```env
PORT=8080
ENVIRONMENT=development
KTOR_ENV=development
```

---

## ▶️ Como Executar o Projeto

### Executar em Modo de Desenvolvimento

```bash
# Linux/Mac
./gradlew run

# Windows
.\gradlew.bat run
```

A API estará disponível em: **http://localhost:8080**

### Executar os Testes

```bash
# Linux/Mac
./gradlew test

# Windows
.\gradlew.bat test
```

### Compilar o Projeto

```bash
# Linux/Mac
./gradlew build

# Windows
.\gradlew.bat build
```

O arquivo JAR será gerado em: `build/libs/app.jar`

### Executar o JAR Compilado

```bash
java -jar build/libs/app.jar
```

---

## 📁 Estrutura do Projeto

```
Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── cespede/
│   │   │           ├── Application.kt              # Entry point da aplicação
│   │   │           ├── controller/
│   │   │           │   └── CalculoController.kt    # Controller do endpoint
│   │   │           ├── model/
│   │   │           │   ├── CalculationRequest.kt   # Modelo de entrada
│   │   │           │   └── CalculationResponse.kt  # Modelo de resposta
│   │   │           └── service/
│   │   │               └── PenalCalculatorService.kt # Lógica de cálculo
│   │   └── resources/
│   │       ├── application.conf                    # Configuração do Ktor
│   │       └── logback.xml                         # Configuração de logs
│   └── test/
│       └── kotlin/
│           └── com/
│               └── cespede/
│                   └── PenalCalculatorServiceTest.kt # Testes unitários
├── gradle/
│   └── wrapper/
├── build.gradle.kts                                # Configuração do Gradle
├── settings.gradle.kts
├── gradlew                                         # Gradle Wrapper (Linux/Mac)
├── gradlew.bat                                     # Gradle Wrapper (Windows)
├── Dockerfile                                      # Container Docker
├── .dockerignore
├── render.yaml                                     # Configuração Render
├── README.md                                       # Este arquivo
└── LICENSE
```

---

## 🧪 Testes

O projeto conta com uma suíte completa de testes automatizados cobrindo:

### Testes Implementados

- ✅ Cálculo de remição por trabalho (3 dias = 1 dia remido)
- ✅ Cálculo de remição por estudo (12 horas = 1 dia remido)
- ✅ Cálculo de remição por leitura (1 livro = 4 dias remidos, máx. 12 livros/ano)
- ✅ Progressão de regime para crimes comuns (16% primário, 20% reincidente)
- ✅ Progressão de regime para crimes hediondos (40% primário, 60% reincidente)
- ✅ Livramento condicional (1/3 primário, 1/2 reincidente, 2/3 hediondo)
- ✅ Validação de datas e cálculos de detração
- ✅ Tratamento de casos extremos e limites

### Executar Testes com Relatório

```bash
# Executar testes
./gradlew test

# Gerar relatório de cobertura
./gradlew jacocoTestReport
```

O relatório HTML estará disponível em: `build/reports/jacoco/test/html/index.html`

---

## 👥 Equipe

### Desenvolvimento

- **Eduardo** - Engenheiro de Software (Backend Developer)
- **[Nome do Frontend Developer]** - Frontend Developer

### Parceria Jurídica

- **Cespedes Lourenço Advogados** - Idealização e Consultoria Jurídica
- **Contato Institucional:** contato@cespedeslourenco.com.br

### Orientação Acadêmica

- **Prof. Aparecido V. de Freitas** - Doutor em Engenharia da Computação (EPUSP)
- **Instituto Mauá de Tecnologia** - Projeto de Extensão Universitária

---

## 📚 Referências Legais

### Legislação Aplicável

1. **[Lei nº 7.210/1984 - Lei de Execução Penal (LEP)](http://www.planalto.gov.br/ccivil_03/leis/l7210.htm)**
   - Regras gerais de execução penal
   - Definição de regimes penitenciários
   - Critérios de progressão de regime

2. **[Lei nº 13.964/2019 - Pacote Anticrime](http://www.planalto.gov.br/ccivil_03/_ato2019-2022/2019/lei/L13964.htm)**
   - Alterações nas frações de progressão
   - Novos requisitos para benefícios
   - Mudanças no livramento condicional

3. **[Código Penal Brasileiro](http://www.planalto.gov.br/ccivil_03/decreto-lei/del2848.htm)**
   - Definição de crimes hediondos
   - Conceitos de reincidência
   - Detração da pena

### Documentação Técnica

- [Ktor Framework Documentation](https://ktor.io/docs/) - Framework utilizado no projeto
- [Kotlin Language Documentation](https://kotlinlang.org/docs/) - Linguagem de programação

### Material de Apoio

- Documentação técnica e diretrizes operacionais - Cespedes Lourenço Advogados

---

## 🔒 Conformidade e Precisão

Esta API foi desenvolvida com base nas **regras legais fornecidas pela Cespedes Lourenço Advogados**, garantindo:

- ✅ **Conformidade com a Lei nº 13.964/2019** (Pacote Anticrime)
- ✅ **Precisão nos cálculos** de progressão de regime
- ✅ **Atualização legislativa** conforme mudanças na LEP
- ✅ **Validação jurídica** dos algoritmos de cálculo

⚠️ **Aviso Legal:** Esta ferramenta fornece **estimativas** baseadas nos dados informados. Para decisões jurídicas definitivas, **sempre consulte um advogado especializado**.

---

## 🗺️ Roadmap - Futuras Melhorias

### Versão 2.0

- [ ] **Autenticação JWT** - Sistema seguro de autenticação de usuários e advogados
- [ ] **Banco de Dados** - Persistência com PostgreSQL/MySQL
- [ ] **Histórico de Cálculos** - Armazenamento de cálculos realizados
- [ ] **Exportação PDF** - Geração automática de cronogramas em formato PDF
- [ ] **API de Notificações** - Alertas sobre datas de progressão

### Versão 3.0

- [ ] **Integração PJe/BNMP** - Sincronização com sistemas judiciais nacionais
- [ ] **Cálculo de Remição Avançado** - Inclusão de cursos profissionalizantes
- [ ] **Dashboard Administrativo** - Painel de controle para advogados
- [ ] **API GraphQL** - Alternativa ao REST
- [ ] **Webhooks** - Notificações em tempo real

### Infraestrutura

- [ ] **CI/CD Pipeline** - Integração e deploy automatizados
- [ ] **Docker Compose** - Orquestração de containers
- [ ] **Monitoring** - Integração com Prometheus/Grafana
- [ ] **Rate Limiting** - Controle de taxa de requisições
- [ ] **Documentação OpenAPI** - Especificação Swagger 3.0

---

## 🤝 Como Contribuir

Contribuições são bem-vindas! Para contribuir:

1. Faça um **fork** do projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. **Commit** suas mudanças (`git commit -m 'feat: adiciona nova funcionalidade'`)
4. Faça **push** para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um **Pull Request**

### Padrão de Commits

Seguimos o padrão [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` Nova funcionalidade
- `fix:` Correção de bug
- `docs:` Documentação
- `test:` Testes
- `refactor:` Refatoração de código
- `style:` Formatação de código

---

## 📞 Suporte e Contato

### Questões Técnicas

- **GitHub Issues:** [Abrir Issue](https://github.com/DuduArts01/Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end/issues)
- **Email do Desenvolvedor:** [seu-email@exemplo.com]

### Questões Jurídicas

- **Email:** contato@cespedeslourenco.com.br
- **WhatsApp:** [+55 11 98949-8044](https://api.whatsapp.com/send/?phone=5511989498044)
- **Website:** [cespedeslourencoadvogados.com.br](https://cespedeslourencoadvogados.com.br)

---

## 📄 Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE) - veja o arquivo LICENSE para mais detalhes.

---

## 🙏 Agradecimentos

Agradecimentos especiais a:

- **Cespedes Lourenço Advogados** - Pela idealização e parceria no projeto
- **Instituto Mauá de Tecnologia** - Pelo suporte acadêmico e infraestrutura
- **Prof. Aparecido V. de Freitas** - Pela orientação técnica e acadêmica
- **Comunidade Kotlin/Ktor** - Pelos recursos e documentação

---

<p align="center">
  <strong>Desenvolvido com ⚖️ por estudantes do Instituto Mauá de Tecnologia</strong><br>
  <strong>Em parceria com Cespedes Lourenço Advogados</strong>
</p>

<p align="center">
  <sub>Projeto de Extensão Universitária • 2026</sub>
</p>
