
```agsl
docker build -t sriramasunkara02/demo-mysql .

 docker run  --link heuristic_dijkstra  --name demo-mysql -e HOST=heuristic_dijkstra -e password=root -p 8081:8080 -d sriramasunkara02/demo-mysql 

```



table

```agsl

create table test.employee(

id int not null AUTO_INCREMENT,
name varchar(50) not null,
company varchar(50),
salary int,

PRIMARY KEY (id)


);
```