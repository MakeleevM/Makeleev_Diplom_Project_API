package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

public class TestData {

    public String errorRegister = "Missing email or username";
    public Integer id = 2,
            page = 2;

    public String name = "mmakeleev",
            job = "manual QA",
            updatedJob = "AQA",
            updatedName = "msmakeleev";

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

}
