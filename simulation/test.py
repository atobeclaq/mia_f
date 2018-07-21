from math import log
import matplotlib as mpl
import matplotlib.pyplot as plt
import random
from numpy.random import randn 
lamda =0
y = []
x= []
lam  = [ i/100 for i in range(4,100)]

for lamda in lam:
    tp =random.random()
    
    temp = -log(1-tp)/lamda
    
    x.append(temp)

data = randn(75) 
plt.hist(x,bins=50)
plt.show()
