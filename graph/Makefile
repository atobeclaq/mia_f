wordchains: wordchains.o Graph.o stack.o queue.o
	gcc -o wordchains wordchains.o Graph.o stack.o queue.o

wordchains.o : wordchains.c Graph.h stack.h queue.h
	gcc -Wall -Werror -c wordchains.c

Graph.o : Graph.c Graph.h
	gcc -Wall -Werror -c Graph.c

stack.o : stack.c stack.h
	gcc -Wall -Werror -c stack.c

queue.o : queue.c queue.h
	gcc -Wall -Werror -c queue.c

clean:
	rm -f main wordchains.o Graph.o stack.o queue.o  core

