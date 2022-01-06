/**
 * This class is in use only to measure  performance of the job, for every operation
 */
public class CommandsTime {
    private CommandTime makeHeap = new CommandTime();
    private CommandTime insert = new CommandTime();
    private CommandTime union = new CommandTime();
    private CommandTime extract = new CommandTime();
    private CommandTime minimum = new CommandTime();

    public CommandTime getMakeHeap() {
        return makeHeap;
    }

    public CommandTime getInsert() {
        return insert;
    }

    public CommandTime getUnion() {
        return union;
    }

    public CommandTime getExtract() {
        return extract;
    }

    public CommandTime getMinimum() {
        return minimum;
    }

    @Override
    public String toString() {
        return "CommandsTime{" +
                "makeHeap=" + makeHeap +
                ", insert=" + insert +
                ", union=" + union +
                ", extract=" + extract +
                ", minimum=" + minimum +
                '}';
    }
}
