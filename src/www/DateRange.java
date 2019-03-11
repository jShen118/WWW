package www;
import java.util.ArrayList;

public class DateRange {
    Date lowerBound;
    Date upperBound;
    
    DateRange(Date lowerBound, Date upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    
    public boolean contains(Date date) {
        boolean dateGreaterOrEqualLowerBound = (date.compareTo(lowerBound) == 1 | date.compareTo(lowerBound) == 0);
        boolean dateLessOrEqualUpperBound = (date.compareTo(upperBound) == -1 | date.compareTo(upperBound) == 0);
        return (dateGreaterOrEqualLowerBound & dateLessOrEqualUpperBound);
    }
    
    public ArrayList<Week> makeWeeks() { //creates all weeks necessary to cover range
        ArrayList<Week> toReturn = new ArrayList<>();
        Date firstDayOfWeek = lowerBound;
        while(firstDayOfWeek.compareTo(upperBound) != 1) {
            toReturn.add(new Week(firstDayOfWeek));
            firstDayOfWeek = firstDayOfWeek.future(7);
        }
        return toReturn;
    }
}
