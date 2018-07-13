#include <ESP8266WiFi.h>
#include <WiFiUdp.h>
#include <Roomba.h>
#include "libraries/Credentials/Credentials.h"

// UDP server config
WiFiUDP UDPControlServer;
unsigned int UDPPort = 2807;
const int packetSize = 2;
byte packet[packetSize];

// Roomba selection
Roomba roomba(&Serial, Roomba::Baud115200);

void connect_wifi(){
  WiFi.begin(SSID, PASSWORD);
  const IPAddress ip = IPAddress(10, 0, 0, 60);
  const IPAddress ip_host = IPAddress(10, 0, 0, 1);
  const IPAddress ip_mask = IPAddress(255, 255, 255, 0);
  WiFi.config(ip, ip_host, ip_mask);

  int timeout = 0;
  while(WiFi.status() != WL_CONNECTED){
    if(timeout >= 60000){
      break;
    }
    delay(500);
    timeout += 500;
  }
}

void startUDPServer(){
  UDPControlServer.begin(UDPPort);
}

String parseByteToString(byte untranslated[]){
  String translated = "";
  for(int i = 0; i < packetSize; i++){
    translated += (char)untranslated[i];
  }
  return translated;
}

void callbackUDPServer(){
  int cb = UDPControlServer.parsePacket();

  if(cb){
    UDPControlServer.read(packet, packetSize);

    String command = parseByteToString(packet);
    execCommand(command);
  }
}

void execCommand(String command){
  if(command.equals("SM")){
    roomba.safeMode();
  } else if(command.equals("FM")){
    roomba.fullMode();
  } else if(command.equals("UP")){
    driveForward();
  } else if(command.equals("DN")){
    driveBackward();
  } else if(command.equals("RT")){
    rotateRight();
  } else if(command.equals("LT")){
    rotateLeft();
  } else if(command.equals("CL")){
    roomba.demo(Roomba::DemoCover);
  } else if(command.equals("DK")){
    roomba.dock();
  } else if(command.equals("ST")){
    roomba.demo(Roomba::DemoAbort);
  } else{
    // Do nothing
  }
}

void driveForward(){
  roomba.drive(200, Roomba::DriveStraight);
  delay(100);
  stopDrive();
}

void driveBackward(){
  roomba.drive(-200, Roomba::DriveStraight);
  delay(100);
  stopDrive();
}

void rotateRight(){
  roomba.drive(200, Roomba::DriveInPlaceClockwise);
  delay(100);
  stopDrive();
}

void rotateLeft(){
  roomba.drive(200, Roomba::DriveInPlaceCounterClockwise);
  delay(100);
  stopDrive();
}

void stopDrive(){
  roomba.drive(0, 0);
}

void setup(){
  Serial.begin(115200);
  connect_wifi();
  roomba.start();
  startUDPServer();
}

void loop(){
  callbackUDPServer();
  delay(1);
}
