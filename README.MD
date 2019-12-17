# Strategies

Throughput is calculated as passengers offboarded/timesteps after 5000 timesteps

Simulation hyperparameter remained untouched throughout this process

### Deliver to closest floor
This strategy will always deliver the passenger closest to their floor,
regardless of when they entered the elevator

######Throughput = 0.33944413

Throughput is calculated as passengers offboarded/timesteps after 5000 timesteps
### Deliver to closest floor
This strategy will always deliver the passenger furthest to their floor,
regardless of when they entered the elevator. Surprisingly it only performs a little 
worse than the closest strategy

######Throughput = 0.3292728
