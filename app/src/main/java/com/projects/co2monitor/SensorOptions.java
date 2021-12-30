package com.projects.co2monitor;

public enum SensorOptions {
    sensor1(1),
    sensor2(2),
    sensor3(3);
    private int sensorNo;
    private SensorOptions(int sensorNo){
        this.sensorNo = sensorNo;
    }
    public static SensorOptions getSensor(int sensorNo){
        for(SensorOptions i: SensorOptions.values()){
            if(i.sensorNo==sensorNo) return i;
        }
        throw new IllegalArgumentException("Invalid sensor number");
    }
}
