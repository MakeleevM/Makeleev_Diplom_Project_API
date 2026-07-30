package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

public class TestData {

    public String errorRegister = "Missing email or username";
    public Integer id = 2;
    public Integer page = 2;
    public String name = "mmakeleev";
    public String job = "manual QA";
    public String updatedJob = "AQA";
    public String updatedName = "msmakeleev";

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        return simpleDateFormat.format(new Date());
    }
}
