# 🌳 OcupaMais

Sistema de relatos e engajamento em espaços públicos.  
Projeto desenvolvido para o **4º semestre do curso de Engenharia de Software**.

---

## 🧩 Tecnologias

**Backend:**
- Java 17
- Spring Boot
- MySQL
- Maven

**Frontend:**
- HTML5
- CSS3
- JavaScript

---

## 🚀 Como Rodar o Projeto

### 🧱 Pré-requisitos
Garanta que você tem instalado:
- **Java 17+**
- **Maven 3.9+**
- **MySQL Server**

---

### ⚙️ Configuração do Banco de Dados
No MySQL, crie o banco de dados
```sql
CREATE DATABASE ocupamais;
```
Faça um arquivo `src/main/resources/application.properties` com suas credenciais

---

### 🧰 Compilação e Execução
No terminal, dentro da raiz do projeto:
```bash
mvn clean install
mvn spring-boot:run
```
A aplicação será iniciada em: 
👉 http://localhost:8080

---

## 🧪 Testando a Conexão com o Banco
Para validar a comunicação com o MySQL:
1. Execute a classe:  
`src/main/java/util/TestaConexao.java` 
 
2. O console exibirá: 
    ```
    Conectado ao banco de dados
    ```
    ou  
    ```php-template
    Erro ao conectar: <detalhes>
    ```

---

## 📁 Estrutura do Projeto

### 🔙 Backend
```bash
/ → Raiz do projeto  
├── pom.xml                      # Configuração Maven e dependências
├── .gitignore                   # Arquivos ignorados pelo Git
├── README.md                    # Documentação do projeto
└── src/
    └── main/
        ├── java/
        │   ├── com/ocupamais/
        │   │   ├── controller/   # Controladores REST (endpoints)
        │   │   ├── dao/          # Acesso ao banco (CRUD via JPA)
        │   │   ├── dto/          # Objetos de transferência de dados (requisições)
        │   │   ├── model/        # Entidades do sistema (Usuario, Publicacao, etc.)
        │   │   ├── service/      # Lógica de negócio (intermediário entre Controller e DAO)
        │   │   └── Application.java  # Classe principal do Spring Boot
        │   ├── util/
        │   │   ├── Conexao.java       # Classe utilitária para conexão manual com MySQL
        │   │   └── TestaConexao.java  # Testa a conexão manualmente
        │   └── Main.java              # Classe auxiliar usada para testes manuais
        └── resources/
            └── application.properties # Configurações do banco (local, ignorado no Git)
```

### 🖥️ Frontend
```bash
frontend/
├── index.html
├── css/
│   └── style.css
├── js/
│   └── main.js
├── usuario/
│   ├── cadastro.html
│   ├── perfil.html
│   └── publicacoes.html
├── admin/
│   ├── notificacoes.html
│   ├── publicacoes.html
│   └── relatorios.html
└── assets/
    └── logo-arvore.png
```
