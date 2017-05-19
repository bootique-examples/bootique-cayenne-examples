# bootique-cayenne-demo

Run:

    java -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar

Options:

          -c yaml_location, --config=yaml_location
               Specifies YAML config location, which can be a file path or a URL.
    
          -h, --help
               Prints this message.
    
          -H, --help-config
               Prints information about application modules and their configuration options.
    
          -i, --insert
               Insert initial data into Derby db
    
          -s, --select
               Select data from Derby db
               
Init db:
    
    java  -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar --config=run.yml --insert

Simple select:

    java  -jar target/bootique.cayenne.demo-1.0-SNAPSHOT.jar --config=run.yml --select

Output:
 
    INFO  [2017-05-19 12:36:04,060] main i.b.c.d.SelectDataCommand: Articles count on domain mysite1.example.org: 2
    
Listen on post persist event log: 
    
    NEW ARTICLE {<ObjectId:Article, id=3>; committed; [domain=>{<ObjectId:Domain, id=2>}; publishedOn=>Fri May 19 15:33:34 MSK 2017; title=>LinkRest Presentation; body=>Here is how to use LinkRest; tags=>(..)]} 


   
   
   