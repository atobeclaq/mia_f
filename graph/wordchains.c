#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "Graph.h"
#include "stack.h"

#include <string.h>
//#define true 1;
//#define false 0;
#include <stdbool.h>
// the fuction used as abs function make always get the positive result.

int tbs(int a)
{
	return a>0?a:-a;
}
int compare(char*x, char*y)
{
	int size1=0;
	int size2=0;
	size1=strlen(x);
	size2=strlen(y);
	int i=0;
	int j=0;
	int default_num=0;
	int right_num=0;
	// take the pro's advice,just use one'break', pls overlook it ....
	if (size1==size2)
	{
		while (i<size1 && j<size2)
		{
			if (x[i]==y[j])
			{
				right_num++;
				i++;
				j++;
			}
			else if (x[i]!=y[j])
			{
				default_num++;
				if ((i+1)<size1)
				{
					if (x[i+1]!=y[j])
					{
						i++;
						j++;
					}
					else
						return 0;
				}
				else
					break;
			}
		}
	}
	else if(size1 > size2)
	{
		while (i<size1 && j<size2)
		{
			if (x[i]==y[j])
			{
				right_num++;
				i++;
				j++;
			}
			else if (x[i]!=y[j])
			{
				default_num++;
				if ((i+1)<size1)
				{
					if (x[i+1]==y[j])
					{
						i++;
					}
					else
						return 0;
				}
				//else
				//	break;
			}
		}
	}
	else if(size1 < size2)
	{
		while (i<size1 && j<size2)
		{
			if (x[i]==y[j])
			{
				right_num++;
				i++;
				j++;
			}
			else if (x[i]!=y[j])
			{
				default_num++;
				if ((j+1)<size2)
				{
					if (x[i]==y[j+1])
					{
						j++;
					}
					else
						return 0;
				}
				
			}
		}
	}
	if (default_num<=1 || (size1>size2?size2:size1)-1==right_num)
	{
		return 1;
	}
	else
		return 0;
}

//try use dfs search
#define MAX_NODES 1000
int visited[MAX_NODES];
int path_length=0;
int paths_length=0;
#define MAX_NODES 1000
bool dfsPathCheck(Graph g, int nV, Vertex v, Vertex dest) {
	Vertex w;
	for (w = 0; w < nV; w++)
		if (adjacent(g, v, w) && visited[w] == -1&&v<w) {
			visited[w] = v;
			if (w == dest)
				return true;
			else if (dfsPathCheck(g, nV, w, dest))
				return true;
		}
	return false;
}

bool findPathDFS(Graph g, int nV, Vertex src, Vertex dest) {
	Vertex v;
	for (v = 0; v < nV; v++)
		visited[v] = -1;
	visited[src] = src;
	return dfsPathCheck(g, nV, src, dest);
}

//main function
int main(int argc, char *argv[])
{
	printf("Enter a number: ");
	int number;
	Edge e;
	scanf("%d",&number);
	Graph g = newGraph(number);
	char color[number][20];
	int i=0;
	int j=0;
	for (i =0; i < number ; i++)
	{
		printf("Enter word: ");
		scanf("%s",color[i]);
	}
	//int weight=1;
	printf("\n");
	for (i=0;i<number; i++)
	{
		
		printf("%s: ",color[i]);
		for ( j=i+1; j< number; j++)
		{
			int gap=0;
			gap=tbs(strlen(color[i])-strlen(color[j]));
			if (gap<=1)
			{
				int num=0;
				num=compare(color[i],color[j]);
				
				if (num==1)
				{
					e.v=i;
					e.w=j;
					//e.weight=weight;
					insertEdge(g,e);
					//	tp[i][j]=1;
					printf("%s ",color[j]);
					//weight++;
				}
			}
		}
		printf("\n");
	}
	//seem useless operation btw good try....
	//struct pair arr[number*number];
	
	//find the max chain
	int max=0;
	int maxer[number][number];
	int flag=0;
	j=1;
	int num=0;
	int src=0;
	for (src=0;src<number-1;src++)
	{
		for ( i=src+1;i<number;i++)
		{
		int length=0;
		stack s=newStack();
		//use function and get the visited array which go from different src to dest
		//change it respectively to find the path between each node.
		if (findPathDFS(g, i+1, src, i)) {
			Vertex v = i;
			while (v != src) {
				//to reverse the array
				StackPush(s,v);
				length++;
				v = visited[v];
			}
			if (max<length)
			{
				// use flag like the 'flag' and 'num' to identifly wheather it has mutiple length or not
				num=0;
				j=1;
				int h;
				for (h=0;h<length;h++)
				{
					flag=0;
					maxer[0][h]=StackPop(s);
				}
				//if it has change the number of the max
				max=length;
				
			}
			else if(max==length)
			{
				// use the flag to identify
				flag=1;
				int e;
				for (e=0;e<length;e++)
				{
					maxer[j][e]=StackPop(s);
				}
				// increase the flag
				j++;
				num++;
				
			}
			
		}
		
	}
	}
	printf("\n");
	printf("Maximum chain length: %d\n",max+1);
	printf("Maximal chains:\n");
	if (flag==0)
	{
		// print the max length which already stored in the arrary.
		printf("%s -> ", color[0]);
		for ( i=0;i<max-1;i++)
		{
			printf("%s -> ",color[maxer[0][i]]);
		}
		printf("%s",color[maxer[0][max-1]]);
		
	}
	else if(flag==1)
	{
		int k;
		for( k=0;k<num+1;k++){
			printf("%s -> ", color[0]);
			for( i=0;i<max-1;i++)
			{
				printf("%s -> ",color[maxer[k][i]]);
			}
			printf("%s",color[maxer[k][max-1]]);
			printf("\n");
			
		}
	}
	
	return true;
}
