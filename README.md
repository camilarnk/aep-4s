# 🌳 OcupaMais

Sistema de relatos e engajamento em espaços públicos.  
Projeto desenvolvido para o 4º semestre do curso.

## 🧩 Tecnologias
- Java 17 + Spring Boot
- Maven
- HTML, CSS, JavaScript

## 🚀 Como rodar
```bash
mvn clean install
mvn spring-boot:run
```
## Estrutura do Projeto

### Backend
```bash
/ → Raiz do projeto  
├─ /src → Código-fonte Java  
│ ├─ /model → Classes de modelo (entidades do sistema)  
│ │ Ex.: Usuario.java, Publicacao.java  
│ ├─ /dao → Classes de acesso a dados (CRUD com o banco)  
│ │ Ex.: UsuarioDAO.java, PublicacaoDAO.java  
│ ├─ /util → Classes utilitárias, como conexão com o   
│ │ Ex.: Conexao.java  
│ └─ /main → Classe principal para testar ou iniciar a aplicação  
│   Ex.: Main.java  
├─ /lib → Bibliotecas externas (.jar), ex.: driver JDBC do MySQL  
├─ .vscode → Configurações do Visual Studio Code  
└─ README.md → Documentação do projeto  
```

### Descrição das Pastas

- **`src/model`**: Define os objetos de negócio do sistema, cada classe representa uma tabela do banco de dados.
- **`src/dao`**: Contém métodos para inserir, atualizar, excluir e buscar dados no banco (CRUD).
- **`src/util`**: Contém classes utilitárias, como a conexão JDBC com o MySQL (`Conexao.java`).
- **`src/main`**: Classe principal que inicia a aplicação ou realiza testes das funcionalidades antes de criar interface web.
- **`lib`**: Contém bibliotecas externas necessárias para o projeto, como o `mysql-connector.jar`.
- **`.vscode`**: Configurações específicas do VS Code (não obrigatório para compilação, mas facilita o desenvolvimento).

### Frontend
```bash
/ → Raiz do projeto  
├── pom.xml
├── src/
│   └── ... (backend)
└── frontend/
    ├── index.html
    ├── css/
    │   └── style.css
    ├── js/
    │   └── main.js
    ├── usuario/
    │   ├── cadastro.html
    │   ├── login.html
    │   ├── perfil.html
    │   ├── publicacoes.html
    │   ├── mapa.html
    │   └── apoio.html
    ├── admin/
    │   ├── dashboard.html
    │   ├── espacos.html
    │   ├── relatorios.html
    │   ├── notificacoes.html
    │   └── publicacoes.html
    └── assets/
        └── logo.png
```