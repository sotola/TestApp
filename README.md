# TestApp
Spring Boot Test App

## Build
`$ ./gradlew build`

## Run
`$ ./gradlew bootRun`

Or

`$ java -jar build/libs/testapp-0.0.1-SNAPSHOT.jar`

## Notes
* The requirements mention a CV File (on the CV file page). I added very basic file upload support on the cv branch.
* I'm currently using the "Date" input type for the date field, but only Opera/Chrome (and maybe Firefox) support it as a View Finder. It shows up as a text fields for Safari/Internet Explorer. If desired, I can create a custom cross-platform date-picker.
* The specified requirements asked for a Java 9 Spring Boot runnable jar file. However, Java 9's end of life was [March 2018](http://www.oracle.com/technetwork/java/javase/eol-135779.html). I initially setup the project using Java 10 (since https://start.spring.io only had Java 8 or Java 10 options); however, I believe I've changed project's configuration files to build with the Java-9 JDK (and I've verified that I can build and run the app using the Java 9 JDK). 
