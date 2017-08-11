
import Distributor.AlarmClock;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Locale;


public class TimeTableTest {
    private static final Logger log = Logger.getLogger(TimeTableTest.class);

    public static void main(String[] args) {

        Locale.getDefault();

        try {
            new AlarmClock().getTime();
        }catch (ParseException e){
            log.error("Parse exception", e);
        }
    }
}

