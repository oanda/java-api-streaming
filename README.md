FX-OANDA-Streaming
==================

A simple demo app in Java 8 using Spring Boot for getting streaming rates using OANDA
API. This example uses [Spring RestTemplate](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) for https
connections.

### Setup

Clone this repo to the location of your choice.

Modify the following variables in
[application.properites](src/main/resources/application.properties):

    domain
    access_token
    account_id
    instruments

Maven is used for building. Install from http://maven.apache.org/download.cgi.
On Ubuntu, you can run `sudo apt-get install maven`. On Mac using brew `brew install maven`
    
To create and execute the jar file, run

    mvn clean package
    java -jar target/oanda.streaming-0.0.1-SNAPSHOT.jar

### Sample Output

    2016-04-10 14:28:57.068  INFO 84369 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
    2016-04-10 14:28:57.121  INFO 84369 --- [  restartedMain] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
    2016-04-10 14:28:57.132  INFO 84369 --- [  restartedMain] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
    2016-04-10 14:28:57.305  INFO 84369 --- [  restartedMain] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
    2016-04-10 14:28:58.809  INFO 84369 --- [  restartedMain] i.c.fx.oanda.streaming.FXOANDAStreaming  : {
      "instrument" : "EUR_USD",
      "bid" : 1.13929,
      "ask" : 1.14029,
      "time" : "2016-04-08T21:08:58.499"
    }
    2016-04-10 14:28:58.813  INFO 84369 --- [  restartedMain] i.c.fx.oanda.streaming.FXOANDAStreaming  : {
      "instrument" : "USD_JPY",
      "bid" : 108.028,
      "ask" : 108.128,
      "time" : "2016-04-08T21:09:00.391"
    }
    2016-04-10 14:28:58.819  INFO 84369 --- [  restartedMain] i.c.fx.oanda.streaming.FXOANDAStreaming  : {
      "instrument" : "EUR_JPY",
      "bid" : 123.133,
      "ask" : 123.233,
      "time" : "2016-04-08T21:08:59.84"
    }


### More Information

http://developer.oanda.com/docs/v1/stream/#rates-streaming
