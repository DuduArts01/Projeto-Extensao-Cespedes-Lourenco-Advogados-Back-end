# ⚖️ Calculadora Penal Jurídica - Backend API

> **Cespedes Lourenço Advogados** • Projeto de Extensão Universitária

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Ktor](https://img.shields.io/badge/Ktor-2.3+-087CFA?logo=ktor&logoColor=white)](https://ktor.io/)
[![JDK](https://img.shields.io/badge/JDK-17+-orange?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

API backend desenvolvida para automatizar o cálculo de progressão de regime penal, determinando com precisão matemática as datas de transição entre os regimes fechado, semiaberto e aberto, além do livramento condicional e término da pena, em conformidade com a legislação brasileira vigente.

---

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Endpoints da API](#-endpoints-da-api)
- [Instalação e Execução](#-instalação-e-execução)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Exemplos de Uso](#-exemplos-de-uso)
- [Testes](#-testes)
- [Equipe](#-equipe-e-autoria)
- [Roadmap](#-futuras-melhorias-roadmap)
- [Referências](#-referências)
- [Licença](#-licença)

---

## ✨ Funcionalidades

- ✅ Cálculo automatizado de progressão de regime penal
- ✅ Suporte a diferentes tipos de crime (comum, hediondo, equiparado)
- ✅ Distinção entre réu primário e reincidente
- ✅ Remição de pena por trabalho (3 dias trabalhados = 1 dia remido)
- ✅ Remição por estudo (12 horas de estudo = 1 dia remido)
- ✅ Remição por leitura (1 livro lido = 4 dias remidos, máx. 12 livros/ano)
- ✅ Cálculo de detração (dias já cumpridos)
- ✅ Determinação de datas de transição entre regimes
- ✅ Projeção de livramento condicional
- ✅ Conformidade com o Pacote Anticrime (Lei nº 13.964/2019)

---

## 🔗 Endpoints da API

### **POST /calculate**

Endpoint principal responsável por receber os dados do apenado e retornar a projeção detalhada do cronograma de progressão de regime.

#### Requisição (Body JSON)

```json
{
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
```

#### Parâmetros

| Campo | Tipo | Descrição | Obrigatório |
|-------|------|-----------|-------------|
| `penaltyYears` | Integer | Anos da pena aplicada | Sim |
| `penaltyMonths` | Integer | Meses da pena aplicada | Sim |
| `penaltyDays` | Integer | Dias da pena aplicada | Sim |
| `baseDate` | String (ISO 8601) | Data de início do cumprimento da pena | Sim |
| `detractionDays` | Integer | Dias já cumpridos (detração) | Não (padrão: 0) |
| `crimeType` | Enum | Tipo de crime: `COMMON`, `HEINOUS`, `EQUIVALENT` | Sim |
| `inmateStatus` | Enum | Status do apenado: `PRIMARY`, `RECIDIVIST` | Sim |
| `daysWorked` | Integer | Dias trabalhados para remição | Não (padrão: 0) |
| `studyHours` | Integer | Horas de estudo para remição | Não (padrão: 0) |
| `booksRead` | Integer | Livros lidos para remição (máx. 12/ano) | Não (padrão: 0) |

#### Resposta (200 OK)

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

#### Estrutura da Resposta

| Campo | Descrição |
|-------|-----------|
| `metrics.totalPenaltyDays` | Total de dias da pena original |
| `metrics.totalRemittedDays` | Total de dias remidos (trabalho, estudo, leitura) |
| `metrics.remainingPenaltyDays` | Dias efetivos a cumprir após remições |
| `schedule.closedRegimeStartDate` | Data de início do regime fechado |
| `schedule.semiOpenEligibilityDate` | Data elegível para regime semiaberto |
| `schedule.openEligibilityDate` | Data elegível para regime aberto |
| `schedule.conditionalReleaseDate` | Data elegível para livramento condicional |
| `schedule.penaltyEndDate` | Data de término total da pena |
| `notes` | Observações sobre o cálculo realizado |

---

## 🚀 Instalação e Execução

### Pré-requisitos

- **JDK 17** ou superior ([Download](https://openjdk.org/))
- **Gradle Wrapper** (já incluído no projeto)

### Passos para Executar Localmente

1. **Clone o repositório:**

```bash
git clone https://github.com/DuduArts01/Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end.git
cd Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end
```

2. **Execute os testes automatizados:**

```bash
# Windows
.\gradlew.bat test

# Linux/Mac
./gradlew test
```

3. **Inicie o servidor:**

```bash
# Windows
.\gradlew.bat run

# Linux/Mac
./gradlew run
```

4. **Confirmação de inicialização:**

Quando o servidor estiver pronto, você verá no terminal:

```
INFO  io.ktor.server.Application - Application started in X.XXX seconds.
INFO  io.ktor.server.Application - Responding at http://0.0.0.0:8080
```

5. **Acesse a API:**

A API estará disponível em: **http://localhost:8080/calculate**

---

## 🛠️ Tecnologias Utilizadas

- **[Kotlin](https://kotlinlang.org/)** - Linguagem de programação moderna e concisa
- **[Ktor](https://ktor.io/)** - Framework assíncrono para aplicações server-side
- **[Gradle](https://gradle.org/)** - Sistema de automação de build
- **[JUnit](https://junit.org/)** - Framework de testes unitários
- **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)** - Serialização JSON nativa do Kotlin

---

## 📁 Estrutura do Projeto

```
calculadora-penal-backend/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── Application.kt          # Entry point da aplicação
│   │   │   ├── routes/                 # Rotas da API
│   │   │   ├── models/                 # Modelos de dados
│   │   │   ├── services/               # Lógica de negócio
│   │   │   └── utils/                  # Utilitários e helpers
│   │   └── resources/
│   │       └── application.conf        # Configurações do Ktor
│   └── test/
│       └── kotlin/                     # Testes automatizados
├── gradle/
├── build.gradle.kts                    # Configurações do Gradle
├── gradlew.bat                         # Gradle Wrapper (Windows)
├── gradlew                             # Gradle Wrapper (Linux/Mac)
├── settings.gradle.kts
└── README.md
```

---

## 💡 Exemplos de Uso

### Usando cURL

```bash
curl -X POST http://localhost:8080/calculate \
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

### Usando JavaScript (Fetch API)

```javascript
const calculatePenalty = async () => {
  const response = await fetch('http://localhost:8080/calculate', {
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
  
  const data = await response.json();
  console.log(data);
};

calculatePenalty();
```

### Usando Python (Requests)

```python
import requests

url = "http://localhost:8080/calculate"
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

response = requests.post(url, json=payload)
print(response.json())
```

---

## 🧪 Testes

O projeto conta com uma suíte completa de testes automatizados cobrindo:

- ✅ Cálculo de remição por trabalho
- ✅ Cálculo de remição por estudo
- ✅ Cálculo de remição por leitura
- ✅ Progressão de regime para crimes comuns
- ✅ Progressão de regime para crimes hediondos
- ✅ Tratamento de réus primários e reincidentes
- ✅ Validação de datas e cálculos de detração

### Executar testes

```bash
# Windows
.\gradlew.bat test

# Linux/Mac
./gradlew test
```

### Relatório de cobertura

```bash
# Windows
.\gradlew.bat jacocoTestReport

# Linux/Mac
./gradlew jacocoTestReport
```

O relatório estará disponível em: `build/reports/jacoco/test/html/index.html`

---

## 👥 Equipe e Autoria

- **Desenvolvedor:** Eduardo (Engenharia de Software)
- **Empresa Jurídica:** Cespedes Lourenço Advogados
- **Contato Institucional:** contato@cespedeslourenco.com.br

---

## 🗺️ Futuras Melhorias (Roadmap)

- [ ] **Persistência de dados** - Integração com PostgreSQL/MySQL
- [ ] **Autenticação JWT** - Sistema seguro de autenticação de usuários e advogados
- [ ] **Exportação PDF** - Geração automática de cronogramas em formato PDF
- [ ] **Integração PJe/BNMP** - Sincronização com sistemas judiciais nacionais
- [ ] **Documentação OpenAPI** - Especificação Swagger/OpenAPI 3.0
- [ ] **Docker** - Containerização da aplicação
- [ ] **CI/CD** - Pipeline automatizado de integração e deploy
- [ ] **Logs estruturados** - Sistema de logging avançado
- [ ] **Métricas e monitoring** - Integração com Prometheus/Grafana
- [ ] **API Gateway** - Rate limiting e controle de acesso

---

## 📚 Referências

- [Lei nº 7.210/1984](http://www.planalto.gov.br/ccivil_03/leis/l7210.htm) - Lei de Execução Penal (LEP)
- [Lei nº 13.964/2019](http://www.planalto.gov.br/ccivil_03/_ato2019-2022/2019/lei/L13964.htm) - Pacote Anticrime
- [Ktor Framework Documentation](https://ktor.io/docs/) - Documentação oficial do Ktor
- Material técnico e diretrizes - Setor operacional da Cespedes Lourenço Advogados

---

## 📄 Licença

Este projeto é licenciado sob a [Licença MIT](LICENSE) - veja o arquivo LICENSE para mais detalhes.

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor, siga estes passos:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

---

## 📞 Suporte

Para questões técnicas ou jurídicas relacionadas ao projeto, entre em contato:

- **Email:** contato@cespedeslourenco.com.br
- **Issues:** [GitHub Issues](https://github.com/DuduArts01/Projeto-Extensao-Cespedes-Lourenco-Advogados-Back-end/issues)

---

<p align="center">
  Desenvolvido com ⚖️ por <strong>Cespedes Lourenço Advogados</strong>
</p>
