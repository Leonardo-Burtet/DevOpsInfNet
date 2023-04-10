
Crie um projeto com Spring Boot expondo algumas APIS. O projeto pode ser o mais simples possível, um CRUD, um conversor de medidas etc. É importante construir o projeto através das seguintes regras:

Criar testes unitários para métodos utilizando:
JUNIT, MOCKITO, TESTContainers (Opcional).
Expor o health check do seu projeto com o Actuator.
Exportar métricas do seu projeto para o formato do Prometheus utilizando o micrometer.
Exportar LOGs do seu projeto para alguma ferramenta de logs. (Ex. Papertrail)
Exportar dados do seu projeto para o Zipkin.
Criar Script no terraform em qualquer provedor que crie apenas uma máquina virtual de qualquer formato. Esse script deve possuir 1 arquivo main, um arquivo de variáveis e um arquivo de outputs.
Criar um pipeline de build do seu projeto no Gitlab.
