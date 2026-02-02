# 🎭 CarnaUai

Aplicativo mobile para descobrir **onde e quando estão acontecendo os blocos oficiais de carnaval**, de forma organizada, confiável e simples.

O projeto nasce como um **MVP focado em informação clara e mapa estático**, com arquitetura preparada para evoluir futuramente para interações e mapa em tempo real.

---

## 🚀 Proposta de Valor

> **“Onde e quando estão acontecendo os blocos oficiais, de forma organizada e confiável?”**

O CarnaUai ajuda o usuário a:

* Descobrir blocos por dia e bairro
* Ver detalhes completos de cada bloco
* Organizar sua agenda de carnaval
* Receber avisos antes do início dos blocos
* Visualizar tudo em um mapa simples

---

## ✅ Escopo do MVP

### Funcionalidades incluídas

* 📅 Lista de blocos por dia
* 📍 Filtro por bairro
* 🥁 Detalhes do bloco (nome, horário, local, estilo)
* ⭐ Favoritar blocos
* 🗓️ Agenda do usuário
* 🗺️ Mapa estático (sem tempo real)
* 🔔 Push notification avisando início do bloco

### Fora do MVP (futuro)

* Chat
* Feed social
* Localização contínua do usuário
* Crowd / lotação em tempo real
* Interações colaborativas (banheiro, bloco cheio, etc)

---

## 🏗️ Arquitetura (Modular Monolith)

O backend está organizado em **módulos por bounded context**, prontos para evoluir para microsserviços no futuro:

```
com.br.carnauai
├── CarnauaiApplication.java
├── shared/                          # Código compartilhado entre módulos
│   ├── config/security/             # Configurações globais (ex: Security)
│   ├── kernel/usuario/              # Entidade Usuario (referenciada por vários módulos)
│   └── util/location/              # Utilitários (Location, LocationService)
└── modules/
    ├── agenda/                      # Módulo Agenda do usuário
    │   ├── domain/                  # Entidades (Agenda)
    │   ├── application/             # Casos de uso (AgendaService)
    │   ├── infrastructure/         # Persistência (AgendaRepositoryJpa)
    │   └── api/                     # REST (AgendaController)
    ├── bloco/                       # Módulo Catálogo de Blocos
    │   ├── domain/                  # Bloco, BlocoDia, Bairro
    │   ├── application/
    │   ├── infrastructure/
    │   └── api/
    ├── favorito/                    # Módulo Favoritos
    │   ├── domain/
    │   ├── application/
    │   ├── infrastructure/
    │   └── api/
    └── notificacao/                 # Módulo Notificações
        ├── domain/
        └── infrastructure/
```

**Dependências entre módulos (para futura extração):**
- **agenda** → shared (Usuario), modules.bloco (BlocoDia)
- **favorito** → shared (Usuario), modules.bloco (Bloco)
- **notificacao** → shared (Usuario)

Cada módulo pode ser extraído como microsserviço: basta criar um novo projeto Maven, copiar o pacote do módulo e trocar referências diretas por chamadas HTTP ou eventos.

---

## 🧠 Stack Tecnológica

### 🔧 Backend

* **Java 17**
* **Spring Boot 3.x**
* Spring Web (REST API)
* Spring Data JPA
* Spring Security (JWT)
* Spring Validation
* Spring Scheduling
* Flyway (migrations)

### 🗄️ Banco de Dados

* **PostgreSQL**
* Coordenadas geográficas usando `latitude` e `longitude` (DECIMAL)
* ❌ PostGIS **não utilizado no MVP** (planejado para versões futuras)

### 📱 Mobile

* **React Native (Expo)**
* Aplicativo único para iOS e Android

### 🗺️ Mapa

* Google Maps **ou** Mapbox
* Marcadores fixos por bloco

### 🔔 Push Notification

* Firebase Cloud Messaging (FCM)
* Disparo via backend (agendado)

### ☁️ Infraestrutura (MVP)

* Sem Docker no início
* Deploy simples (Railway / Render / VPS futuramente)

---

## 🗂️ Modelagem Básica (MVP)

### Bloco

* id
* nome
* data
* hora_inicio
* hora_fim
* bairro
* latitude
* longitude
* estilo
* descricao
* oficial (boolean)

### Usuário

* id
* email
* senha
* created_at

### Favorito

* usuario_id
* bloco_id

### Agenda

* usuario_id
* bloco_id
* lembrete_ativado

---

## 🔐 Autenticação

* Login com email e senha
* Autenticação via JWT
* Sem login social no MVP

---

## 📈 Visão de Evolução

O projeto é planejado para crescer **sem refatorações drásticas**:

### Próximas versões

* Uso de **PostGIS** para geolocalização avançada
* Interações em tempo real no mapa
* Crowd / lotação colaborativa
* WebSocket ou Firebase Realtime
* Feed social e chat

---

## 🧪 Ambiente de Desenvolvimento

### Requisitos

* Java 17+
* PostgreSQL
* Node.js (para o app mobile)

---

## 📌 Status do Projeto

🚧 **Em desenvolvimento (MVP)**

---

## ✍️ Autor

**Sóstenes Rodrigues**
Desenvolvedor Backend Java

---

CarnaUai — informação certa, no lugar certo, na hora certa. 🎉
