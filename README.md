Product Service PoC
===============

This is the project to showcase how micro services and Solr Cloud can be leveraged to fulfill the upcoming BMF business requirement. Currently there are some sub projects conducted to represent each basic functional roles in the overall archiecture --

 *  Config API

    The configuration API for other API services to fetch necessary environment specific properties. 

 *  Admin API

    The administration API for data manipulations. 

 *  Store API

    The front end API to be used for storefront data rendering. 

 *  Sample site

    The web site.

Technical Spec
----------------
This project is mainly implemented with [Spring Boot](http://projects.spring.io/spring-boot/), [Spring Data Solr](http://projects.spring.io/spring-data-solr/) and [Spring Cloud Config](http://cloud.spring.io/spring-cloud-config/).


Build and Run
----------------
[Gradle](http://http://www.gradle.org) and Java 8 are required to build and run this project. 

To build all the sub projects at once, please run `gradle clean build` under the project root (or with the assist of IDEs).
After the project being built, simply run `java -jar <SUB_PROJECT_DIST_JAR>` can instantiate any of the desired services.

Alternatively, run `gradle clean :<SUB_PROJECT_NAME>:bootRun` can also launch the desired service under Spring Boot directly.

