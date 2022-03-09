#include <Stepper.h>
Stepper motor(2048,8,10,9,11);
int test = 1;
void setup() {
  Serial.begin(9600);
  motor.setSpeed(6);
  
}

void loop() {
  
   if(Serial.available())
  {
    switch(Serial.read())
    {
      case '0':
      test = 0;
      break;
      case '1':
      test = 1;
      break;
       }
  }
  if(test==1)motor.step(1);
  if(test==0)motor.step(0);
  
  
}
