# tangram-notification-listener

## About

**tangram-notification-listener** is a console application that listen TANGRAM notification requests 
and auto send a OK response. The application prints both the request and the response.

The purpose of this application is to test the TANGRAM flows quickly. 
The application outputs raw request and response datas and 
allows partners test the asynchronous behavior of TANGRAM.

tangram-notification-listener is written in Java. 
It uses a Jetty Server <sup id="JettyPos">[[1]](#Jetty)</sup> to listen 8181 port,
receive the request and send a OK response.
All requests and responses are written both to console and to a file. 
The log file is placed inside a directory called log in the same directory of the application path.

## Compiling

The sources includes the a Maven project <sup id="MavenPos">[[2]](#Maven)</sup>. 
You can compile the application with the following command:

```
mvn package
```

You can find the binaries <sup id="TNLPos">[[3]](#TNL)</sup> of tangram-notification-listener here:

> [tangram-notification-listener-bin](https://github.com/takenet/tangram-notification-listener-bin)

## Usage 
For Windows users, just double click on the .exe file, or use the following command to execute the jar:

```
java -jar tangram-notification-listener.jar
```

The application will start and wait for notification requests. 
Note that the application needs permission the write log files at its own folder. 
The application will create a subfolder called *log*.

# References
<a name="Jetty">1</a>. [Jetty Server](http://www.eclipse.org/jetty/). [↩](#JettyPos)

<a name="Maven">2</a>. [Apache Maven](https://maven.apache.org/). [↩](#MavenPos)

<a name="TNL">3</a>. [TANGRAM notification listener binaries](https://github.com/takenet/tangram-notification-listener-bin). [↩](#TNLPos)
