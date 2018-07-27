# Smart Roomba

## Installation Guide
### Hardware and Flashing
#### Materials
1. ESP8266-01
2. Voltage regulator with at least 20 V input and 3.3 V output
3. PNP Transistor
4. Wires
5. FT232 USB to TTL converter (Or something that can flash the ESP8266-01)
6. Breadboard
7. 3.3 V Breadboard Power Supply

#### Required Dependencies
- Roomba library for Arduino
  - http://www.airspayce.com/mikem/arduino/Roomba/Roomba-1.3.zip
- SimpleTimer library for Arduino
  - https://github.com/jfturcot/SimpleTimer
- Arduino Client for MQTT
  - https://github.com/knolleary/pubsubclient

#### Installation
1. Connect components using circuit diagram:
<img src="./circuit-diagrams/programming_esp8266.png" width="600px">

2. Ensure GPIO0 is connected to ground
3. Connect TTL converter to computer (Keep breadboard power supply off)
4. Bring up Arduino IDE and open ./smart-roomba-arduino/smart-roomba-arduino.ino
5. Ensure all dependencies are installed
6. Rename Credentials.h~ to Credentials.h and replace placeholder SSID, PASSWORD, and IP_ADDRESS with actual values
7. Press upload and turn on breadboard power supply when Arduino IDE says "Uploading..."
8. After the program is fully uploaded, unplug the GPIO0 pin from ground

#### Running
1. Wake Roomba from sleep (Ensure buttons are lit up)
2. Connect components using circuit diagram:
<img src="./circuit-diagrams/roomba_esp8266.png" width="600px">

### Android
#### Installation
1. Open ./smart-roomba-android/ in Android Studio IDE
2. Build the project (Build > Make Project)
3. Plug-in and turn on Android device
4. Run the app (Run > Run 'app')
5. Select the correct device from the 'Select Deployment Target' and press "OK"

#### Running
1. Open "Smart Roomba" app on Android device

## Borrowed Resources
<div>Icons made by <a href="https://www.flaticon.com/authors/google" title="Google">Google</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
