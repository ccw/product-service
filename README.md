Product Service PoC
===============

This project currently contains four sub projects --

 *  Solr Client

    A wrapper to communicate external Solr servers and is commonly used by other sub project

 *  Config Server

    A configuration server to serve for client to fetch necessary environment properties.

 *  Admin API

    The administration API service application

 *  Store API

    The front end API service for the composition of JSON information required by storefronts

 *  and a sample site web application

    To demonstrate the usage of store API service

This project is built with Java 8 and [Spring Boot](http://projects.spring.io/spring-boot/) and managed by [Gradle](http://http://www.gradle.org).

To build all the sub projects at once, please simply type `gradle clean build` under the project root in the terminal command line (or run the task in any supported IDE).
Then using `java -jar <SUB_PROJECT_DIST_JAR>` to instantiate any of the desired services.

Or, by typing `gradle clean :<SUB_PROJECT_NAME>:bootRun` to startup the desired service with Spring Boot directly.