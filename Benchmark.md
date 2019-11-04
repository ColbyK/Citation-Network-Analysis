# Project Benchmarks
## Time Complexity
Time is computed from start to end of the entire program and printed to the console. Depending on the configurations, time may be drastically affected, especially in the case of a larger depth or common keywords searched.
### Time Examples (Max depth = 3, Keyword = "Middleware")
* 10,000 instances: Reading Time = ~2 seconds, Program Time = ~2 seconds
* 50,000 instances: Reading Time = ~9 seconds, Program Time = ~11 seconds
* 100,000 instances: Reading Time = ~29 seconds, Program Time = ~35 seconds
* 150,000 instances: Reading Time = ~75 seconds, Program Time = ~108 seconds
## Memory Usage
Memory usage is recorded at the end of the program. Likewise with time complexity, memory is greatly affected from the confiurations, especially the number of instances to read, max depth, and the keyword used. For more instances, the keyword should be more complex as the reference tree will exponentially grow potentially crashing the program.
### Memory Examples (Max depth = 3, Keyword = "Middleware")
* 10,000 instances: Memory Used = 314.76 MB
* 50,000 instances: Memory Used = 1003.37 MB
* 100,000 instances: Memory Used = 2171.21 MB
* 150,000 instances: Memory Used = 3304.57 MB
