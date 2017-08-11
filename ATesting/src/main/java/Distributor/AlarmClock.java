package Distributor;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static Distributor.DayPart.*;

/**
 * The class contains a method to display the greeting according to a day period.
 */
public class AlarmClock {

        private static final Logger logger = Logger.getLogger(AlarmClock.class);
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        private ResourceBundle res = ResourceBundle.getBundle("bundle");

        private Date currentTime, currentDate, morning, day, evening, night;

        public AlarmClock(){
            currentDate = new Date();
        }

        public AlarmClock(String time) throws ParseException{
        currentDate = dateFormat.parse(time);
        }

        public DayPart getTime() throws ParseException {

            currentTime = dateFormat.parse(dateFormat.format(currentDate));

            morning = dateFormat.parse(MORNING.toString());
            day = dateFormat.parse(DAY.toString());
            evening = dateFormat.parse(EVENING.toString());
            night = dateFormat.parse(NIGHT.toString());

            if (currentTime.equals(morning) | currentTime.after(morning) && currentTime.before(day)) {
                logger.info("Generate <Good morning, World!> message.");
                System.out.println(res.getString("MORNING"));
                return MORNING;

            } else if (currentTime.equals(day) | currentTime.after(day) && currentTime.before(evening)) {
                logger.info("Generate <Good day, World!> message");
                System.out.println(res.getString("DAY"));
                return DAY;

            } else if (currentTime.equals(evening) | currentTime.after(evening) && currentTime.before(night)) {
                logger.info("Generate <Good evening, World!> message");
                System.out.println(res.getString("EVENING"));
                return EVENING;

            } else {
                logger.info("Generate <Good night, World!> message");
                System.out.println(res.getString("NIGHT"));
                return NIGHT;
            }
        }
}




