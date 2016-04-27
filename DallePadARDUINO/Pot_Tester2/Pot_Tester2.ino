int val = 0; //Our initial pot values. We need one for the first value and a second to test if there has been a change made. This needs to be done for all 3 pots.
int lastVal = 0;
int val2 = 0;
int lastVal2 = 0;
int val3 = 0;
int lastVal3 = 0;

void setup()
{
   Serial2.begin(31250);       // Set the speed of the midi port to the same as we will be using in the Hairless Midi software  
   Serial1.begin(9600);
}

void loop()
{
   val = analogRead(0)/8;   // Divide by 8 to get range of 0-127 for midi
   if (val != lastVal) // If the value does not = the last value the following command is made. This is because the pot has been turned. Otherwise the pot remains the same and no midi message is output.
   {
      MIDImessage(176,1,val);
   }         // 176 = CC command (channel 1 control change), 1 = Which Control, val = value read from Potentionmeter 1 NOTE THIS SAYS VAL not VA1 (lowercase of course)
   
   lastVal = val;

   delay(10); //here we add a short delay to help prevent slight fluctuations, knocks on the pots etc. Adding this helped to prevent my pots from jumpin up or down a value when slightly touched or knocked.
}

void MIDImessage(byte command, byte data1, byte data2) //pass values out through standard Midi Command
{
   Serial1.println(command);
   Serial1.println(data1);
   Serial1.println(data2);

   Serial2.write(command);
   Serial2.write(data1);
   Serial2.write(data2);
}
