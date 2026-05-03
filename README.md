# Gestor de Vestuário Pessoal (GVP) - Sistema Completo de Organização de Guarda-Roupa

## Visão Geral

O **Gestor de Vestuário Pessoal (GVP)** é um sistema desktop desenvolvido em Java com interface gráfica Swing para organização e controle completo do guarda-roupa pessoal. O projeto é o trabalho final da disciplina CK0235 - TÉCNICAS DE PROGRAMAÇÃO I que oferece funcionalidades avançadas como gerenciamento de itens, criação de looks, controle de empréstimos, histórico de lavagens e estatísticas detalhadas de uso.

O sistema permite cadastrar diferentes tipos de peças (camisas, calças, saias, relógios e roupas íntimas), criar combinações de looks, acompanhar empréstimos de roupas para amigos, registrar lavagens e gerar relatórios estatísticos sobre o uso do vestuário. Com uma interface intuitiva e funcionalidades robustas, o GVP transforma a gestão do guarda-roupa em uma experiência organizada e eficiente.

---

## Índice

- [Funcionalidades Principais](#funcionalidades-principais)
- [Arquitetura do Sistema](#arquitetura-do-sistema)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Instalação e Configuração](#instalação-e-configuração)
- [Como Usar](#como-usar)
- [Tipos de Itens Suportados](#tipos-de-itens-suportados)
- [Sistema de Persistência](#sistema-de-persistência)
- [Interface do Usuário](#interface-do-usuário)

## Funcionalidades Principais

### Gerenciamento de Itens
- **Cadastro Completo**: Nome, tipo, cor, tamanho, loja de origem, estado de conservação e foto
- **Edição e Remoção**: Modificação de dados e exclusão de itens do sistema
- **Registro de Uso**: Acompanhamento da frequência de utilização de cada peça
- **Visualização de Fotos**: Exibição de imagens dos itens cadastrados

### Sistema de Looks
- **Criação de Combinações**: Monte looks completos com diferentes peças
- **Organização por Partes do Corpo**: Sistema inteligente que organiza itens por categoria
- **Registro de Utilização**: Acompanhe quando e onde cada look foi usado
- **Histórico Detalhado**: Data, período (manhã/tarde/noite) e evento de cada uso

### Controle de Empréstimos
- **Rastreamento Completo**: Saiba exatamente quem está com suas roupas
- **Histórico Temporal**: Acompanhe há quantos dias o item foi emprestado
- **Sistema de Devolução**: Registre facilmente o retorno dos itens
- **Alertas Visuais**: Interface dedicada para controle de empréstimos

### Gerenciamento de Lavagens
- **Registro de Lavagens**: Documente quando cada peça foi lavada
- **Seleção Múltipla**: Registre várias peças em uma única lavagem

### Estatísticas Avançadas
- **Peças Mais Usadas**: Identifique seus itens favoritos
- **Itens Esquecidos**: Descubra peças pouco utilizadas no armário
- **Looks Populares**: Veja quais combinações você mais usa
- **Frequência de Lavagens**: Acompanhe a higienização das peças

## Arquitetura do Sistema

### Padrão MVC (Model-View-Controller)
O projeto segue a arquitetura MVC para separação clara de responsabilidades:

```
src/com/gvp/
├── model/ 
├── view/ 
├── controller/  
├── persistence/
└── main/ 
```

### Sistema de Interfaces
- **IEmprestavel**: Interface para itens que podem ser emprestados
- **ILavavel**: Interface para itens que podem ser lavados


## Tecnologias Utilizadas

### Linguagem e Framework
- **Java SE 8+** - Linguagem principal do projeto
- **Swing** - Interface gráfica nativa do Java
- **AWT** - Sistema de janelas e eventos

### Persistência de Dados
- **Serialização Java** - Armazenamento de objetos em arquivos `.dat`
- **ObjectInputStream/ObjectOutputStream** - Leitura e escrita de dados
- **File I/O** - Gerenciamento de arquivos do sistema


## Estrutura do Projeto

```
gestor-vestuario-pessoal/
├── src/com/gvp/
│   ├── controller/
│   │   └── VestuarioController.java
│   ├── model/
│   │   ├── Item.java
│   │   ├── Camisa.java 
│   │   ├── Calca.java
│   │   ├── Saia.java 
│   │   ├── Relogio.java
│   │   ├── RoupaIntima.java
│   │   ├── Look.java
│   │   ├── Lavagem.java 
│   │   ├── IEmprestavel.java
│   │   └── ILavavel.java
│   ├── view/
│   │   └── GestorVestuarioGUI.java 
│   ├── persistence/
│   │   └── DataManager.java
│   └── main/
│       └── main.java
├── itens.dat 
├── looks.dat 
└── README.md
```

## Instalação e Configuração

### Pré-requisitos
- **Java Development Kit (JDK) 8 ou superior**
- **IDE Java** (IntelliJ IDEA, Eclipse, NetBeans, etc.)
- **Sistema Operacional**: Windows, macOS ou Linux

### Configuração do Ambiente
1. **Clone ou baixe o projeto** para seu ambiente local
2. **Importe o projeto** na sua IDE Java preferida
3. **Configure o JDK** nas configurações do projeto
4. **Compile o projeto** usando sua IDE ou linha de comando


## Como Usar

### Inicialização do Sistema
1. **Execute a aplicação** - A interface gráfica será aberta automaticamente
2. **Navegue pelas abas** - Cada funcionalidade possui sua própria seção
3. **Dados são salvos automaticamente** - Todas as alterações são persistidas

### Gerenciamento de Itens
1. **Aba "Itens"** - Acesse o gerenciamento de peças
2. **Adicionar Item** - Clique em "Adicionar Item" e preencha os dados
3. **Escolher Foto** - Selecione uma imagem para o item (opcional)
4. **Visualizar** - Clique em qualquer item da tabela para ver sua foto
5. **Editar/Remover** - Use os botões correspondentes com item selecionado

### Criação de Looks
1. **Aba "Looks"** - Acesse o sistema de combinações
2. **Criar Look** - Defina um nome para sua combinação
3. **Editar Look** - Adicione itens por parte do corpo (torso, pernas, etc.)
4. **Registrar Uso** - Documente quando e onde usou o look

### Controle de Empréstimos
1. **Aba "Empréstimos"** - Visualize itens emprestados
2. **Emprestar Item** - Selecione item e pessoa que receberá
3. **Acompanhar** - Veja há quantos dias cada item foi emprestado
4. **Devolver** - Registre o retorno do item emprestado

### Registro de Lavagens
1. **Aba "Lavagens"** - Acesse o histórico de lavagens
2. **Nova Lavagem** - Selecione múltiplos itens para lavar
3. **Histórico** - Acompanhe todas as lavagens registradas
4. **Estatísticas** - Veja quais peças são mais lavadas

### Estatísticas do Vestuário
1. **Aba "Estatísticas"** - Visualize relatórios detalhados
2. **Atualizar** - Clique para gerar estatísticas atualizadas
3. **Análise Completa** - Veja peças mais/menos usadas, looks populares, etc.

## Tipos de Itens Suportados

### Camisa
- **Características**: Emprestável e lavável
- **Parte do Corpo**: Torso Superior

### Calça
- **Características**: Emprestável e lavável
- **Parte do Corpo**: Pernas

### Saia
- **Características**: Emprestável e lavável
- **Parte do Corpo**: Pernas

### Relógio
- **Características**: Apenas emprestável
- **Parte do Corpo**: Pulso

### Roupa Íntima
- **Características**: Apenas lavável
- **Parte do Corpo**: Íntimo

## Sistema de Persistência

### Armazenamento de Dados
```java
//Arquivos de dados gerados automaticamente
itens.dat  - Serialização dos itens cadastrados
looks.dat  - Serialização dos looks criados
```

### Gerenciamento Automático
- **Salvamento Automático**: Dados são salvos a cada operação
- **Carregamento na Inicialização**: Sistema restaura estado anterior
- **Backup**: Os arquivos .dat servem como backup dos dados

## Interface do Usuário

### Design e Paleta de Cores
```
COR_PRINCIPAL   = #AD8B73  // Marrom principal
COR_SECUNDARIA  = #CEAB93  // Marrom secundário  
COR_DESTAQUE    = #E3CAA5  // Bege para botões
COR_FUNDO       = #FFFBE9  // Creme para fundo
```

### Componentes da Interface
- **Abas Organizadas**: Cada funcionalidade em sua própria seção
- **Tabelas Interativas**: Visualização clara dos dados
- **Diálogos Intuitivos**: Formulários simples para entrada de dados
- **Feedback Visual**: Mensagens de confirmação e erro
- **Layout Responsivo**: Interface adaptável a diferentes resoluções

### Usabilidade
- **Navegação Intuitiva**: Fluxo natural entre as funcionalidades
- **Seleção Visual**: Itens selecionados são destacados claramente
- **Mensagens Informativas**: Sistema comunica status das operações

---

## Características do Projeto

**Desenvolvido em**: Java com Swing  
**Padrão Arquitetural**: MVC (Model-View-Controller)  
**Persistência**: Serialização nativa Java  
**Interface**: Desktop GUI nativa  

---
