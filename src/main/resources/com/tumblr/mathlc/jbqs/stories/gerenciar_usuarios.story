Scenario: Usuário válido deve ser salvo.

Given um usuário A com e-mail foo@bar.baz
When A for salvo  
Then A deve ser persistido pelo repositório.

Scenario: Usuário sem e-mail não deve ser salvo.

Given um usuário B sem e-mail
When B for salvo  
Then a obrigatoriedade do e-mail deve impedir a operação.

Scenario: Usuário com e-mail inválido não deve ser salvo.

Given um usuário C com e-mail foo
When C for salvo  
Then a validade do e-mail deve impedir a operação.

Scenario: Consulta a usuários deve permitir filtro por nome.

Given os usuários:
|email|
|foo@domain.com|
|foobar@domain.com|
|bar@domain.com|
When consultar por foo
Then deve retornar os usuários foo@domain.com e foobar@domain.com.