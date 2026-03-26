# App-IronKey
App Android desenvolvido em Kotlin com Jetpack Compose para geração e gerenciamento de senhas seguras, com funcionalidade de cópia rápida para a área de transferência.

# 🔐 IronKey - Gerador de Senhas

Aplicativo Android desenvolvido em **Kotlin + Jetpack Compose** com o objetivo de auxiliar na criação e manipulação de **senhas seguras** de forma simples, rápida e intuitiva.

---

## 📱 Sobre o Projeto

O **IronKey** é um app minimalista que permite:

- Criar senhas diretamente na interface
- Definir limite máximo de caracteres
- Visualizar o tamanho da senha em tempo real
- Copiar a senha com um clique para a área de transferência

Ideal para estudos de **Android moderno com Compose** e boas práticas de UI.

---

## 🚀 Tecnologias utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- Android SDK

---

## 🧠 Conceitos aplicados

- Gerenciamento de estado (`remember`, `mutableStateOf`)
- Composables reutilizáveis
- Layouts com `Column`, `Box` e `Modifier`
- Scroll vertical com `verticalScroll`
- Uso de ícones do Material Design
- Interação com sistema Android (ClipboardManager)
- Feedback ao usuário com `Toast`

---

## 🎨 Interface

- Layout moderno com Material Design 3
- Logo central com estilo circular
- Campo de senha com:
  - Ícone de segurança 🔒
  - Botão de copiar 📋
- Contador de caracteres em tempo real

---

## ⚙️ Funcionalidades

### 🔑 Entrada de senha
O usuário pode digitar uma senha manualmente, respeitando o limite máximo de caracteres.

### 📏 Controle de tamanho
A senha é limitada por um valor configurável (`maxCharacter`), evitando entradas maiores que o permitido.

### 📋 Copiar senha
Ao clicar no ícone de copiar:
- A senha é enviada para a área de transferência
- Um feedback visual é exibido via Toast

```kotlin
fun copyPassword(context: Context, password: String)
