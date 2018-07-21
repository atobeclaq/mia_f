from simulation import tracesimulation
from simulation import randomsimulation
num_of_tests = open("num_tests.txt")
num_of_tests = num_of_tests.read()
for number in range(1,int(num_of_tests)+1):
    ##### this is the place to get the txt file(parameter and model)
    para = "para_"+str(number)+".txt"
    model = "mode_"+str(number)+".txt"
    ##### split the information we got distribute to right parameter
    parameter = open(para)
    parameter = parameter.read().strip().split("\n")
    m = int(parameter[0])
    set_up_time = float(parameter[1])
    delayedoff_time = float(parameter[2])
    model = open(model).read()
    #### cause the para of trace and random are different.
    if len(parameter)>3:
        time_end = float(parameter[3])
    else:
        time_end = 200
    lamda = 0.35
    seed = 3 #### this could be changed
    miu = 1
    ############################initilise######################################
    
    server = [(i,'off',-1) for i in range(m)]
###### if model is random no need to got the arrive and service
    if model == "trace":
        input_file = "arrival_"+str(number)+".txt"
        input_file1 = "service_"+str(number)+".txt"
        f = open(input_file)
        f1 = open(input_file1)
        line = f.read()
        line1 = f1.read()
        line = line.split('\n')
        line1 = line1.split('\n')
 
    arrive = []
    service = []
### same reason as before
    if model == 'trace':
        for i in range(len(line)):
            if line[i] !='':
                arrive.append(line[i])
        for i in range(len(line1)):
            if line1[i] !='':
                service.append(line1[i])
    #server state
    if model == "trace":
        a = tracesimulation(model,arrive,service,m,set_up_time,delayedoff_time,time_end,server)
        start, finish = a.mainn()
    elif model == "random":
        b = randomsimulation(model,m,set_up_time,delayedoff_time,time_end,server,seed)
        start, finish = b.mainn()
    
 ##### generate the output part ######        
    departure = "departure_"+str(number)+".txt"
    mrt = "mrt_" + str(number)+".txt"
    for i in range(len(start)):
        start[i] = "%.3f" % (start[i])
        finish[i] = "%.3f" % (finish[i])
    f = open(departure,'w+')
    f1 = open(mrt,'w+')
    summ = 0
    for i in range(len(start)):
        gap = float(finish[i]) -float(start[i])
        x = start[i]+" "+finish[i]
        f.write(x)
        f.write('\n')
        summ+=gap
    gap = 0

    abg ="%.3f" % ( summ/len(start))
 

    f1.write(abg)

    f.close()
    f1.close()
