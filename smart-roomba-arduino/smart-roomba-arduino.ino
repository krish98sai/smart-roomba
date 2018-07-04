#include <ESP8266WiFi.h>
#include <WiFiUdp.h>
#include <Roomba.h>

// General WiFi credentials
const char* ssid = "SSID";
const char* password = "PASS";

// UDP server config
WiFiUDP UDPControlServer;
unsigned int UDPPort = 2807;
const int packetSize = 2;
byte packet[packetSize];

// Roomba selection
Roomba roomba(&Serial, Roomba::Baud115200);

void connect_wifi(){
  WiFi.begin(ssid, password);
  const IPAddress ip = IPAddress(192, 168, 1, 60);
  const IPAddress ip_host = IPAddress(192, 168, 1, 1);
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
    roomba.start();
    roomba.driveDirect(500, 500);
  } else if(command.equals("01")){
    roomba.start();
    roomba.driveDirect(0, 0);
  } else{
    // Do nothing
  }
}

void setup(){
  Serial.begin(115200);
  delay(10);
  connect_wifi();
}

void loop(){
  callbackUDPServer();
  delay(1);
}

