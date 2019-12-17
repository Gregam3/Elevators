# Strategies

Throughput is calculated as passengers offboarded/timesteps after 5000 timesteps

Simulation hyperparameter remained untouched throughout this process and elevator capacity did not exist in this phase.

### Deliver to closest floor
This strategy will always deliver the passenger closest to their floor,
regardless of when they entered the elevator

######Throughput = 0.33944413

## Funny but horrible strategies

### Deliver to furthest floor
This strategy will always deliver the passenger furthest to their floor,
regardless of when they entered the elevator. 

Gets stuck with too many passengers

######Throughput = 0.1144

### Deliver to random floor
This strategy delivers passengers to a random floor, this random value is updated
whenever a passenger boards

Gets stuck with too many passengers

######Throughput = 0.2694

### Deliver to average floor
This strategy will always deliver to the mean of the passengers desired floors

Gets stuck with too many passengers

######Throughput = 0.0148

### Seesaw


