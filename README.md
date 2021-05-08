# Spring Security

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

### 2 - Overriding the default UserDetailsService: [Users](ssia0202users)
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
