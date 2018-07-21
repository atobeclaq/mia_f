import random
from math import log
class tracesimulation:
    def __init__(self,model ,arrive,service,m,
                 set_up_time,delayedoff_time,time_end,server):
        self.model = model
        self.arrive = arrive
        self.service = service
        self.m = m
        self.set_up_time = set_up_time
        self.delayedoff_time = delayedoff_time
        self.time_end=time_end
        self.server = server
        self.master_clock = []
        self.start = []
        self.finish = []
        self.dispatcher_queue = []
    
    def handle_arrive(self,a,b):
        count = 0 # count for the server states
        small = 0
        num = 0
        server = self.server
        start = self.start
        finish = self.finish
        dispatcher_queue = self.dispatcher_queue
        master_clock = self.master_clock
        set_up_time = self.set_up_time
        delayedoff_time = self.delayedoff_time
        m = self.m
        for i in range(len(server)):
            if server[i][1] == 'delayoff':
                maxe = server[i][2]
                if small < maxe:
                    small = maxe
                    num = server[i][0]
        if small != 0:
            for i in range(len(server)):
                if server[i][0] == num:
                    for j in range(len(master_clock)):
             
                        if float(master_clock[j]) == float(server[i][2]):
                            
                            master_clock.pop(j)
                            
                            break
                    server[i] = (server[i][0],  'busy', round(float(a)+float(b) ,3))### changing
                    master_clock.append(server[i][2])
     
                    finish.append(server[i][2])
                    start.append(float(a))                
            master_clock.sort()
     
            return 'success'
        for i in range(len(server)):
            if server[i][1] == 'off':
                job = (float(a), float(b), 'Marked')
                server[i] = (server[i][0],'setup', round(float(a) + set_up_time,3))
                dispatcher_queue.append(job)
                master_clock.append(server[i][2])
                master_clock.sort()
                
     
                return job
        for i in range(len(server)):
            if server[i][1] == 'off' or server[i][1] == 'setup' or server[i][1] == 'busy':
                count += 1
        if count == m:
            dispatcher_queue.append((float(a),float(b),'Unmarked'))
            master_clock.sort()
     
            return 'fail'

    def handling_departure(self,server1,timer):
    ##### refer to the server that has just completed processing of a job
        server = self.server
        start = self.start
        finish = self.finish
        m = self.m
        dispatcher_queue = self.dispatcher_queue
        master_clock = self.master_clock
        set_up_time = self.set_up_time
        delayedoff_time = self.delayedoff_time
        a = server1
        if a[2] == 'Marked':
           
            for i in range(len(server)):
                if server[i][2] == timer:
                    server[i] = (server[i][0],server[i][1],round(server[i][2]+a[1],3))
                    finish.append(server[i][2])
                    start.append(a[0])
                    master_clock.append(server[i][2])
                    master_clock.sort()              
                    flag = 0              
                    if dispatcher_queue!=[] and server[i][1] == 'busy':### need to add mark at the
                        for i in range(len(dispatcher_queue)):
                            if dispatcher_queue[i][2] == 'Unmarked':
                                dispatcher_queue[i] = (dispatcher_queue[i][0],dispatcher_queue[i][1],'Marked')
                                flag =1
                                return 'flase'
                        if flag == 0 or dispatcher_queue:
                            small = 0
                            nu = 0        
                            for i in range(len(server)):
                                if server[i][1] == 'setup':
                                    time = server[i][2]
                                    if time > small:
                                        small = time
                                        nu = server[i][0]                                                  
                            for i in range(len(server)):

                                if server[i][0] == nu:
                                    tim = server[i][2]
                                    for j in range(len(master_clock)):
                                        if master_clock[j] == tim:
                                            master_clock.pop(j)
                                            break
                                    server[i] = (nu,'off',-1)
                                    
                                   
                                    break
                    if dispatcher_queue == [] and server[i][1] == 'busy':
                            small = 0
      
                            nu = 0
      
             
                            for i in range(len(server)):

                                if server[i][1] == 'setup':
                                    time = server[i][2]
                                    if time > small:
                                        small = time
                                        nu = server[i][0]
                            
                                
                            for i in range(len(server)):

                                if server[i][0] == nu:
                                    tim = server[i][2]
                                    if len(master_clock) == 1:
                                        master_clock.pop()
                                    else:
                                        for j in range(len(master_clock)):
                                            
                                            if master_clock[j] == tim:
                                                master_clock.pop(j)
                                                break
                                    server[i] = (nu,'off',-1)
                    elif server[i][1] == 'setup' and flag!=1:
                        server[i] = (server[i][0],'busy',round(server[i][2],3))                            
                        
        else:
            #### do as usual
            for i in range(len(server)):
                if server[i][2] == timer:
                    server[i] = (server[i][0],'busy',round(server[i][2]+a[1],3))
                    finish.append(server[i][2])
                    start.append(a[0])
                    master_clock.append(server[i][2])
                    master_clock.sort()
                    break
    def mainn(self):
        server = self.server
        start = self.start
        finish = self.finish
        dispatcher_queue = self.dispatcher_queue
        master_clock = self.master_clock
        arrive = self.arrive
        service = self.service
        delayedoff_time = self.delayedoff_time
        for i in arrive:
            master_clock.append(float(i))
        answer = self.handle_arrive(arrive[0],service[0])
        arrive.pop(0)
        service.pop(0)
        master_clock.pop(0)
       
        while master_clock:
            if arrive!=[] and float(arrive[0]) == float(master_clock[0]):
                answer = self.handle_arrive(arrive[0],service[0])
                master_clock.pop(0)
                arrive.pop(0)
                service.pop(0)
          
            else:
         
                for i in range(len(server)):
         
                    if float(server[i][2]) ==float(master_clock[0]):
                        master_clock.pop(0)

                        if server[i][1] == 'setup' :
                          
                           answer = self.handling_departure(dispatcher_queue.pop(0),server[i][2])
                           
                           break
                        elif dispatcher_queue!=[] and server[i][1] == 'busy':
                            answer = self.handling_departure(dispatcher_queue.pop(0),server[i][2])
     


                            break                            
                        elif server[i][1] == 'delayoff':
                            server[i] = (server[i][0], 'off',-1)
                            break
                        elif dispatcher_queue==[] and server[i][1] == 'busy':
                            server[i] = (server[i][0],'delayoff',round(server[i][2]+ delayedoff_time,3))
                            master_clock.append(server[i][2])
                            master_clock.sort()
                            break
        for k in range(len(finish)-1):
            for p in range(len(finish)-1-k):
                if finish[p] > finish[p+1]:
                    finish[p], finish[p+1] = finish[p+1], finish[p]
                    start[p],start[p+1]= start[p+1],start[p]
        return start,finish
class randomsimulation:
    def __init__(self,model ,m,set_up_time,delayedoff_time,time_end,server,seed):
            self.model = model
            self.arrive = []
            self.service = []
            self.m = m
            self.set_up_time = set_up_time
            self.delayedoff_time = delayedoff_time
            self.time_end=time_end
            self.server = server
            self.master_clock = []
            self.start = []
            self.finish = []
            self.dispatcher_queue = []
            self.lamda = 0.35
            self.miu = 1
            self.seed = seed
    
    def handle_arrive(self,a,b):
        count = 0 # count for the server states
        small = 0
        num = 0
        server = self.server
        start = self.start
        finish = self.finish
        dispatcher_queue = self.dispatcher_queue
        master_clock = self.master_clock
        set_up_time = self.set_up_time
        delayedoff_time = self.delayedoff_time
        m = self.m
        for i in range(len(server)):
            if server[i][1] == 'delayoff':
                maxe = server[i][2]
                if small < maxe:
                    small = maxe
                    num = server[i][0]
        if small != 0:
            for i in range(len(server)):
                if server[i][0] == num:
                    for j in range(len(master_clock)):
             
                        if float(master_clock[j]) == float(server[i][2]):
                            
                            master_clock.pop(j)
                            
                            break
                    server[i] = (server[i][0],  'busy', round(float(a)+float(b) ,3))### changing
                    master_clock.append(server[i][2])
     
                    finish.append(server[i][2])
                    start.append(float(a))                
            master_clock.sort()
     
            return 'success'
        for i in range(len(server)):
            if server[i][1] == 'off':
                job = (float(a), float(b), 'Marked')
                server[i] = (server[i][0],'setup', round(float(a) + set_up_time,3))
                dispatcher_queue.append(job)
                master_clock.append(server[i][2])
                master_clock.sort()
                
     
                return job
        for i in range(len(server)):
            if server[i][1] == 'off' or server[i][1] == 'setup' or server[i][1] == 'busy':
                count += 1
        if count == m:
            dispatcher_queue.append((float(a),float(b),'Unmarked'))
            master_clock.sort()
     
            return 'fail'

    def handling_departure(self,server1,timer):
    ##### refer to the server that has just completed processing of a job
        server = self.server
        start = self.start
        finish = self.finish
        m = self.m
        dispatcher_queue = self.dispatcher_queue
        master_clock = self.master_clock
        set_up_time = self.set_up_time
        delayedoff_time = self.delayedoff_time
        a = server1
        if a[2] == 'Marked':
           
            for i in range(len(server)):
                if server[i][2] == timer:
                    server[i] = (server[i][0],server[i][1],round(server[i][2]+a[1],3))
                    finish.append(server[i][2])
                    start.append(a[0])
                    master_clock.append(server[i][2])
                    master_clock.sort()              
                    flag = 0              
                    if dispatcher_queue!=[] and server[i][1] == 'busy':### need to add mark at the
                        for i in range(len(dispatcher_queue)):
                            if dispatcher_queue[i][2] == 'Unmarked':
                                dispatcher_queue[i] = (dispatcher_queue[i][0],dispatcher_queue[i][1],'Marked')
                                flag =1
                                return 'over'
                        if flag == 0 or dispatcher_queue:
                            small = 0
                            nu = 0        
                            for i in range(len(server)):
                                if server[i][1] == 'setup':
                                    time = server[i][2]
                                    if time > small:
                                        small = time
                                        nu = server[i][0]                                                  
                            for i in range(len(server)):

                                if server[i][0] == nu:
                                    tim = server[i][2]
                                    for j in range(len(master_clock)):
                                        if master_clock[j] == tim:
                                            master_clock.pop(j)
                                            break
                                    server[i] = (nu,'off',-1)
                                    
                                   
                                    break
                    if dispatcher_queue == [] and server[i][1] == 'busy':
                            small = 0
      
                            nu = 0
      
             
                            for i in range(len(server)):

                                if server[i][1] == 'setup':
                                    time = server[i][2]
                                    if time > small:
                                        small = time
                                        nu = server[i][0]
                            
                                
                            for i in range(len(server)):

                                if server[i][0] == nu:
                                    tim = server[i][2]
                                    if len(master_clock) == 1:
                                        master_clock.pop()
                                    else:
                                        for j in range(len(master_clock)):
                                            
                                            if master_clock[j] == tim:
                                                master_clock.pop(j)
                                                break
                                    server[i] = (nu,'off',-1)
                    elif server[i][1] == 'setup' and flag!=1:
                        server[i] = (server[i][0],'busy',round(server[i][2],3))                            
                        
        else:
            #### do as usual
            for i in range(len(server)):
                if server[i][2] == timer:
                    server[i] = (server[i][0],'busy',round(server[i][2]+a[1],3))
                    finish.append(server[i][2])
                    start.append(a[0])
                    master_clock.append(server[i][2])
                    master_clock.sort()
                    break
    def mainn(self):
       
        seed = self.seed
        lamda = self.lamda
        miu = self.miu
        server = self.server
        start = self.start
        finish = self.finish
        time_end = self.time_end
        dispatcher_queue = self.dispatcher_queue
        master_clock = self.master_clock
        delayedoff_time = self.delayedoff_time
        arrive = self.arrive
        service = self.service
        random.seed(seed)
        arrive.append(round(-log(1-random.random())/lamda,3))
        master_clock.append(arrive[0])
        temp = arrive[0]
        service_time = round(-log(1-random.random())/miu-log(1-random.random())/miu-log(1-random.random())/miu,3)
        service.append(service_time)
        while master_clock == [] or master_clock[0] < time_end:
 
            master_clock.sort()
            if arrive!=[] and float(arrive[0]) == float(master_clock[0]):
                answer = self.handle_arrive(arrive[0],service[0])
                master_clock.pop(0)
                arrive.pop(0)
                service.pop(0)
                master_clock.sort()
            elif arrive == []:           
                x = round(-log(1-random.random())/lamda,3)
                x+=temp
                x = round(x,3)
                temp = 0
                arrive.append(x)
                master_clock.append(arrive[0])
                master_clock.sort()
                temp = x
     
                service_time = round(-log(1-random.random())/miu-log(1-random.random())/miu-log(1-random.random())/miu,3)
                service.append(service_time)
            else:        
                for i in range(len(server)):
         
                    if float(server[i][2]) ==float(master_clock[0]):
                        master_clock.pop(0)

                        if server[i][1] == 'setup' :
                          
                           answer = self.handling_departure(dispatcher_queue.pop(0),server[i][2])
                           
                           break
                        elif dispatcher_queue!=[] and server[i][1] == 'busy':
                            answer = self.handling_departure(dispatcher_queue.pop(0),server[i][2])
                            


                            break                            
                        elif server[i][1] == 'delayoff':
                            server[i] = (server[i][0], 'off',-1)
                            break
                        elif dispatcher_queue==[] and server[i][1] == 'busy':
                            server[i] = (server[i][0],'delayoff',round(server[i][2]+ delayedoff_time,3))
                            master_clock.append(server[i][2])
                            master_clock.sort()
                            break
                
        for k in range(len(finish)-1):
            for p in range(len(finish)-1-k):
                if finish[p] > finish[p+1]:
                    finish[p], finish[p+1] = finish[p+1], finish[p]
                    start[p],start[p+1]= start[p+1],start[p]
        if finish[-1] >= time_end:
            finish.pop()
            start.pop()
 
        return start,finish
