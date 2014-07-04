java-api-streaming
==================

A simple demo app in Java for getting streaming rates using OANDA
API. This example uses [apache httpcomponents](http://hc.apache.org/httpcomponents-client-ga/) for https
connections and [json-simple](https://code.google.com/p/json-simple/) for json decoding.

### Setup

Clone this repo to the location of your choice.

Modify the following variables in
[JavaApiStreaming.java](src/main/java/JavaApiStreaming.java):

    domain
    access_token
    account_id
    instruments

Maven is used for building. Install from http://maven.apache.org/download.cgi.
On Ubuntu, you can run `sudo apt-get install maven`. 
    
To create and execute the jar file, run

    mvn package
    java -jar target/streaming-example-java-1.jar

### Sample Output

    EUR_USD
    2014-03-21T17:56:09.932922Z
    1.37912
    1.37923
    -------
    USD_CAD
    2014-03-21T17:56:20.776248Z
    1.12011
    1.12029
    -------
    USD_JPY
    2014-03-21T17:56:13.668154Z
    102.262
    102.275

### More Information

http://developer.oanda.com/docs/v1/stream/#rates-streaming
