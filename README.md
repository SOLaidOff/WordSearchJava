# Instructions
## Prerequisites
Ensure that your machine supports Java 10  
Ensure that your machine supports Maven (https://maven.apache.org/guides/getting-started/)  
Ensure that your machine supports Maven wrapper (may need to run `mvn -N io.takari:maven:0.7.6:wrapper`) (https://github.com/takari/maven-wrapper)

## Building
From command line, go to project root directory  
Build using `mvn clean package`

## Testing
Same as steps for Building
Can run `mvn clean test` instead to run tests without producing output JAR, if desired

## Running
Run using `.\target\word-search-1.0-SNAPSHOT.jar`

# Credit
This project was bootstrapped using the junit5-jupiter-starter-maven project (https://github.com/junit-team/junit5-samples/tree/r5.4.2/junit5-jupiter-starter-maven).
