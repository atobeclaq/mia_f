#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "studentLL.h"
#include "studentRecord.h"
#include <math.h>
// linked list node type
// DO NOT CHANGE
typedef struct node {
    studentRecordT data;
    struct node    *next;
} NodeT;

// linked list type
typedef struct ListRep {
  NodeT *head;

/* Add more fields if you wish */

} ListRep;

/*** Your code for stages 2 & 3 starts here ***/

List newLL();

NodeT *makeNode(studentRecordT v);
void insertLL(List listp, int zid, int cr, float wam);
void showLL(List listp);
// Time complexity: o(1)
// Explanation: it only need to do once, therefore the big oh is o(1)
List newLL() {
  ListRep *p=malloc(sizeof(ListRep));
  /*** p->head = (NodeT*)malloc(sizeof(NodeT)); ***/
  /*** p->head->next=NULL; ***/
  p->head=NULL;
  
  return p;
  /* needs to be replaced */
}

// Time complexity: o(n)
// Explanation: it is like the showll, it use the simple loop to free every node
//memory of the list.
void dropLL(List listp) {
  NodeT *p;
  p=listp->head;
  while (listp->head!=NULL){
    listp->head=listp->head->next;
    free(p);
    p=listp->head;

  }
  
   return;  /* needs to be replaced */
}
NodeT *makeNode(studentRecordT v){
  
  NodeT *new;
  new=(NodeT*)malloc(sizeof(NodeT));
  assert(new !=NULL);
  new->data=v;
  new->next=NULL;
  return new;
}
// Time complexity: o(n)
// Explanation: it is like the show which has a loop to run all the list ,but it// more
//time-saving, cause  once it get it want i will break out,otherwise ,show the a//lert
//information  no recorded.
void inLL(List listp, int zid) {
   NodeT *p;
   int count=0;
   for(p=listp->head;p!=NULL;p=p->next){
     if (p->data.zID==zid){
       printf("------------------------\n");
       printf("Student zID: z%d\n",p->data.zID);
       printf("Credits: %d\n",p->data.credits);
       if (p->data.WAM>=85 && p->data.WAM<=100)
	 printf("Level of performance: HD\n");
       if (p->data.WAM>=75 && p->data.WAM<=84.9)
	 printf("Level of performance: DN\n");
       if (p->data.WAM>=65 && p->data.WAM<=74.9)
	 printf("Level of performance: CR\n");
       if (p->data.WAM>=50 && p->data.WAM<=64.9)
	 printf("Level of performance: PS\n");
       printf("------------------------\n");
       count=1;
       break;
     
     }
     
   }
  
   if(count==0)
     {printf("No record found.\n");}
   return ;
     /* needs to be replaced */
}




// Time complexity: o(n^2)
// Explanation: i think because i use many for loop in this fuction,however
//i think the comlexity won't be affected by the loop times, cause i am just
//use the loop to sort,which use for in while ,and end the loop it will return t//o
//the head and start loop again until run to the end of the loop; therefore i th//ink
//it is a o(n^2)
void insertLL(List listp, int zid, int cr, float wam) {
    NodeT *newrecord = malloc(sizeof(NodeT));
    studentRecordT studentdata;
    // set value
    studentdata.zID = zid;
    studentdata.credits = cr;
    studentdata.WAM = wam;
    
    newrecord->data = studentdata;
    newrecord->next = NULL;
    NodeT *pro;
    for(pro=listp->head;pro!=NULL;pro=pro->next){
      if (pro->data.zID==zid){
	pro->data.WAM=wam;
        pro->data.credits=cr;
	printf("Student record updated.\n");
	return;
      }
    }
    // insert
    if (listp->head == NULL) {
        listp->head = newrecord;
        printf("Student record added.\n");
    }
    else if (listp->head->next == NULL){
        NodeT *node = listp->head;
        if (node->data.zID == zid) {
            listp->head = newrecord;
            printf("Student record updated.\n");
        }
        else if (node->data.zID > zid) {
            newrecord->next = node;
            listp->head = newrecord;
            printf("Student record added.\n");
        }
        else {
            node->next = newrecord;
            printf("Student record added.\n");
        }

    }
    else {
      NodeT *node = listp->head;
      NodeT *node1 = listp->head;
      
      while (node->next){
	node=node->next;
      }
      if( node->data.zID<zid){
	node->next=newrecord;
	newrecord->next=NULL;
	printf("Student record added.\n");
      }
      else if(zid<node1->data.zID){
	newrecord->next=node1;
	listp->head=newrecord;
      	printf("Student record added.\n");
      }
      else{
      /* NodeT *node = listp->head; */
      /* newrecord->next=node->next; */
      /* node->next=newrecord; */
        NodeT *p;
      
        for(p=listp->head;p!=NULL;p=p->next){
	  if (zid>p->data.zID&&zid<p->next->data.zID){
	    newrecord->next=p->next;
	    p->next=newrecord;
	  }
	}
	printf("Student record added.\n");
      }
    }
   
    
    /* NodeT *p,*q; */
    /* studentRecordT temp; */
    /* for(p=listp->head;p!=NULL;p=p->next){ */
    /* 	for(q=p->next;q!=NULL;q=q->next) */
    /* 	  { */
    /* 	    	if(p->data.zID>q->data.zID) */
    /* 	       	{ */
    /* 		       	temp=q->data; */
    /* 	       		q->data=p->data; */
    /*    			p->data=temp; */
    /* 		} */
    /* 	   } */
    /* } */

    
}






// Time complexity: o(n)
// Explanation: also use the loop and get all the things i want to calculate the
//number
void getStatLL(List listp, int *n, float *wam, float *w_wam) {
  assert(listp->head);
  NodeT *p;
  int n1,cre;
  float wam1,wam2;
  n1=0;
  wam1=0;
  wam2=0;
  cre=0;
  for(p=listp->head;p!=NULL;p=p->next){
    n1+=1;
    wam1+=p->data.WAM;
    wam2+=(p->data.WAM)*(p->data.credits);
    cre+=p->data.credits;
  }
  wam1=floor((wam1/n1)*1000+0.5);
  wam1=wam1/1000;
  wam2=floor((wam2/cre)*1000+0.5);
  wam2=wam2/1000;
  printf("Number of records: %d\n",n1);
  printf("Average WAM: %.3f\n",wam1);
  printf("Average weighted WAM: %.3f\n",wam2);
  

}

// Time complexity: o(n)
// Explanation: cause i only has one loop for the list and to print all the data// from the
//list therefore  only o(n)
void showLL(List listp) {
  assert(listp->head);
  NodeT *p;
  for(p=listp->head;p!=NULL;p=p->next){
     if (p!=NULL){
       printf("------------------------\n");
       printf("Student zID: z%d\n",p->data.zID);
       printf("Credits: %d\n",p->data.credits);
       if (p->data.WAM>=85 && p->data.WAM<=100)
	 printf("Level of performance: HD\n");
       if (p->data.WAM>=75 && p->data.WAM<=84.9)
	 printf("Level of performance: DN\n");
       if (p->data.WAM>=65 && p->data.WAM<=74.9)
	 printf("Level of performance: CR\n");
       if (p->data.WAM>=50 && p->data.WAM<=64.9)
	 printf("Level of performance: PS\n");
       printf("------------------------\n");
     }

  }
    

    /* needs to be replaced */
}
