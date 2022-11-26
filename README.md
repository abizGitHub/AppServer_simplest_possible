## Simplest Possible Application Server (using Java-Core)
   
This project tries to provide some REST endpoints using java.net.Socket without any other liberaries
methods like POST,GET and DELETE has been implemented 
GET method uses path-variable as controler's method param

###How to Use:
- implement your controller in package com.abiz.controller
- Use custom annotation @HttpMapping and methods Like HttpMethod.POST ,HttpMethod.GET and HttpMethod.DELETE

###Example for calling end-points inside SampleController :

http 127.0.0.1:8080/YourControllerName/MethodName

- echo '-body-' | http :8080/SampleController/addPerson
- http :8080/SampleController/allPerson
- http :8080/SampleController/getPerson/path-param

