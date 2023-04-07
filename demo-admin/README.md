# Spring Boot Admin
Spring Boot Admin is a web application, used for managing and monitoring Spring Boot applications. Each application is considered as a client and registers to the admin server. Behind the scenes, the magic is given by the Spring Boot Actuator endpoints.

# `<packaging>` element in `pom.xml`
- `<packaging>pom</packaging>` indicates that the Maven project is intended to be a parent project or an aggregator project that simply groups other projects together, but doesn't create an artifact of its own.
- On the other hand, `<packaging>jar</packaging>` specifies that the Maven project is intended to create a JAR (Java ARchive) file when built. A JAR file is essentially a ZIP file that contains Java classes, resources, and metadata, which can be used as a library or a standalone executable.

So, `<packaging>pom</packaging>` is typically used for parent or aggregator projects, while `<packaging>jar</packaging>` is used for projects that create JAR files.