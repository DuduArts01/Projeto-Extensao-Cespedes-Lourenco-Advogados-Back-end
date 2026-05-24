# 🔧 Correções Necessárias para Deploy

## ❌ Problemas Encontrados no build.gradle.kts

### 1. **Plugins Duplicados**
```kotlin
// ❌ ERRADO - Você tinha:
plugins {
    kotlin("jvm") version "1.9.22"           // Declaração explícita
    id("io.ktor.plugin") version "2.3.7"    // Declaração explícita
    application
    alias(libs.plugins.kotlin.jvm)           // DUPLICADO via catalog
    alias(ktorLibs.plugins.ktor)             // DUPLICADO via catalog
    alias(libs.plugins.kotlin.serialization)
}

// ✅ CORRETO - Use apenas os alias:
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    application
}
```

### 2. **mainClass Duplicado**
```kotlin
// ❌ ERRADO - Você tinha:
application {
    mainClass.set("com.cespede.ApplicationKt")      // Primeira declaração
    mainClass.set("io.ktor.server.netty.EngineMain") // Segunda declaração (sobrescreve)
}

// ✅ CORRETO - Use apenas uma:
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}
```

**IMPORTANTE:** Use `io.ktor.server.netty.EngineMain` quando estiver usando `application.conf` para configurar o Ktor (que é o seu caso).

---

## 📝 Arquivos Que Você Precisa Atualizar

### 1️⃣ **build.gradle.kts** (substitua pelo arquivo que gerei)

Baixe o arquivo `build.gradle.kts` que gerei e substitua o seu.

**Principais mudanças:**
- ✅ Removida duplicação de plugins
- ✅ Removida declaração duplicada de mainClass
- ✅ Mantido JDK 21 (compatível com seu projeto)
- ✅ Mantida configuração do fatJar com nome `app.jar`

### 2️⃣ **Dockerfile** (substitua pelo arquivo atualizado que gerei)

Atualizei o Dockerfile para usar **JDK 21** ao invés de 17, compatível com seu `jvmToolchain(21)`.

**Mudanças:**
```dockerfile
# Build stage - Atualizado de JDK 17 para JDK 21
FROM gradle:8.5-jdk21 AS build

# Runtime stage - Atualizado de JDK 17 para JDK 21
FROM eclipse-temurin:21-jre-alpine
```

### 3️⃣ **application.yaml** (verifique se existe)

Como você está usando `ktorLibs.server.config.yaml`, você provavelmente tem um arquivo `application.yaml` ao invés de `application.conf`.

**Localize:** `src/main/resources/application.yaml`

**Deve estar assim:**

```yaml
ktor:
  deployment:
    host: 0.0.0.0  # CRÍTICO: deve ser 0.0.0.0
    port: 8080
    port: ${PORT}  # Lê variável de ambiente
  application:
    modules:
      - com.cespede.ApplicationKt.module  # Ajuste para o caminho correto do seu módulo
```

**OU se for application.conf:**

```hocon
ktor {
    deployment {
        host = "0.0.0.0"
        port = 8080
        port = ${?PORT}
    }
    application {
        modules = [ com.cespede.ApplicationKt.module ]
    }
}
```

---

## 🎯 Checklist de Arquivos

Antes de fazer o push, verifique:

- [ ] ✅ `build.gradle.kts` corrigido (sem duplicações)
- [ ] ✅ `Dockerfile` atualizado para JDK 21
- [ ] ✅ `application.yaml` ou `application.conf` com host `0.0.0.0`
- [ ] ✅ `.dockerignore` na raiz
- [ ] ✅ `gradlew` com permissão de execução

---

## 🚀 Comandos Para Aplicar as Correções

```bash
# 1. Substitua os arquivos pelos que gerei
# - build.gradle.kts
# - Dockerfile
# - .dockerignore (se ainda não tiver)

# 2. Adicione permissão ao gradlew
git update-index --chmod=+x gradlew

# 3. Verifique o application.yaml
# Certifique-se que host: 0.0.0.0 e port: ${PORT}

# 4. Teste localmente (opcional mas recomendado)
./gradlew clean build

# 5. Commit e push
git add .
git commit -m "fix: corrige build.gradle.kts e atualiza Dockerfile para JDK 21"
git push origin main
```

---

## ⚠️ Sobre o mainClass

Existem **duas formas** de iniciar uma aplicação Ktor:

### **Opção 1: EngineMain (Recomendado)** ✅
```kotlin
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}
```
- ✅ Usa `application.yaml` ou `application.conf`
- ✅ Mais configurável
- ✅ Melhor para produção

### **Opção 2: Application.kt direto**
```kotlin
application {
    mainClass.set("com.cespede.ApplicationKt")
}
```
- ⚠️ Precisa configurar tudo em código
- ⚠️ Menos flexível

**Como você está usando `ktorLibs.server.config.yaml`, você DEVE usar a Opção 1!**

---

## 🐛 Possível Erro se Não Corrigir

Se não corrigir o `mainClass` duplicado, o Render pode falhar com:

```
Error: Could not find or load main class com.cespede.ApplicationKt
```

Ou pior: pode iniciar, mas não ler as configurações do `application.yaml` corretamente.

---

## ✅ Depois de Aplicar as Correções

1. Faça push do código corrigido
2. No Render, configure conforme as imagens que você mostrou
3. Clique em "Create Web Service"
4. Aguarde o build (pode demorar 5-10 minutos na primeira vez)

Se tudo der certo, você verá:

```
==> Starting service with 'java -jar app.jar'
INFO  io.ktor.server.application - Application started in X.XXX seconds.
INFO  io.ktor.server.application - Responding at http://0.0.0.0:8080
```

🎉 Sucesso!
