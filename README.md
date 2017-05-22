  [![Build Status](https://travis-ci.org/bootique-examples/bootique-cayenne-demo.svg)](https://travis-ci.org/bootique-examples/bootique-cayenne-demo)
# bootique-cayenne-demo

A simple example that explains how to use [Cayenne](https://cayenne.apache.org) integrated for [Bootique](https://bootique.io).
Here [Cayenne Commands](http://bootique.io/docs/0/bootique-docs/index.html#d50e385) are used to inject Cayenne ServerRuntime.
It can be injected basing on your task (e.g. in web resource).
See in addition [an example for LinkRest](https://github.com/bootique-examples/bootique-linkrest-demo) using Cayenne.
   
*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo
      
Here is how to build it:
        
        git clone git@github.com:bootique-examples/bootique-cayenne-demo.git
        cd bootique-cayenne-demo
        mvn package
      
## Run the Demo

Now you can check the options available in your app:
   
    java -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar
    
    Option                                          Description
    ------                                          -----------    
    -c yaml_location, --config=yaml_location        Specifies YAML config location, which can be a file path or a URL.
    
    -h, --help                                      Prints this message.
    
    -H, --help-config                               Prints information about application modules and their configuration options.
    
    -i, --insert                                    Insert initial data into Derby db
    
    -s, --select                                    Select data from Derby db
 
Configure database settings in YAML file *run.yml*:
    
    jdbc:
        derby:
        url: jdbc:derby:target/demodb;create=true
        driverClassName: org.apache.derby.jdbc.EmbeddedDriver
        initialSize: 1

    cayenne:
        datasource: derby
        createSchema: true


Insert initial data into Derby database via *--insert* command:
    
    java  -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar --config=run.yml --insert

Simple select via *--select* command:

    java  -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar --config=run.yml --select

Output:
 
    INFO  [2017-05-19 12:36:04,060] main i.b.c.d.SelectDataCommand: Articles count on domain mysite1.example.org: 2
    
To listen on events use Cayenne [Lifecycle Events](https://cayenne.apache.org/docs/4.0/cayenne-guide/lifecycle-events.html).
 
The example provides post persist listener (see logs):
    
    NEW ARTICLE {<ObjectId:Article, id=3>; committed; [domain=>{<ObjectId:Domain, id=2>}; publishedOn=>Fri May 19 15:33:34 MSK 2017; title=>LinkRest Presentation; body=>Here is how to use LinkRest; tags=>(..)]} 


