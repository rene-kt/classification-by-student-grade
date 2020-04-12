# Pre-Matriculas
Sistema feito para minha própria escola com um gerenciamento de cadastro e listagem por ranking dos alunos. Eram divididos em 4 grupos: 
- Escola pública ampla concorrência
- Escola Particular ampla concorrência
- Escola pública com cota
- Escola particular com cota 

#### Configuração -> 
Por mais que o sistema seja bem exclusivo e específico, vou deixar como fazer para que você consiga rodá-lo em seu computador. 

#### Para executar o projeto, será necessário instalar os seguintes programas:
- JDK 8 e JDK 7 (Pois o iReport na versão instalada na lib só funciona com JDK 7)
- Netbeans/Eclipse ou qualquer IDE de sua preferência.
- Executar o comando SQL de criação da base de dados e de suas respectivas tarefas, pois como foi meu primeiro projeto, optei por não 
utilizar ORM que automatiza essa parte do banco de dados.
- Baixar as respectivas libs de conexão com banco de dados e do iReport que podem ser facilmente encontrados [aqui](https://mvnrepository.com)
- Para instalação, digite a seguinte linha de código numa pasta de sua preferência utilizando o git bash: 
```
git clone https://github.com/reness0/Pre-Matriculas/edit/master/README.md
```
**Ou então baixe o winrar do projeto [aqui](https://github.com/reness0/Pre-Matriculas/archive/master.zip)**
## Features 
Por se tratar de um sistema de ranking baseada por notas, pode ser usado por escolas no geral ou por qualquer seletiva que tenha como
critério notas.

## Usabilidade 
O projeto foi usado pela secretaria da escola EEEP Alfredo Nunes de Melo para realizar a pré-matrícula da mesma. Elas lançavam todas as 
notas do aluno numa tela onde continham os campos de textos das respectivas médias, e depois exbia na tabela organizando por critério 
decrescente de notas.

## Instruções SQL - Complexidade e dificuldades
A escola possuía um sistema de cotas e exigências bem complexo que resultou em instruções **enormes** de SQL -> 

|      Estilo      | Nª de vagas | 
|:----------------:|:-----------:|  
|      Pública     |      32     |
|    Particular    |      8      |
|   Cotas pública  |      10     |
| Cotas particular |      2      |

 Dessa forma, caso não houvesse o número de pessoas suficientes nas cotas dos  estilos, o sistema fazia o preenchimento auto
mático, dando toda a autonomia e agilidade do processo que antes era **todo papelado**, e acabou por ficar **automatizado**.

## Como funciona 

1. Um [método](https://pastebin.com/HqTMSxV6) ficava responsável por automizativar o ranking e organizar os critérios de forma dinâmica, por exemplo: 
> Supondo que na lista dos 32 alunos selecionados para a vaga de escola pública, desses apenas 9 cumprem o requisito de cota, então como diz na regra de negócio que precisam de 10 alunos cotistas, o sistema ficava encarregado de tirar o último da lista e buscar por algum outro que tivesse cota para aí assim completar a regra de negócio. E da mesma forma acontecia com escola de particular, mas só que com menos vagas -> 8 vagas, sendo 2 dessas para alunos cotistas.
2. O sistema sabia do número de cotas através de outro  [método](https://pastebin.com/5kkkFMeU) que fazia uma busca nos 32 alunos melhores colocados das escola pública e retornava quantos cotistas haviam, para aí fazer a lógica explicada no 1., e da mesma forma acontecia com a escola particular.

