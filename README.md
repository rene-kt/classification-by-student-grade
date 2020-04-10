# Pre-Matriculas
Sistema feito para minha própria escola com um gerenciamento de cadastro e listagem por ranking dos alunos.

#### Configuração -> 
Por mais que o sistema seja bem exclusivo e específico, vou deixar como fazer para que você consiga rodá-lo em seu computador. 

#### Para executar o projeto, será necessário instalar os seguintes programas:
- JDK 8 e JDK 7 (Pois o iReport na versão instalada na lib só funciona com JDK 7)
- Netbeans/Eclipse ou qualquer IDE de sua preferência.
- Executar o comando SQL de criação da base de dados e de suas respectivas tarefas, pois como foi meu primeiro projeto, optei por não 
utilizar ORM que automatiza essa parte do banco de dados.
- Baixar as respectivas libs de conexão com banco de dados e do iReport que podem ser facilmente encontrados [aqui](https://mvnrepository.com)

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

 Dessa forma, caso não houvesse o número de pessoas suficientes nas cotas dos respecitivos estilos, o sistema fazia o preenchimento auto
mático, dando toda a autonomia e agilidade do processo que antes era **todo papelado**, e acabou por ficar **automatizado**.

