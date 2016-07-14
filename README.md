# Ping pong camel
Demo project for testing Apache Camel features using Spring Boot.

Project layout
==============

* `pingpong-common` Contains all common files used by the rest of the maven modules.
* `pingpong-beans` Play ping pong between two Java beans.
* `pingpong-jms` Play ping pong between using a JMS broker.

Installation
============
Download the project and in parent folder run

    mvn clean install
  
Then you can start one of the contained bundles by simply running

    java -jar pingpong-beans/target/pingpong-beans.war

You can also check Camel route metrics from the remote shell running

    ssh -p 2000 user@localhost (password is 'password123')
