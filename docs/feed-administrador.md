# Feed do administrador

Interface nativa em Kotlin e XML, baseada nas páginas 41–43 do documento
`Cópia de Trabalho_Disp_Moveis_2026.pdf`.

## Executar no Android Studio

1. Abra a raiz deste projeto e configure o **Gradle JDK como 21**.
2. Sincronize o Gradle.
3. Selecione a configuração **Feed Administrador** e execute em um emulador
   ou dispositivo Android. A configuração inicia `FeedAdministradorActivity`.

O fluxo de introdução existente continua disponível na configuração padrão do app.

## Telas e transições

- Feed com próxima atividade, resumo do dia, semana e atividades de hoje.
- **Ver atividade** abre o painel expandido; **Diminuir atividade** retorna ao feed.
- Os cards **Musculação** e **Yoga** abrem seus respectivos painéis.
- **Substituir professor** e o status abrem os seletores do protótipo.
- **Adicionar atividade** abre o formulário visual, com título editável,
  professor, vagas, horário e status. Selecionar um horário abre o calendário
  de setembro de 2026 mostrado no documento.
- Selecionar uma data retorna ao formulário. Os botões de adicionar e cancelar
  fecham o painel. Voltar e tocar fora também fecham os painéis.

Todo o conteúdo é demonstrativo. As seleções alteram apenas o estado visual
do painel aberto. Não há cadastro, cancelamento real, autenticação, integração
com API ou persistência. A navegação inferior é a composição visual do feed;
as demais áreas não fazem parte desta entrega.

## Padrões e recursos

- Kotlin, Activities, XML Views e tema Material sem barra de ação, como no projeto.
- ViewBinding e componentes XML compartilhados para cards, indicadores e navegação.
- Fundo `#191A1E`, tipografia sans do repositório e recursos de cor/estilo
  concentrados em `res/values/admin_feed.xml`.
- Avatar e seta de retorno reutilizados do projeto. Ícones Início, Treinos,
  Perfil e marca MoemIA Fit reutilizados da branch `origin/feature/mirella`;
  a marca foi ajustada ao viewport do componente de navegação.
- Painéis com animação de entrada/saída, rolagem e tratamento das barras do sistema.
- O calendário usa um componente nativo com idioma português do Brasil.

## Verificação

Com JDK 21:

```powershell
.\gradlew.bat :app:assembleDebug :app:lintDebug
```

Build e lint executados sem erros. Os arquivos do feed não apresentam avisos
de lint. As transições de detalhes, professor, vagas, status, horário,
calendário e retorno foram conferidas em emulador Android API 37.
