int pin8 = 8;
int pin7= 7;
void setup() {
  Serial.begin(9600);
  pinMode(pin7, OUTPUT);
  pinMode(pin8,OUTPUT);
}

void loop() {
  if(Serial.available())
  {
    switch(Serial.read())
    {
      case '0':
      digitalWrite(pin8,LOW);
      digitalWrite(pin7,LOW);
      break;
      case '1':
      digitalWrite(pin8,HIGH);
      digitalWrite(pin7,LOW);
      break;
      default: break;
    }
  }
}
