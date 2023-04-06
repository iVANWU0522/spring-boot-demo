# Spring Properties Demo

`@Value` and `@ConfigurationProperties` are two different ways to inject external configuration values into a Spring application.

`@Value` is used to inject a single configuration property value into a field or method parameter. The value of the property 
is retrieved by specifying the property key as an argument to the `@Value` annotation. For example, `@Value("${my.property}")` 
would inject the value of the `my.property` property into the annotated field or parameter.

On the other hand, `@ConfigurationProperties` is used to inject a group of related configuration properties into a Java object. 
The properties are mapped to the fields of the Java object based on their names. 
The prefix for the properties to be mapped is specified using the prefix attribute of the `@ConfigurationProperties` annotation. 
For example, `@ConfigurationProperties(prefix = "my")` would map all properties starting with `my.` to the fields of the annotated Java object.

The main difference between these two approaches is that `@Value` is typically used for simple configuration scenarios 
where only a few values need to be injected, while `@ConfigurationProperties` is more suited for more complex scenarios 
where multiple related properties need to be injected into an object.

In summary, if you need to inject only a few configuration properties, you can use `@Value`. If you have a larger set of 
related properties, it's usually better to use `@ConfigurationProperties`.