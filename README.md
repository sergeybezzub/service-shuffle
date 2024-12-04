# service-shuffle
- That micro-service has API to return a shuffled array from 1 to N without duplicates of numbers.  The numbers in array have values from 1 to 1000.  The performance is important. Needs to achieve O(N).
- The service-shffle should send the async request to “service-log” before the requested numbers will be responded to client.
- The URL of 'service-log' should come from a configuration.


# Acceptance test description

- Clone code both microcervices from github (githab links are provided)
- Run both microcervices (from console or from IDE, I tested from IDE)
- Perform POST request (I use POSTMAN) - http://localhost:8080/api/service-shuffle/25
- In postman response we will see 25 shuffled numbers array in range from 1 to 1000
- In console of service-log microservice we can see record like that:
`Record from shuffle-numbers. Requested numbers:25 Recieved numbers:25 Status:SUCCESS`