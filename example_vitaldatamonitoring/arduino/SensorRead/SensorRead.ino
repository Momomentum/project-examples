#include <Arduino.h>
#include <Wire.h>
#include <stdint.h>
#include <SPI.h>
#if not defined (_VARIANT_ARDUINO_DUE_X_) && not defined (_VARIANT_ARDUINO_ZERO_)
  #include <SoftwareSerial.h>
#endif

#include "Adafruit_BLE.h"
#include "Adafruit_BluefruitLE_SPI.h"
#include "Adafruit_BluefruitLE_UART.h"
#include "BluefruitConfig.h"

#include <stdlib.h>

#define FACTORYRESET_ENABLE         0
#define MINIMUM_FIRMWARE_VERSION    "0.6.6"
#define MODE_LED_BEHAVIOUR          "MODE"



/*=========================================================================*/

// Create the bluefruit object, either software serial...uncomment these lines

SoftwareSerial bluefruitSS = SoftwareSerial(BLUEFRUIT_SWUART_TXD_PIN, BLUEFRUIT_SWUART_RXD_PIN);

Adafruit_BluefruitLE_UART ble(bluefruitSS, BLUEFRUIT_UART_MODE_PIN, BLUEFRUIT_UART_CTS_PIN, BLUEFRUIT_UART_RTS_PIN);



#define PULSE_PIN A0
#define SKINRES1_PIN A1
#define SKINRES2_PIN A2

typedef struct GyroAccData {
    uint16_t dGyro;
    uint16_t dAcc;
} GyroAccData;

typedef struct PulseData {
    uint16_t pulseValue;
} PulseData;

typedef struct HumData {
    uint16_t humValue;
} HumData;


/* GLOBALS */


PulseData pd;
uint16_t BPM = 0;

GyroAccData gad;
HumData hd;

//Address for gyro sensor
const int MPU_addr=0x68;

// Flags
bool MEASURE_NOW=false;


// A small helper
void error(const __FlashStringHelper*err) {
  Serial.println(err);
  while (1);
}


/* INTERRUPT SETUP */
void interruptSetup() {
    cli();

    // timer 1 is used for sending
    // when a package is ready
    //set timer1 interrupt at 2Hz
    TCCR1A = 0;// set entire TCCR1A register to 0
    TCCR1B = 0;// same for TCCR1B
    TCNT1  = 0;//initialize counter value to 0
    // set compare match register for 2hz increments
    OCR1A = 31249;// = 16000000 / (256 * 2) - 1 (must be <65536)
    // turn on CTC mode
    TCCR1B |= (1 << WGM12);
    // Set CS10 and CS12 bits for 1024 prescaler
    TCCR1B |= (1 << CS12) | (0 << CS11) | (0 << CS10);
    // enable timer compare interrupt
    TIMSK1 |= (1 << OCIE1A);

    sei();
}

/* ARDUINO SETUP */
void setup() {
  Serial.begin(115200);
  Serial.println(F("Adafruit Bluefruit Vital Connection"));
  Serial.println(F("---------------------------------------"));
  
  /* Initialise the module */
  Serial.print(F("Initialising the Bluefruit LE module: "));

  if ( !ble.begin(VERBOSE_MODE) )
  {
    error(F("Couldn't find Bluefruit....."));
  }
  Serial.println( F("OK!") );

  if ( FACTORYRESET_ENABLE )
  {
    /* Perform a factory reset to make sure everything is in a known state */
    Serial.println(F("Performing a factory reset: "));
    if ( ! ble.factoryReset() ){
      error(F("Couldn't factory reset"));
    }
  }

  /* Disable command echo from Bluefruit */
  ble.echo(false);

  Serial.println("Requesting Bluefruit info:");
  /* Print Bluefruit information */
  ble.info();
  
  Serial.println();

  ble.verbose(false);  // debug info is a little annoying after this point!

  /* Wait for connection */
  while (! ble.isConnected()) {
     delay(2);
  }

  // LED Activity command is only supported from 0.6.6
  if ( ble.isVersionAtLeast(MINIMUM_FIRMWARE_VERSION) )
  {
    // Change Mode LED Activity
    Serial.println(F("******************************"));
    Serial.println(F("Change LED activity to " MODE_LED_BEHAVIOUR));
    ble.sendCommandCheckOK("AT+HWModeLED=" MODE_LED_BEHAVIOUR);
    Serial.println(F("******************************"));
  }
//    Gyro mit dem Arduino verbinden..
    Wire.begin();
    Wire.beginTransmission(MPU_addr);
    Wire.write(0x6B);  // PWR_MGMT_1 register
    Wire.write(0);     // set to zero (wakes up the MPU-6050)
    Wire.endTransmission(true);

    interruptSetup();
}

/* INTERRUPT ROUTINES */
ISR(TIMER1_COMPA_vect) {
        MEASURE_NOW=true;
}

//ISR(TIMER2_COMPA_vect) {
//    MEASURE_NOW=true;
//}


void measure_pulseSensor() {
    int16_t hb_val = analogRead(A0);
    pd.pulseValue = hb_val;
    Serial.println(hb_val);
}

void measure_gyroSensor() {
    int16_t AcX,AcY,AcZ,Tmp,GyX,GyY,GyZ;
    int16_t AcXold, AcYold, AcZold, TmpOld, GyXold, GyYold, GyZold;
    bool firststep = true;

    Wire.beginTransmission(MPU_addr);
    Wire.write(0x3B);  // starting with register 0x3B (ACCEL_XOUT_H)
    Wire.endTransmission(false);
    Wire.requestFrom(MPU_addr,14,true);  // request a total of 14 registers
    if(firststep) {
        AcXold = AcX;
        AcYold = AcY;
        AcZold = AcZ;
        GyXold = GyX;
        GyYold = GyY;
        GyZold = GyZ;
        firststep = false;
    }
    AcX=Wire.read()<<8|Wire.read();  // 0x3B (ACCEL_XOUT_H) & 0x3C (ACCEL_XOUT_L)
    AcY=Wire.read()<<8|Wire.read();  // 0x3D (ACCEL_YOUT_H) & 0x3E (ACCEL_YOUT_L)
    AcZ=Wire.read()<<8|Wire.read();  // 0x3F (ACCEL_ZOUT_H) & 0x40 (ACCEL_ZOUT_L)
    Tmp=Wire.read()<<8|Wire.read();  // 0x41 (TEMP_OUT_H) & 0x42 (TEMP_OUT_L)
    GyX=Wire.read()<<8|Wire.read();  // 0x43 (GYRO_XOUT_H) & 0x44 (GYRO_XOUT_L)
    GyY=Wire.read()<<8|Wire.read();  // 0x45 (GYRO_YOUT_H) & 0x46 (GYRO_YOUT_L)
    GyZ=Wire.read()<<8|Wire.read();  // 0x47 (GYRO_ZOUT_H) & 0x48 (GYRO_ZOUT_L)

    if(!firststep) {
        int16_t dAcX = AcX - AcXold;
        int16_t dAcY = AcY - AcYold;
        int16_t dAcZ = AcZ - AcZold;
        int16_t dGyX = GyX - GyXold;
        int16_t dGyY = GyY - GyYold;
        int16_t dGyZ = GyZ - GyZold;
        uint16_t dAc = sqrt(pow(dAcX, 2) + pow(dAcY, 2) + pow(dAcZ, 2));
        uint16_t dGy = sqrt(pow(dGyX, 2) + pow(dGyY, 2) + pow(dGyZ, 2));
        gad = { dAc, dGy };
    }
}

void measure_humiditySensor() {
    pinMode(SKINRES1_PIN, INPUT);
    pinMode(SKINRES2_PIN, OUTPUT);
    digitalWrite(SKINRES2_PIN, LOW);
    delay(1);
    uint16_t skin_res_temp1 = analogRead(SKINRES1_PIN);
    pinMode(SKINRES1_PIN, OUTPUT);
    digitalWrite(SKINRES1_PIN, LOW);
    pinMode(SKINRES2_PIN, INPUT);
    delay(1);
    uint16_t skin_res_temp2 = analogRead(SKINRES2_PIN);
    hd.humValue = (skin_res_temp1 + skin_res_temp2) / 2;
}

void send(String pSend, String gSend, String aSend, String hSend){
    String sendStr = pSend + "|" + gSend + "|" + aSend + "|" + hSend;
    Serial.println(sendStr);
    ble.print("AT+BLEUARTTX=");   
    //Sends the Value to Android!!!!!!!!!!!!!!!!
    //ble.println(sendStr);

    //     var = map(var, 0, 1023, 0, 999);
}


/* ARDUINO LOOP
 * HIER KOMMEN ALLE BERECHNUNGEN REIN */

void loop() {

    if (MEASURE_NOW) {
        Serial.println("MEASURING");
        measure_pulseSensor();
        measure_gyroSensor();
        measure_humiditySensor();
        MEASURE_NOW = false;
        String bpmString = String(pd.pulseValue);
        String gyroString = String(gad.dGyro);
        String accString = String(gad.dAcc);
        String humString = String(hd.humValue);
        send(bpmString, gyroString, accString, humString);
    }
}
