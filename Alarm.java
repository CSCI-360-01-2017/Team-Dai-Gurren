/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robert
 */
public class Alarm {
    
    private Alarm alarmOne = new Alarm(6 , 00, true);
    private Alarm alarmTwo = new Alarm(7, 00, true);
    private int alarmHour;
    private int alarmMinute;
    private boolean alarmState;
    // constructor to create an alarm object       
    public Alarm (int hour, int minutes, boolean state){
        this.alarmHour= hour;
        this.alarmMinute = minutes;
        this.alarmState= state;
    }
  
    // allows user to set hour of chosen alarm 
    public void setAlarmHour(int hours){
        this.alarmHour = hours;
    
    }
    // return the curent hour the alarm is set to 
    public  int getAlarmHours (){
        return this.alarmHour;
    }
    //sets the minutes of the chosen alarm 
    public void setAlarmMinutes(int minutes){
        this.alarmMinute= minutes;
    }
    public int getAlarmMinutes(){
        return this.alarmMinute;
    }
    public void setAlarmActive (boolean state){
        this.alarmState = state;
        
    }
    public boolean getAlarmActive(){
    return this.alarmState;
    }
    
    public void changeAlarm(int hours, int minutes, boolean state){
        this.alarmHour = hours;
        this.alarmMinute = minutes;
        this.alarmState = state;
    
    }
    
    public void toggleAlarm(boolean state){
        this.alarmState = state;
        
    }
    public String soundAlarm(){
        return "The alarm is going off";
    }
}
