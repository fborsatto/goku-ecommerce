# Teste Abastece aí

Passos para rodar:

1. Clonar o repositório: https://github.com/fborsatto/goku-ecommerce.git

2. Na raiz do projeto rodar:

   2.1 - mvn clean install
   
   2.2 - docker-compose up --build

   2.3 - java -jar target/*.jar

A documentação das api se encontra endereços:

API de endereço -> http://localhost:8081/swagger-ui.html#/

Para consumir a API é preciso antes fazer a chamada de login:

curl --location --request POST 'localhost:8081/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=D93898CBF323281C9C7E7AFD46041E32' \
--data-raw '{
"login" : "user",
"password" : "user"
}'
