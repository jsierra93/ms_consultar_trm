package co.com.jsierra.consultartrm.utilities;

import java.time.LocalDate;

public class UtilitiesManager {

    /**
     * Falta hacer calculo de dias habiles para festivos colombia
     *
     **/
    public static boolean workingDay(LocalDate date){
        boolean isWorking = true;
        if ( ("SATURDAY").equalsIgnoreCase(date.getDayOfWeek().toString()) || ("SUNDAY").equalsIgnoreCase(date.getDayOfWeek().toString())|| ("MONDAY").equalsIgnoreCase(date.getDayOfWeek().toString())){
            isWorking = false;
        }
    return isWorking;
    }

    public static LocalDate lastWorkingDay(LocalDate day){

        if (("SUNDAY").equalsIgnoreCase(day.getDayOfWeek().toString())){
            day = day.minusDays(1);
        }else
            if (("MONDAY").equalsIgnoreCase(day.getDayOfWeek().toString())){
                day = day.minusDays(2);
            }
        return day;
    }
    public static LocalDate nextWorkingDay(LocalDate day){
        if (("SATURDAY").equalsIgnoreCase(day.getDayOfWeek().toString())){
            day = day.plusDays(2);
        } else
            if (("SUNDAY").equalsIgnoreCase(day.getDayOfWeek().toString())){
            day = day.plusDays(1);
        }
        return day;
    }
}
