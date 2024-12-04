# service-shuffle
- That micro-service has API to return a shuffled array from 1 to N without duplicates of numbers.  The N is  a number from 1 to 1000.  The performance is important. Needs to achieve O(N).
- The service-shffle should send the async request to “service-log” before the requested numbers will be responded to client.
- The URL of 'service-log' should come from a configuration.
