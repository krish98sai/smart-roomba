#include <ESP8266WiFi.h>
#include <WiFiUdp.h>
#include <Roomba.h>
#include <Credentials.h>

// General WiFi credentials
Credentials credentials();
const char* ssid = credentials.ssid;
const char* password = credentials.password;

// UDP server config
WiFiUDP UDPControlServer;
unsigned int UDPPort = 2807;
const int packetSize = 2;
byte packet[packetSize];

// Roomba selection
//Roomba roomba(&Serial, Roomba::Baud115200);

void connect_wifi(){
  WiFi.begin(ssid, password);
  const IPAddress ip = IPAddress(10, 0, 0, 60);
  const IPAddress ip_host = IPAddress(10, 0, 0, 1);
  const IPAddress ip_mask = IPAddress(255, 255, 255, 0);
  WiFi.config(ip, ip_host, ip_mask);

  while(WiFi.status() != WL_CONNECTED){
    delay(500);
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
  if(command.equals("00")){
    forwardDrive();
  } else if(command.equals("01")){
    backwardDrive();
  } else if(command.equals("02")){
    rotateRight();
  } else if(command.equals("03")){
    rotateLeft();
  } else if(command.equals("CL")){
    startCleaning();
  } else if(command.equals("ST")){
    stopCleaning();
  } else{
    // Do nothing
  }
}

void setupRoombaBaud(){
  Serial.write(129);
  delay(50);
  Serial.write(11);
  delay(50);
}

void startCleaning(){
  Serial.write(136);
  delay(50);
  Serial.write(0);
}

void stopCleaning(){
  Serial.write(136);
  delay(50);
  Serial.write(255);
}

void forwardDrive(){
  Serial.write(137);
  Serial.write((byte)0x00);
  Serial.write(0xc8);
  Serial.write(0x80);
  Serial.write((byte)0x00);
  delay(1000);
  stopDrive();
}

void backwardDrive(){
  Serial.write(137);
  Serial.write(0xff);
  Serial.write(0x38);
  Serial.write(0x80);
  Serial.write((byte)0x00);
  delay(1000);
  stopDrive();
}

void rotateRight(){
  Serial.write(137);
  Serial.write((byte)0x00);
  Serial.write(0x0f);
  Serial.write(0xff);
  Serial.write(0xff);
  delay(1000);
  stopDrive();
}

void rotateLeft(){
  Serial.write(137);
  Serial.write((byte)0x00);
  Serial.write(0x0f);
  Serial.write((byte)0x00);
  Serial.write(0x01);
  delay(1000);
  stopDrive();
}

void stopDrive(){
  Serial.write(137);
  Serial.write((byte)0x00);
  Serial.write((byte)0x00);
  Serial.write((byte)0x00);
  Serial.write((byte)0x00);
}

void setup(){
  Serial.begin(115200);
  //setupRoombaBaud();
  connect_wifi();
  delay(50);
  Serial.write(128); // listen io mode
  delay(50);
  startUDPServer();
  delay(50);
  Serial.write(132); // full mode
  delay(50);
}

void loop(){
  callbackUDPServer();
  delay(1);
}
