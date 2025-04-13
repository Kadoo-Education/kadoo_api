## Kadoo API - Arquitetura Spring Boot

Este projeto utiliza uma arquitetura em camadas com base no padrão **MVC (Model-View-Controller)**, ideal para garantir organização, manutenção e escalabilidade da aplicação.

```markdown




---

Estrutura de Pastas

```bash
├── controller      # Controladores REST (camada de entrada da aplicação)
├── model           # Entidades JPA (representam as tabelas do banco)
├── repository      # Interfaces JPA (acesso ao banco com Spring Data)
├── service         # Interfaces das regras de negócio
├── service.impl    # Implementações dos serviços
├── dto             # Data Transfer Objects (opcional por enquanto)
├── mapper          # Conversores entre DTO e entidade (ex: MapStruct)
└── config          # Configurações (segurança, CORS, autenticação, etc)
```

---

## 🔍 Componentes da Arquitetura

| Camada     | Responsabilidade                                                                 |
|------------|------------------------------------------------------------------------------------|
| Controller | Recebe as requisições HTTP e encaminha para a camada de serviço                   |
| Service    | Implementa as regras de negócio da aplicação                                       |
| Repository | Realiza a comunicação com o banco de dados (via Spring Data JPA)                  |
| Model      | Representa as entidades e tabelas da base de dados (com anotações JPA/Hibernate)  |

---

## ✅ Vantagens dessa Arquitetura

- ✅ **Separação clara de responsabilidades**
- 🛠️ **Fácil manutenção e testes**
- 🔁 **Reutilização de código**
- 🚀 **Escalabilidade** (facilita aplicação de DDD, CQRS ou até migração para microserviços)

---

## 💡 Tecnologias 

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Security (em breve)
- JWT (em breve)
- Lombok
- MapStruct (opcional)
```

---

Se quiser, posso completar esse README com instruções de como rodar o projeto localmente, configurar o banco de dados, ou até adicionar badges e links automáticos do GitHub. Deseja isso também?
