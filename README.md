# Spring Security

_Code in these projects is for educational purposes only._

* [1 - Default application: Hello](#1---default-application-hello)
* [2 - Overriding the default UserDetailsService: Users](#2---overriding-the-default-userdetailsservice-and-passwordencoder-users)
* [3 - Alternative way of configuring UserDetailsService and PasswordEncoder: Alternative](#3---alternative-way-of-configuring-userdetailsservice-and-passwordencoder-alternative)
* [4 - Overriding the default AuthenticationProvider implementation: Provider](#4---overriding-the-default-authenticationprovider-implementation-provider)
* [5 - Describing the user, implementing UserDetailsService](#5---describing-the-user-implementing-userdetailsservice-user)
* [6 - Using a JdbcUserDetailsManager for user management: JDBC](#6---using-a-jdbcuserdetailsmanager-for-user-management-jdbc)
* [7 - Custom authentication logic: Auth](#7---custom-authentication-logic-auth)
* [8 - Using the SecurityContext: Context](#8---using-the-securitycontext-context)
* [10 - Implementing authentication with form-based login: Form](#10---implementing-authentication-with-form-based-login-form)
* [11 - A small secured web application: Web](#11---a-small-secured-web-application-web)
* [12 - Restricting access for all endpoints based on user authorities: Authorities](#12---restricting-access-for-all-endpoints-based-on-user-authorities-authorities)
* [13 - Restricting access for all endpoints based on user roles: Roles](#13---restricting-access-for-all-endpoints-based-on-user-roles-roles)
* [14 - Using matcher methods to select endpoint: Matcher](#14---using-matcher-methods-to-select-endpoint-matcher)
* [15 - Selecting requests for authorization using MVC matchers: MvcMatchers](#15---selecting-requests-for-authorization-using-mvc-matchers-mvcmatchers)
* [16 - Selecting requests for authorization using regex matchers: Regex](#16---selecting-requests-for-authorization-using-regex-matchers-regex)
* [17 - Adding a filter before or after an existing one in the chain: Filter Before/After](#17---adding-a-filter-before-or-after-an-existing-one-in-the-chain-filter-beforeafter)

### 1 - Default application: [Hello](ssia0201hello)
It generates a new random password and prints it in the console. 
You must use this password to call any application's endpoints with HTTP Basic authentication.  
`curl -u user:2578ab11-8ae7-4009-bff1-623ba6cb705e http://localhost:8080/hello`  
or  
`http --auth user:2578ab11-8ae7-4009-bff1-623ba6cb705e http://localhost:8080/hello`
#### How to use _Authorization_ header
Encode string `user:password` with Base64:  
`echo -n user:2578ab11-8ae7-4009-bff1-623ba6cb705e | base64`  
Use the Base64 encoded value as the value of the _Authorization_ header:  
`curl -H "Authorization: Basic dXNlcjoyNTc4YWIxMS04YWU3LTQwMDktYmZmMS02MjNiYTZjYjcwNWU=" http://localhost:8080/hello`  
or  
`http http://localhost:8080/hello Authorization:"Basic dXNlcjoyNTc4YWIxMS04YWU3LTQwMDktYmZmMS02MjNiYTZjYjcwNWU="`  

[TOC](#spring-security)

### 2 - Overriding the default UserDetailsService and PasswordEncoder: [Users](ssia0202users)
* Create ProjectConfig configuration class.  
* Override the UserDetailsService bean instance.
* Create one user with a set of credentials:
```
UserDetails user = User.withUsername("jane")
        .password("doe")
        .authorities("read")
        .build();
```
* Add the user to the userDetailsService
* Define a bean of the type PasswordEncoder: `NoOpPasswordEncoder.getInstance()`
* Extend the WebSecurityConfigurerAdapter
* Override configure(HttpSecurity) method
* Use HttpSecurity parameter to change configuration:
    * default config: `http.authorizeRequests().anyRequest() .authenticated();`
    * make all endpoints accessible: `http.authorizeRequests().anyRequest().permitAll();`

[TOC](#spring-security)

### 3 - Alternative way of configuring UserDetailsService and PasswordEncoder: [Alternative](ssia0203alternative)
* Add configuration class ProjectConfig extends WebSecurityConfigurerAdapter
* Override configure(AuthenticationManagerBuilder) method
* Override configure(HttpSecurity) method

[TOC](#spring-security)

### 4 - Overriding the default AuthenticationProvider implementation: [Provider](ssia0204provider)
* Add class CustomAuthenticationProvider implements AuthenticationProvider
* Register the new implementation of AuthenticationProvider in ProjectConfig class

[TOC](#spring-security)

### 5 - Describing the user, implementing UserDetailsService: [User](ssia0301user)
* Add simple class User implements UserDetails
* Add simple class InMemoryUserDetailsService implements UserDetailsService
* Register the InMemoryUserDetailsService in ProjectConfig class

[TOC](#spring-security)

### 6 - Using a JdbcUserDetailsManager for user management: [JDBC](ssia0302jdbc)
* Create database `ssia`
* Add `schema.sql` and `data.sql` to project
* Add `spring-boot-starter-jdbc` and `mysql-connector-java` dependencies to pom.xml
* Add properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ssia?serverTimezone=UTC
spring.datasource.username=<your user>
spring.datasource.password=<your password>
spring.datasource.initialization-mode=always
```
* Add ProjectConfig, register JdbcUserDetailsManager
* (Optional) Override queries for the JdbcUserDetailsManager if names of tables and columns are not default

[TOC](#spring-security)

### 7 - Custom authentication logic: [Auth](ssia0501auth)
* Declare a class CustomAuthenticationProvider that implements the AuthenticationProvider contract
* Override the supports(Class<?>) method to specify which type of authentication is supported
* Override the authenticate(Authentication) method to implement the authentication logic
* Register the AuthenticationProvider in the configuration class ProjectConfig

[TOC](#spring-security)

### 8 - Using the SecurityContext: [Context](ssia0502context)
* Obtain the SecurityContext from the SecurityContextHolder
* Obtain the Authentication from a method parameter
* Use default security context strategy SecurityContextHolder.MODE_THREADLOCAL
* Use security context strategy SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
* Use DelegatingSecurityContextCallable to forward the security context to self-managed thread
* Use DelegatingSecurityContextExecutorService to forward the security context to self-managed thread

[TOC](#spring-security)

### 9 - Using and configuring HTTP Basic

[TOC](#spring-security)

### 10 - Implementing authentication with form-based login: [Form](ssia0504form)
* Add class ProjectConfig extends WebSecurityConfigurerAdapter
* Override configure(HttpSecurity http) and call the formLogin() and defaultSuccessUrl() methods of the HttpSecurity parameter
* Add HomeController and home page
* Add class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
* Add class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler
* Register both handlers in ProjectConfig
* Change the configuration to support both the HTTP Basic and the form-based login methods

[TOC](#spring-security)

### 11 - A small secured web application: [Web](ssia0601web)
#### Setup project db
* Create database `ssiaweb`
* Add `schema.sql` and `data.sql`
* Add connection properties and dependency for spring-boot-starter-data-jpa and mysql-connector-java
#### Implement user management
* Define the password encoder objects for the two hashing algorithms (bcrypt and scrypt)
* Define the JPA entities to represent the user and authority tables that store the details needed in the authentication process
* Declare the JpaRepository contract for Spring Data - UserRepository
* Create a decorator that implements the UserDetails contract over the User JPA entity  - CustomUserDetails
* Implement the UserDetailsService contract - JpaUserDetailsService
#### Implement custom authentication logic
* Add class AuthenticationProviderService implements AuthenticationProvider
* Register AuthenticationProvider in ProjectConfig
* Implement the static main page and controller
#### Display records of the product table on the main page
* Add Product entity, repository and service
* Add spring-boot-starter-thymeleaf dependency
* Move main page to templates and display products

[TOC](#spring-security)

### 12 - Restricting access for all endpoints based on user authorities: [Authorities](ssia0701authorities)
* Create ProjectConfig and InMemoryUserDetailsManager bean, create 2 users.
* Create NoOpPasswordEncoder bean.
* Call hasAuthority("authority") or access("expression") methods to restrict access based on authorities.

[TOC](#spring-security)

### 13 - Restricting access for all endpoints based on user roles: [Roles](ssia0703roles)
* Create ProjectConfig and InMemoryUserDetailsManager bean, create 2 users.
* Create NoOpPasswordEncoder bean.
* Call hasRole("role") or access("expression") methods to restrict access based on Roles.

[TOC](#spring-security)

### 14 - Using matcher methods to select endpoint: [Matcher](ssia0801matcher)
* Create ProjectConfig, create InMemoryUserDetailsManager, add two users.
* Use mvcMatchers() method to select endpoint

[TOC](#spring-security)

### 15 - Selecting requests for authorization using MVC matchers: [MvcMatchers](ssia0802mvcmatchers)
* Create ProjectConfig, create InMemoryUserDetailsManager, add two users.
* Use mvcMatchers(HttpMethod, pattern) with _path expressions_ methods to select endpoint.
* Disable CSRF just for this example.
* Add ProductController with path variable `code`.
* Use mvcMatchers(pattern) with parameter _regular expression_ to select endpoint.

[TOC](#spring-security)

### 16 - Selecting requests for authorization using regex matchers: [Regex](ssia0806regex)
* Create ProjectConfig, create InMemoryUserDetailsManager, add two users.
* Use regexMatchers(regex) with _regular expression_ to select endpoints.

[TOC](#spring-security)

### 17 - Adding a filter before or after an existing one in the chain: [Filter Before/After](ssia0901filterbefore)
* Add class RequestValidationFilter implements Filter.
* Call addFilterBefore(javax.servlet.Filter, Class<? extends javax.servlet.Filter>) method 
to configure the custom filter before authentication.
* Add class AuthenticationLoggingFilter implements Filter.
* Call addFilterAfter(javax.servlet.Filter, Class<? extends javax.servlet.Filter>) method 
to configure the custom filter after authentication.

[TOC](#spring-security)
