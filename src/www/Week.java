/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

import java.util.ArrayList;

/**
 *
 * @author joshuashen
 */

public class Week {
    Date firstDay;
    Date secondDay;
    Date thirdDay;
    Date fourthDay;
    Date fifthDay;
    Date sixthDay;
    Date seventhDay;
    ArrayList<Date> allDays = new ArrayList<>();
    
    Week(Date firstDay) {
        this.firstDay = firstDay;
        secondDay = firstDay.future(1);
        thirdDay = secondDay.future(1);
        fourthDay = thirdDay.future(1);
        fifthDay = fourthDay.future(1);
        sixthDay = fifthDay.future(1);
        seventhDay = sixthDay.future(1);
        
        allDays.add(firstDay);
        allDays.add(secondDay);
        allDays.add(thirdDay);
        allDays.add(fourthDay);
        allDays.add(fifthDay);
        allDays.add(sixthDay);
        allDays.add(seventhDay);
    }
    
    public boolean contains(Date date) {
        for(Date d: allDays) {
            if(d.equals(date)) {return true;}
        }
        return false;
    }
}
