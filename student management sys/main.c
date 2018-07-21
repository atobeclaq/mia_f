/**
     main.c

     Program supplied as a starting point for
     Assignment 1: Student record manager

     COMP9024 17s2
**/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include <math.h>
#include "studentRecord.h"
#include "studentLL.h"

void printHelp();
void StudentLinkedListProcessing();
void invalid();
studentRecordT useful();
int main(int argc, char *argv[]) {
  //print in and simple calculate in main funtion
   if (argc == 2) {
     // studentRecordT s1;
     //studentRecordT s2;
     int n=0;
     float wam1=0;
     float wam2=0;
     int cr=0;  
     int  nu=atoi(argv[1]);
     studentRecordT *ptr=malloc(nu*sizeof(studentRecordT));
     for(int i=0;i<nu;i++){
       studentRecordT s1;
       s1 = useful();
       ptr[i]=s1;
       n+=1;
       wam1+=s1.WAM;
       wam2+=s1.WAM*s1.credits;
       cr+=s1.credits;
       
     }
     for (int i=0;i<nu;i++){
       printf("------------------------\n");
       printStudentData(ptr[i].zID,ptr[i].credits,ptr[i].WAM);
       printf("------------------------\n");
     }
     float yy;
     yy=wam1/n;
     yy=floor(yy*1000+0.5);
     yy=yy/1000;
     printf("Average WAM: %.3f\n",yy);
     float xx;
     xx=wam2/cr;
     xx=floor(xx*1000+0.5);
     xx=xx/1000;
     printf("Weighted average WAM: %.3f\n",xx);
 
      /*** Insert your code for stage 1 here ***/
      
   } else {
      StudentLinkedListProcessing();
   }
   return 0;
}
//function only used for the struct type which make it easier
void invalid(){
  printf("Not valid. Enter a valid value: ");

}
// to get the value of struct and return value using function to make it not
//too complex,cause this function could be used mutiple times
studentRecordT useful(void){
  studentRecordT s1;
  printf("Enter student ID: ");
  s1.zID= readValidID();
  // ithink the while could give the loop scanf for the unvalid input
  // but may be  it could be simplify though i can only think about this method.
 
  while( s1.zID==-1){
    invalid();
    s1.zID= readValidID();
  }
  printf("Enter credit points: ");
  s1.credits=readValidCredits();
 
  while(s1.credits==-1){
    invalid();
    s1.credits=readValidCredits();
  }
  printf("Enter WAM: ");
  s1.WAM=readValidWAM();
 
  while( s1.WAM==-1){
    invalid();
    s1.WAM=readValidWAM(); 
  }
  return s1;
}
/* Code for Stages 2 and 3 starts here */

void StudentLinkedListProcessing() {
    int op, ch;

    List list = newLL();   // create a new linked list
 
   
   while (1) {
      printf("Enter command (a,f,g,p,q, h for Help)> ");

      do {
	 ch = getchar();
      } while (!isalpha(ch) && ch != '\n');  // isalpha() defined in ctype.h
      op = ch;
      // skip the rest of the line until newline is encountered
      while (ch != '\n') {
	 ch = getchar();
      }

      switch (op) {

         case 'a':
         case 'A':
	   {
	     studentRecordT s1;
	     s1=useful();
	     insertLL(list,s1.zID,s1.credits,s1.WAM);
	    
	   }

            /*** Insert your code for adding a student record ***/

	    break;

         case 'f':
         case 'F':
	   {
	     int id;
	     printf("Enter student ID: ");
             id= readValidID();
	     while(id==-1){
		invalid();
		id= readValidID();

	     }
	     inLL(list,id);
	     
	     
	   }

            /*** Insert your code for finding a student record ***/

	    break;
	    
         case 'g':
         case 'G': {
	   int a=25;
	   int *n=&a;
	   float b=3.0;
	   float *wam=&b;
	   float *w_wam=&b;
	   getStatLL(list,n,wam,w_wam);
	 }
       
            /*** Insert your code for getting statistical information ***/

	    break;
	    
         case 'h':
         case 'H':
            printHelp();
	    break;
	    
         case 'p':
         case 'P':
	   showLL(list);
	  

            /*** Insert your code for printing all student records ***/

	    break;

	 case 'q':
         case 'Q':
	    dropLL(list);       // destroy linked list before returning
	    printf("Bye.\n");
	    return;
      }
   }
}

void printHelp() {
   printf("\n");
   printf(" A - Add student record\n" );
   printf(" F - Find student record\n" );
   printf(" G - Get statistics\n" );
   printf(" H - Help\n");
   printf(" P - Print all records\n" );
   printf(" Q - Quit\n");
   printf("\n");
}
