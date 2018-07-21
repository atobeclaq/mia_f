// Student record implementation ... Assignment 1 COMP9024 17s2
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LINE_LENGTH 1024

// scan input for a positive integer, returns -1 if none
int readInt(void) {
   char line[LINE_LENGTH];
   int  n;

   fgets(line, LINE_LENGTH, stdin);
   if ( (sscanf(line, "%d", &n) != 1) || n <= 0 )
      return -1;
   else
      return n;
}

// scan input for a positive floating point number, returns -1 if none
float readFloat(void) {
   char  line[LINE_LENGTH];
   float f;

   fgets(line, LINE_LENGTH, stdin);
   if ( (sscanf(line, "%f", &f) != 1) || f <= 0.0 )
      return -1;
   else
      return f;
}

/*** Your code for stage 1 starts here ***/
//to check it is valid id or not by dividing it to seven times.
int readValidID(void) {
  
  int x;
  int y;
  x=readInt();
  y=x;
  int i=0;
//using while to divide x.
  while (x!=0){
    x/=10;
    i++;
  }
  if (i==7)
    return y;
  else
    return -1;
 
 
}
//simple check
int readValidCredits(void) {
  int credits;
  credits=readInt();
  if (credits<=480 && credits>=2)
    return credits;
  else
    return -1;
}
//simple check
float readValidWAM(void) {
  float wam;
  wam=readFloat();
  if (wam>=50 && wam<=100)
    return wam;
  else
    return -1;   /* needs to be replaced */
}
//simple print
void printStudentData(int zID, int credits, float WAM) {
 
  printf("Student zID: z%d\n",zID);
  printf("Credits: %d\n",credits);

  if (WAM>=85 && WAM<=100)
    printf("Level of performance: HD\n");
  if (WAM>=75 && WAM<=84.9)
    printf("Level of performance: DN\n");
  if (WAM>=65 && WAM<=74.9)
    printf("Level of performance: CR\n");
  if (WAM>=50 && WAM<=64.9)
    printf("Level of performance: PS\n");
 
    

}
