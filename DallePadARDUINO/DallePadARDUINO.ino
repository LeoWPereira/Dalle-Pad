/*
* DallePadARDUINO
* Dalle Pad - O Gadget que te transforma em um DJ
* @author Leonardo Winter Pereira <leonardowinterpereira@gmail.com>
* @author Luis Felipe Mazzuchetti Ortiz <>
* @author Lucas Zimmermann Cordeiro <>
* Created on 21.04.2016 by Leonardo Winter Pereira
* Copyright Dalle Pad. All Rights Reserved (R)
*/

#pragma region Constants

const int analogInputs = 10; // define how many pots are active up to number of available analog inputs
const int digitalInputs = 32;

#pragma endregion

#pragma region Pre-defined Header

void MIDImessageToPC(int i, int i1, int i2);
void MIDImessageToMobile(int i);
void Debug(bool botao, int command, int data1, int data2);

#pragma endregion

int inputAnalog[analogInputs]; // make arrays for input values and lagged input values

int iAlag[analogInputs];

int ccValue[analogInputs]; // make array of cc values

// cc values for buttons
int cc_off = 0;
int cc_on = 65;
int cc_super = 127;

// map buttons to cc for button
int cc[digitalInputs] = {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53}; 

int lastRead[digitalInputs] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
int newRead[digitalInputs] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
int buttonState[digitalInputs];

/*
Bounce button0 = Bounce(0, 3);
Bounce button1 = Bounce(1, 3);
Bounce button2 = Bounce(2, 3);
Bounce button3 = Bounce(3, 3);
*/

///
/// The main Setup function. Set everything here
///
void setup()
{
	Serial2.begin(31250); // MIDI rate
	Serial1.begin(9600); // Bluetooth rate

	for (int i = 0; i < digitalInputs; i++)
		pinMode(i + 22, INPUT_PULLUP);

	for (int i = 0; i < analogInputs; i++)
	{
		inputAnalog[i] = 0;
		iAlag[i] = 0;
	}
}

///
/// The main loop function. It is here the Arduino looks for commands
///
void loop()
{
	// loop trough active inputs for knobs
	for (int i = 0; i < analogInputs; i++)
	{ 
		inputAnalog[i] = analogRead(i); // read current value at i-th input
		
		if ( (inputAnalog[i] / 8) != (iAlag[i] / 8) ) // if there is difference
		{
			ccValue[i] = inputAnalog[i] / 8; // calc the CC value based on the raw value
			// send the MIDI
			MIDImessageToPC(176, i, ccValue[i]); // usbMIDI.sendControlChange(i, ccValue[i], 3);

      //Debug(false, 176, i, ccValue[i]);
      
			// set raw reading to lagged array for next comparison
		}

		iAlag[i] = inputAnalog[i];
	}

  // loop trough buttons
  for(int i=0; i< digitalInputs; i++){
   buttonState[i] = digitalRead(i + 22);
  
   if (buttonState[i] == LOW)
     {
        newRead[i] = 1;
        if(lastRead[i] != newRead[i]){
        MIDImessageToPC(176, cc[i], cc_on); // usbMIDI.sendControlChange(i, ccValue[i], 3);
        MIDImessageToMobile(true, i);
        //Debug(true, 176, i + 22, cc_on);
        lastRead[i] = 1;
        }
        // set raw reading to lagged array for next comparison
      }
   else{
      newRead[i] = 0;
      if(newRead[i] != lastRead[i]){
          MIDImessageToPC(176, cc[i], cc_off); // usbMIDI.sendControlChange(i, ccValue[i], 3);
          MIDImessageToMobile(false, i);
          //Debug(true, 176, i + 22, cc_off);
          lastRead[i] = 0;
        }
    }
   }

	delay(5); // limits MIDI messages to reasonable number
}

void MIDImessageToPC(int command, int data1, int data2) //pass values out through standard Midi Command
{
  Serial2.write(command);
	Serial2.write(data1);
	Serial2.write(data2);
}

void MIDImessageToMobile(bool botao,int data2)
{
  if(botao == true){
    Serial1.print("begin:");
    Serial1.println(data2);
  }
  else{
  Serial1.print("stop:");
  Serial1.println(data2);
  }
}

void Debug(bool botao, int command, int data1, int data2)
{
  if(botao == true){
    Serial1.print("Botao: ");
    Serial1.println(data1);

    Serial1.print("Valor: ");
    Serial1.println(data2);
  }
  else{
  Serial1.print("Potenciometro: ");
  Serial1.println(data1);

  Serial1.print("Valor: ");
  Serial1.println(data2);
  }
}

