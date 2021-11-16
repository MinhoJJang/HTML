package java_0928.super_time;

public class time {
    private int hour;
    private int minute;
    private int second;

    time(int hour, int minute, int second) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour >= 24) {
            // System.out.println("Can't set");
            return;
        }
        this.hour = hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute >= 60) {
            return;
        }

        this.minute = minute;
    }

    public int getSecond() {

        return this.second;
    }

    public void setSecond(int second) {
        if (second < 0 || second >= 60) {
            return;
        }
        this.second = second;
    }

  

}
