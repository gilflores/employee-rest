## Execute the application with Maven

In one terminal with the JAVA_HOME variable execute: 

```bash
mvn clean package
```

Check: [Java Configuration](#java-configuration)

### If the variables were not configured use:


**Windows**

```shell
SET MVN_HOME="C:\toos\Java\apache\apache-maven-3.8.3"
SET JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.5.8-hotspot"
SET PATH=%PATH%;%JAVA_HOME%\bin;%MVN_HOME%\bin
mvn clean package
```


## Initialize the application using docker

Execute the docker-compose command using the following syntax:

```bash
docker-compose up
```

Start only database service

```bash
docker-compose up service-database
```


## Project's configuration

This project is using GitHub action to describe CI/CD practices using Actions. This configuration is in:

- [.github/linters/sun_checks.xml](.github/linters/sun_checks.xml): CheckStyle to validate the project
- [.github/workflows/maven.yml](.github/workflows/maven.yml): In this file you can find the process to execute the lint in the project and its test. This will help to validate the Continues Integration. 


## Lab requirements

### Required

- [Java Jdk 17](https://adoptium.net/temurin/releases/)
- [Maven](https://maven.apache.org/download.cgi)

### Optional software

- [Docker](https://docs.docker.com/get-docker/)
- [Docker compose](https://docs.docker.com/compose/install/)

### IDE's

- [IntelliJ Idea Community](https://www.jetbrains.com/idea/download/) **Used in the Lab**
- [Eclipse Java and Web Developers](https://www.eclipse.org/downloads/packages/)
- [Visual Studio Code](https://code.visualstudio.com/)

### Databases

- [PostgreSQL](https://www.postgresql.org/download/)

### Usefull links

- [Spring Initialzr](https://start.spring.io/)

## Java Configuration

- Install Java 17 in the selected path example: `C:\Program Files\Eclipse Adoptium\jdk-17.0.5.8-hotspot`
- And the environment variable `JAVA_HOME` pointing the installation path.
- Modify the env variable `PATH` to include `%JAVA_HOME%\bin` in Windows, `$JAVA_HOME\bin` in Linux
- Decompress the maven distribution like:  `apache-maven-3.8.6-bin.zip` in folder `C:\tools\java\` if you are using Windows
- And the environment variable `MVN_HOME` pointing to the Maven installation path.
- Modify the env variable `PATH` to include `%MVN_HOME%\bin` in Windows, `$MVN_HOME\bin` in Linux


### Windows

Add environment variables using Windows Command prompt
```shell
SET MVN_HOME="C:\toos\Java\apache\apache-maven-3.8.3"
SET JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.5.8-hotspot"
SET PATH=%PATH%;%JAVA_HOME%\bin;%MVN_HOME%\bin
```

### Linux

Add environment variables using shell

```shell
export MVN_HOME=/home/dev/toos/java/apache-maven-3.8.3
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$PATH;$JAVA_HOME\bin;$MVN_HOME\bin
```

You can see the variables in Windows like:


![Java Environment Variables Config](./docs/images/java-config-environment-variables.png)

