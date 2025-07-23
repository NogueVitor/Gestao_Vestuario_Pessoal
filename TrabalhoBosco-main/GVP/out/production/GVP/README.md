# Gestor de Vestuário Pessoal (GVP)

## Descrição
Sistema completo para gerenciamento de vestuário pessoal desenvolvido em Java com interface gráfica Swing. O sistema permite gerenciar itens de vestuário, criar looks, controlar empréstimos, registrar lavagens e visualizar estatísticas de uso.

## Funcionalidades Implementadas

### 1. Gerenciamento de Itens
- **Adicionar itens**: Camisa, Calça, Saia, Relógio, Roupa Íntima
- **Editar e remover itens**
- **Registrar uso de itens**
- **Atributos**: nome, cor, tamanho, loja de origem, conservação, foto

### 2. Sistema de Empréstimos (Interface IEmprestavel)
- **Emprestar itens** (exceto roupas íntimas)
- **Registrar devolução**
- **Calcular dias desde o empréstimo**
- **Métodos implementados**: registrarEmprestimo, quantidadeDeDiasDesdeOEmprestimo, registrarDevolucao

### 3. Gerenciamento de Looks
- **Criar, editar e remover looks**
- **Modificação por sobrecarga**: modificar uma ou múltiplas partes do corpo
- **Registrar utilizações** com data, período e evento
- **Associação automática** de itens por parte do corpo

### 4. Sistema de Lavagens (Interface ILavavel)
- **Registrar lavagens** de múltiplos itens
- **Histórico de lavagens**
- **Itens não laváveis**: relógios e pulseiras são excluídos automaticamente

### 5. Estatísticas Completas
- **Itens mais e menos usados**
- **Looks mais utilizados**
- **Itens mais lavados**
- **Itens em empréstimo**
- **Resumo geral** do vestuário

### 6. Persistência de Dados
- **Salvamento automático** em arquivos .dat
- **Carregamento automático** na inicialização
- **Serialização** de objetos Java

## Conceitos de Orientação a Objetos Utilizados

### Classes Abstratas
- `Item`: classe base para todos os itens de vestuário

### Interfaces
- `IEmprestavel`: define comportamento para itens emprestáveis
- `ILavavel`: define comportamento para itens laváveis

### Herança
- `Camisa`, `Calca`, `Saia`, `Relogio`, `RoupaIntima` estendem `Item`

### Polimorfismo
- Implementação diferenciada das interfaces por cada tipo de item
- Sobrecarga de métodos no `Look` para modificação

### Encapsulamento
- Atributos privados com getters/setters
- Métodos de acesso controlado

### Composição
- `Look` contém múltiplos `Item`
- `Lavagem` contém múltiplos `ILavavel`

## Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Sistema operacional com suporte a Java Swing

### Compilação
```bash
javac *.java
```

### Execução
```bash
java main
```

## Estrutura do Projeto

```
GVP/
├── main.java                 # Classe principal
├── GestorVestuarioGUI.java   # Interface gráfica principal
├── GerenciadorVestuario.java # Lógica de negócio
├── Item.java                 # Classe abstrata base
├── IEmprestavel.java         # Interface para empréstimos
├── ILavavel.java             # Interface para lavagens
├── Camisa.java               # Implementação de camisa
├── Calca.java                # Implementação de calça
├── Saia.java                 # Implementação de saia
├── Relogio.java              # Implementação de relógio
├── RoupaIntima.java          # Implementação de roupa íntima
├── Look.java                 # Classe para looks
├── Lavagem.java              # Classe para lavagens
├── itens.dat                 # Arquivo de dados (gerado automaticamente)
├── looks.dat                 # Arquivo de dados (gerado automaticamente)
└── README.md                 # Este arquivo
```

## Interface Gráfica

O sistema possui 5 abas principais:

1. **Itens**: Gerenciamento completo de itens de vestuário
2. **Looks**: Criação e gerenciamento de looks
3. **Empréstimos**: Controle de itens emprestados
4. **Lavagens**: Registro de lavagens
5. **Estatísticas**: Visualização de dados e relatórios

## Recursos Técnicos

- **GUI**: Java Swing com layout responsivo
- **Persistência**: Serialização de objetos
- **Streams**: Uso de Java 8 Streams para filtragem e ordenação
- **Collections**: ArrayList, HashMap, List
- **Data/Hora**: LocalDate para controle temporal
- **Padrões**: Observer (eventos GUI), Strategy (interfaces)

## Funcionalidades Futuras

- Edição completa de itens e looks
- Importação/exportação de dados
- Relatórios em PDF
- Sistema de fotos
- Backup automático
- Filtros avançados de busca

## Autor
Sistema desenvolvido seguindo as especificações do projeto acadêmico de Programação Orientada a Objetos.