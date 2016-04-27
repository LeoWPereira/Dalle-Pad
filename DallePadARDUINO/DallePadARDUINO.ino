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

const int analogInputs = 1; // define how many pots are active up to number of available analog inputs
const int digitalInputs = 0;

#pragma endregion

#pragma region Pre-defined Header

void MIDImessage(int i, int i1, int i2);

#pragma endregion

int inputAnalog[analogInputs]; // make arrays for input values and lagged input values

int iAlag[analogInputs];

int ccValue[analogInputs]; // make array of cc values

// cc values for buttons
int cc_off = 0;
int cc_on = 65;
int cc_super = 127;

// map buttons to cc for button
int cc0 = 51;
int cc1 = 52;
int cc2 = 53;
int cc3 = 54;

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
	Serial.begin(31250); // MIDI rate
	//Serial2.begin(9600); // Bluetooth rate

	for (int i = 0; i < digitalInputs; i++)
		pinMode(i, INPUT_PULLUP);

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
		
		if (inputAnalog[i] != iAlag[i]) // if there is difference
		{
			ccValue[i] = inputAnalog[i] / 8; // calc the CC value based on the raw value
			// send the MIDI
			MIDImessage(176, 1, ccValue[i]); // usbMIDI.sendControlChange(i, ccValue[i], 3);
			// set raw reading to lagged array for next comparison
		}

		iAlag[i] = inputAnalog[i];
	}

	delay(5); // limits MIDI messages to reasonable number
}

void MIDImessage(int command, int data1, int data2) //pass values out through standard Midi Command
{
	//Serial2.println(command);
	//Serial2.println(data1);
	//Serial2.println(data2);

	Serial.write(command);
	Serial.write(data1);
	Serial.write(data2);
}
