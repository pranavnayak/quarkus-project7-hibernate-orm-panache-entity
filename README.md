# quarkus-project7-hibernate-orm-panache-entity
 A Sample Java Microservice Project built with Quarkus demonstrating  Hibernate ORM PanacheEntity and JPA with PostgreSQL using the active record pattern, which is diferent from that used in the next project numbered 8, which uses the repository pattern to access the entities.

To define a Panache entity, simply extend PanacheEntity, annotate it with @Entity and add your columns as public fields.You can put all your JPA column annotations on the public fields. If you need a field to not be persisted, use the @Transient annotation on it.

Add custom queries on your entities inside the entities themselves. That way, you and your co-workers can find them easily, and queries are co-located with the object they operate on. Adding them as static methods in your entity class is the Panache Active Record way.
 
# Docker Postgresql

docker run --name my_db -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -e POSTGRES_DB=my_db -p 5432:5432 postgres:10.5

docker start my_db

![This is an image](https://github.com/pranavnayak/quarkus-project7-hibernate-orm-panache-entity/blob/main/1.JPG)
![This is an image](https://github.com/pranavnayak/quarkus-project7-hibernate-orm-panache-entity/blob/main/2.JPG)
![This is an image](https://github.com/pranavnayak/quarkus-project7-hibernate-orm-panache-entity/blob/main/3.JPG)

