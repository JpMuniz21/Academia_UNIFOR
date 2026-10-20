# Feed do administrador

Interface nativa em Kotlin e XML, baseada nas páginas 41–43 e nas imagens
fornecidas como referência.

## Executar no Android Studio

1. Atualize a branch `feat/feed-administrador`.
2. Abra a raiz do projeto e sincronize o Gradle.
3. Selecione **Feed Administrador**, escolha um emulador e execute.

O feed é a atividade inicial nesta branch. A configuração compartilhada usa
`academia-unifor.app.main`, o módulo Android executável. O Gradle seleciona
JDK 21 por `gradle/gradle-daemon-jvm.properties` (é necessário ter JDK 21 instalado).

As Activities usam Views nativas com referências de IDs dentro de cada layout,
seguindo o padrão original do projeto. Não dependem de classes geradas de
ViewBinding ou DataBinding.

## Telas e transições

- Feed com próxima atividade, resumo do dia, semana e atividades de hoje.
- **Ver atividade** abre o painel expandido na posição do card.
- **Diminuir atividade**, cancelar, voltar e tocar fora retornam ao feed.
- Musculação e Yoga abrem seus próprios painéis.
- Os seletores de professor, status, vagas e horário reproduzem os estados visuais.
- Adicionar atividade abre o formulário visual. Escolher horário abre o calendário.
- O calendário mostra setembro de 2026, com marcadores azuis quadrados e navegação mensal.

O conteúdo é demonstrativo: as seleções alteram apenas a apresentação do painel
aberto. Não há cadastro, autenticação, API ou persistência. A navegação inferior
representa visualmente as áreas do protótipo.

## Recursos visuais

League Spartan é usada nos títulos e botões; Poppins nas legendas e controles.
As fontes acompanham o aplicativo em arquivos locais e suas licenças estão em
`docs/licenses/`. Fontes obtidas do
[repositório oficial Google Fonts](https://github.com/google/fonts/tree/main/ofl).

Cores e estilos estão em `res/values/admin_feed.xml`. As medidas compactas atendem
às telas de telefone; `values-h1000dp` preserva o espaçamento do artboard mais alto.
O avatar, os cards, os seletores e o calendário foram ajustados à referência.
A marca MoemIA Fit e os ícones de navegação foram reutilizados do repositório.

## Verificação

```powershell
.\gradlew.bat :app:assembleDebug :app:lintDebug
```
