[![verify](https://github.com/bootique-examples/bootique-cayenne-demo/actions/workflows/verify.yml/badge.svg)](https://github.com/bootique-examples/bootique-cayenne-demo/actions/workflows/verify.yml)
# bootique-cayenne-demo

A simple example that explains how to use [Cayenne ORM](https://cayenne.apache.org) integrated for [Bootique](https://bootique.io).

*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*

You can find different versions of framework in use at
* [1.x](https://github.com/bootique-examples/bootique-cayenne-demo/tree/1.x)
* [2.x](https://github.com/bootique-examples/bootique-cayenne-demo/tree/2.x)
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo
      
Here is how to build it:
        
    $ git clone git@github.com:bootique-examples/bootique-cayenne-demo.git
    $ cd bootique-cayenne-demo
    $ mvn package
      
## Run the Demo

Now you can check the options available in your app:
   
    $ java -jar target/bootique-cayenne-demo-1.0-SNAPSHOT.jar
    
    NAME
          bootique-cayenne-demo-1.0-SNAPSHOT.jar
    
    OPTIONS
          -c yaml_location, --config=yaml_location
               Specifies YAML config location, which can be a file path or a URL.
    
          -r [true | false], --create-schema[=true | false]
               Create schema. False by default. Optional.
    
          -d [derby, mysql], --datasource[=derby, mysql]
               Select datasource to use. By default 'mysql' is used. Optional.
    
          -h, --help
               Prints this message.
    
          -H, --help-config
               Prints information about application modules and their configuration options.
    
          -i, --insert
               Insert initial data into db
    
          -s, --select
               Select data from db

To execute the example you should have a database to connect with. You can use Docker to start MySQL or use embedded Derby data source.

Here is docker command to start MySQL instance that can be used for this example: 
 
    $ docker run --name cayenne-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cayenne -e MYSQL_DATABASE=cayenne -d mysql:8.0
          
This example relies on Bootique to provide data source to Cayenne runtime. 
Mixing of declarations of data sources in Bootique and Cayenne is allowed but not recommended. 

**Note:** be cautious with Cayenne project name since non-default Cayenne project name, e.g. 'cayenne-myproject.xml', requires an explicit declaration via `CayenneModule.extend(..)`.
The default one `cayenne-project.xml` works out of the box.

If you want to use Derby DB instead of MySQL just skip Docker step and add `--datasource=derby` option to the commands bellow.

Insert data into database via *--insert* command:
    
    $ java  -jar target/bootique-cayenne-demo-1.0-SNAPSHOT.jar --insert --create-schema    

Simple selection via *--select* command:

    $ java  -jar target/bootique-cayenne-demo-1.0-SNAPSHOT.jar --select

Output:
 
    ...
    INFO  [2017-05-19 12:36:04,060] main i.b.c.d.SelectDataCommand: Articles count on domain mysite1.example.org: 2
    ...
    
The example provides post persist [listener](https://cayenne.apache.org/docs/4.0/cayenne-guide/lifecycle-events.html) (see logs):
    
    ...
    NEW ARTICLE {<ObjectId:Article, id=3>; committed; [domain=>{<ObjectId:Domain, id=2>}; publishedOn=>Fri May 19 15:33:34 MSK 2017; title=>LinkRest Presentation; body=>Here is how to use LinkRest; tags=>(..)]} 
    ...

