# TODO LIST

## Arquivos para lembrar de atualizar
- README.md
- TODO.md

----

## TAREFAS INCREMENTAIS
- [x] Diagrama de classes
- [ ] Cartões CRC
- [ ] Prototipação da UI
- [ ] Documentação

## TAREFAS INICIAIS: (09/11/2017) - Realizado Por todos
- [x] Diagrama de classes
- [ ] Modelagem CRC

TAREFAS DE IMPLEMENTAÇÃO:
- Indexação (Fase 1 - 16/11/2017): Conterá operações relacionadas à leitura, interpretação e armazenamento de uma base de arquivos de texto.
    + [x] Organizar as palavras chave em um indice que será implementado com uma arvore balanceada, a qual cada nó terá a palavra chave e o arquivo associado.
    + [ ] Salvar esses dados e um arquivo texto (será o indice para a busca)

- Busca (Fase 2 - 28/11/2017): Conterá operações relacionadas à busca de palavras na base (árvores). O módulo de busca irá realizar uma busca de uma ou múltiplas palavras chaves na base de busca utilizando o índice.
    + [ ] Modo <OR>: Retornar todas as ocorrências em que pelo menos uma das palavras-chave seja encontrada.
    + [ ] Modo <AND>: Retornar todas as ocorrências nos arquivos em que todas as palavras chaves sejam encontradas.
    + [ ] Resultado: Apresentar uma lista de arquivos, com as respectivas linhas em que linha as palavras aparecem. Os arquivos devem ser ordenados de acordo com a quantidade de linhas em que as palavras aparecem; aqueles que possuem mais linhas relacionadas, devem aparecer primeiro. Caso tenham a mesma quantidade de linhas, o desempate será de acordo com a sua ordem lexicográfica.


- Interface gráfica (Entrega Final - 04/12/2017):
    + Indexação: O módulo de indexação permitirá gerenciar quais arquivos compõem a base de arquivos e irá criar o índice de busca.
        + [ ] Adição e remoção de arquivos;
        + [ ] Atualização dos indices dos arquivos da base de dados (Um conteudo do texto de um arquivo pode mudar);
        + [ ] Visualizar os arquivos que compoem a base de dados em ordem alfabetica (nome do arquivo e qtd de palavras);
        + [ ] Leitura de um arquivo de configuração com uma lista negra de palavras (distância de Levenshtein).
    
- [ ] DESAFIO (Entrega Final/ou não - 04/12/2017): implementar um "auto-corretor" para as palavras digitadas pelo usuário (distância de Levenshtein)
