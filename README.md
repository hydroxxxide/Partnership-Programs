## О проекте  

Данный проект - незамысловатый API сервер работающий по принципу _"subscribe-to-program"_
  
В приложении есть пользователи и партнерские программы. В приложении можно создавать новых пользователей. Пользователи могут подписываться на партнёрские программы. Один пользователь может быть подписан на много программ.


Помимо стандартных CRUD, реализованы также такие API методы, как:
	
- создание новых пользователей
- получение информации по конкретному пользователю
- получение списка всех программ 
- подписка пользователя на программу
- отписка пользователя от программы
- получение списка программ, на которую подписан пользователь 
- получение списка популярных программ (популярность измеряется количеством подписанных пользователей)
- блокировка пользователя программой (при блокировке пользователь автоматически отписывается от программы, блокируются также его попытки подписаться на данную программу, при просмотре списка программ данным пользователем, программа которая его заблокировала не отображается в списке программ для текущего пользователя)

Ссылка на workspace с постман коллекцией - 
https://www.postman.com/java-team-f22/workspace/parntership-programs-workspace/collection/25576057-ea628691-cc9c-403d-9c18-657f040654a5?action=share&creator=25576057

## Установка 
1. Создайте новую базу данных _"yourdatabase"_ используя PgAdmin (вы можете заменить имя вашей бд "yourdatabase" на любое другое)
2. Добавьте в application.properties следующий код (поле "yourdatabase" измените на имя вашей новой базы данных):
```sh
   server.port=9090
spring.datasource.url=jdbc:postgresql://localhost:5432/имя-вашей-бд
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create

spring.datasource.driver-class-name=org.postgresql.Driver
```
3. Запустите приложение в PartnershipProgramsApplication.
4. После первого запуска, замените ```sh spring.jpa.hibernate.ddl-auto=create field``` на ```sh spring.jpa.hibernate.ddl-auto=update```
