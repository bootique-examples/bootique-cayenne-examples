[![verify](https://github.com/bootique-examples/bootique-cayenne-examples/actions/workflows/verify.yml/badge.svg)](https://github.com/bootique-examples/bootique-cayenne-examples/actions/workflows/verify.yml)

# Bootique 3.x Apache Cayenne Examples

This is an example [Bootique](http://bootique.io) Cayenne app.

Different Git branches contain example code for different versions of Bootique:

* [3.x](https://github.com/bootique-examples/bootique-cayenne-examples/tree/3.x)
* [2.x](https://github.com/bootique-examples/bootique-cayenne-examples/tree/2.x)
* [1.x](https://github.com/bootique-examples/bootique-cayenne-examples/tree/1.x)


## Prerequisites

To build and run the project, ensure you have the following installed on your machine:

* Docker
* Java 11 or newer
* Maven

and then follow these steps:

# Checkout
```
git clone git@github.com:bootique-examples/bootique-cayenne-examples.git
cd bootique-cayenne-examples
```

## Start Postgres DB Locally

This starts a Postgres instance listening on port 15433, with login credentials of `postgres` / `test`, and
creates a simple test schema:

```bash
docker-compose -f docker-compose.yml up -d
```

## Build, test and package

Run the following command to build the code, run the tests and package the app:
```
mvn clean package
```

## Run

The following command prints a help message with supported options:
```bash  
java -jar target/bootique-cayenne-examples-3.0.jar
```

```  
NAME
      bootique-cayenne-examples-3.0.jar

OPTIONS
      --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      --count
           Checks row count in articles table for default domain

      -h, --help
           Prints this message.

      -H [prefix], --help-config[=prefix]
           Prints information about application modules and their configuration options. 
           Optionally, you can provide a 'prefix' argument to print only the specified 
           config.

      -i, --insert
           Insert test data the DB
```

Notice how there's no short version of either `--count` or `--config` flags, as both start with the same letter `c`, 
and the full name should be used to avoid ambiguity.

Run the `-i` (or `--insert`) command to create some sample data in the DB. DB location and login credential are
specified in the provided `config.yml`.
```bash
java -jar target/bootique-cayenne-examples-3.0.jar --config config.yml -i
```

Run the `--count` command to display the data inserted in the previous step (

```bash    
java -jar target/bootique-cayenne-examples-3.0.jar --config config.yml --count
```