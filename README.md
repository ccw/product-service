Product Service PoC
===============

This project currently contains four sub projects --

 *  Solr Client

    A wrapper to communicate external Solr servers and is commonly used by other sub project

 *  Admin API

    The administration API service application

 *  Store API

    The front end API service for the composition of JSON information required by storefronts

 *  and a sample site web application

    To demonstrate the usage of store API service

This project is built with Java 8 and [Spring Boot](http://projects.spring.io/spring-boot/) and managed by [Gradle](http://http://www.gradle.org).

To build all the sub projects at once, please simply type `gradle clean build` under the project root in the terminal command line (or run the task in any supported IDE).

To instantiate the sample site web application, please type `gradle clean :sample-site:bootRun` (or replace the `sample-site` with other sub project names to run other application as your desire.)