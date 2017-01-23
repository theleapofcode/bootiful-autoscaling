# bootiful-autoscaling
Spring boot actuator based custom autoscaling inspired by [Spring microservice](https://www.packtpub.com/application-development/spring-microservices) chaper 6.

## Steps to run,
	1. Configure host, user, password and command to run search-service on a new host/container in config properties
	
	2. Open project home in terminal and run maven build. 
    	`mvn clean install -DskipTests`
    	
    3. Start Rabbit MQ. 
    	`rabbitmq-server`

	4. Start config server
    	`java -jar bootiful-configserver/target/bootiful-configserver-1.0.0.jar`

	5. Start eureka server
    	`java -jar bootiful-eurekaserver/target/bootiful-eurekaserver-1.0.0.jar`

	6.Run below commands in separate terminal windows.
		`java -jar bootiful-lifecyclemanager/target/bootiful-lifecyclemanager-1.0.0.jar` 
		`java -jar bootiful-apigateway/target/bootiful-apigateway-1.0.0.jar`
		`java -jar bootiful-search/target/bootiful-search-1.0.0.jar`

	7. Hit the search api more than 10 times in a minute using Postman/curl

	8. Open eureka console at http://localhost:8671 to see two instances of search service
