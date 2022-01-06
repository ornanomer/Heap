/**
 * count time and number of time for every operation
 */
public class CommandTime {
    private int count;
    private long time;

    public void addTime(long time){
        count ++;
        this.time +=  time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        double avg= time*1.0 / count;
        return "CommandTime{" +
                "count=" + count +
                ", time=" + time +
                ", average=" + avg +

                '}';
    }
}
