  [![Build Status](https://travis-ci.org/bootique-examples/bootique-cayenne-demo.svg)](https://travis-ci.org/bootique-examples/bootique-cayenne-demo)
# bootique-cayenne-demo

A simple example that explains how to use [Cayenne](https://cayenne.apache.org) integrated for [Bootique](https://bootique.io).

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
    
    OPTIONS
          -c yaml_location, --config=yaml_location
               Specifies YAML config location, which can be a file path or a URL.
    
          -h, --help
               Prints this message.
    
          -H, --help-config
               Prints information about application modules and their configuration options.
    
          -i, --insert
               Insert initial data into db
    
          -s, --select
               Select data from db

To execute the example you should have a database to connect with and schema 'cayenne' in it. 
MySQL is used for the example.

Firstly, specify connection settings (url, driver, etc.) in *config.yml* to be used by Bootique, having left Cayenne project without a data node. 
Rely on Bootique to get connection to your db. Mixing declarations of data sources in Bootique and Cayenne is allowed but not recommended. 
 
Look though the configs: 

**config.yml**
    
    jdbc:
      mysql:
        url: jdbc:mysql://localhost:3306/cayenne?connectTimeout=0&autoReconnect=true
        driverClassName: com.mysql.jdbc.Driver
        initialSize: 1
        username: root
        password:
    
    cayenne:
      datasource: mysql
      createSchema: true
 
To escape MySQL database staff overwrite *config.yml* for Derby db:

    jdbc:
      derby:
        url: jdbc:derby:target/demodb;create=true
        driverClassName: org.apache.derby.jdbc.EmbeddedDriver
        initialSize: 1
    
    cayenne:
      datasource: derby
      createSchema: true

**cayenne-myproject.xml**

    <?xml version="1.0" encoding="utf-8"?>
    <domain project-version="9">
        <map name="datamap"/>
    
        <!--No Cayenne data node - config.yml is used!-->
    
    </domain>

**Note:** be cautious with Cayenne project name since non-default Cayenne project name, e.g. 'cayenne-myproject.xml', requires an explicit declaration via CayenneModule.extend(..).
The default one 'cayenne-project.xml' works out of the box.

Insert data into database via *--insert* command:
    
    java  -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar --config=config.yml --insert

Simple selection via *--select* command:

    java  -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar --config=config.yml --select

Output:
 
    ...
        INFO  [2017-05-19 12:36:04,060] main i.b.c.d.SelectDataCommand: Articles count on domain mysite1.example.org: 2
    
To listen on events use Cayenne [Lifecycle Events](https://cayenne.apache.org/docs/4.0/cayenne-guide/lifecycle-events.html). The example provides post persist listener (see logs):
    
    ...
        NEW ARTICLE {<ObjectId:Article, id=3>; committed; [domain=>{<ObjectId:Domain, id=2>}; publishedOn=>Fri May 19 15:33:34 MSK 2017; title=>LinkRest Presentation; body=>Here is how to use LinkRest; tags=>(..)]} 


