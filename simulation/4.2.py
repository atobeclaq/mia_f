#####CONSIDER THE TC AND A NEW TC AND COMPAIR THEM WITH MUTILE SAME ARRIVE AND SERVICE
####
import matplotlib as mpl
import matplotlib.pyplot as plt
from simulation import randomsimulation
import math
tc_guess = 30 ######changing 
model = "random"
arrive = []
service = []
m =3
set_up_time = 5
delayoff_time = 0.1### which is tc
time_end = 600
server = []
avg = []
avg1 = []

##print('finis')
gap1 = []
for seed in range(3,33):
    server = [(i,'off',-1) for i in range(m)]
    a = randomsimulation(model,m,set_up_time,delayoff_time,time_end,server,seed)
    start, finish = a.mainn()
    summ = 0
    
    for i in range(len(start)):
        gap = float(finish[i]) -float(start[i])
        summ+=gap
        gap1.append(gap)
    gap = 0
    abg ="%.3f" % ( summ/len(start))
    avg.append(abg)
 
gap2 = []
for seed in range(3,33):
    server = [(i,'off',-1) for i in range(m)]
    b = randomsimulation(model,m,set_up_time,tc_guess,time_end,server,seed)   
    start1, finish1 = b.mainn()
 
    ########## different abg 
    summ = 0
    
    for i in range(len(start1)):
        gap = float(finish1[i]) -float(start1[i])
        summ+=gap
        gap2.append(gap)
    gap = 0
##
    abg1 ="%.3f" % ( summ/len(start1))
    avg1.append(abg1)
n = 121 ## that is the time we replicate
summ1 = 0
summ =0
y = []

for m in range(1,len(gap1)):
    for i in range(0,m):
        summ+= float(gap1[i])
    y.append(summ/m)
    summ = 0
m = 10
x = [i for i in range(1,len(gap1))]
################## the part of the plot
plt.plot(x,y)
plt.show()

summ = 0
y= []
for m in range(1,len(gap2)):
    for i in range(0,m):
        summ+= float(gap2[i])
    y.append(summ/m)
    summ = 0
x = [i for i in range(1,len(gap2))]
plt.plot(x,y)
plt.show()
#### choosing reasonable mrt
mean_mrt = 6.20
mean_mrt1 = 3.40   
cou = 0
###### set m = 10 for transient removal
for i in avg:
    cou += (mean_mrt - float(i))**2
s = math.sqrt(cou/(n-1))
cou = 0
for i in avg1:
    cou += (mean_mrt1 - float(i))**2
s1 = math.sqrt(cou/(n-1))
midea = s/math.sqrt(n)
midea1 = s1/math.sqrt(n)
alpha = 0.05 #### cause we want 95% confidence interval
########### [mean_mrt-t98,0.975*midea]
#### t- distribution n = 120 1.980
min_part = mean_mrt - 2.045*midea
max_part = mean_mrt + 2.045*midea
min_part1 = mean_mrt1 - 2.045*midea1
max_part1 = mean_mrt1 + 2.045*midea1
print("%.3f" %min_part,"%.3f" %max_part)
print("%.3f" %min_part1, "%.3f" %max_part1)
