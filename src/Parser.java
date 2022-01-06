import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * excute command from file of from the user.
 * userFileCommand method - user have to insert type, and file path, and the will be executed.
 * userInputCommand() - the user insert type and command, until "exit" command is typed.
 * debugMode - simple flag, for printing the hepas in the end of every command.
 */
public class Parser {
    // set to true for debugging
    public static boolean debugMode = true;
    public static CommandsTime commands   = new CommandsTime();

    //heap types
    List<MergeableHeap<Integer>> heaps = new LinkedList<>();
    enum HeapType {
        SortedHeap,
        UnsortedHeap,
        DisjointHeap
    }
    // type of heap as requested from the user
    private HeapType heapType ;
    //consturctor
    Parser(String heapType){
        this.heapType = HeapType.valueOf(heapType);

    }


    /**
     * get the heap and add it to the heaps list.
     */
    private  void createHeap(){
        switch (this.heapType){
            case SortedHeap:
                this.heaps.add(new MergeableHeapSortedInput());
                break;
            case UnsortedHeap:
                this.heaps.add(new MergeableHeapUnSortedInput());
                break;
            case DisjointHeap:
                this.heaps.add(new MergeableHeapUnSortedDisjointInput());
                break;
        }
    }



    /**
     * run the command as requested  by the use/file
     * @param command
     */
    public void executeCommand(String command){
        String[] part = command.split(" ");
        Long startOp, endOp;

        switch (part[0]){
            case "MakeHeap":
                 startOp =System.currentTimeMillis();
                createHeap();
                endOp =System.currentTimeMillis();
                commands.getMakeHeap().addTime(endOp - startOp);
                break;
            case "Insert" :
                startOp =System.currentTimeMillis();
                heaps.get(heaps.size() -1).insert(Integer.valueOf(part[1]));
                endOp =System.currentTimeMillis();
                commands.getInsert().addTime(endOp - startOp);
                break;
            case "ExtractMin" :
                startOp =System.currentTimeMillis();

                heaps.get(heaps.size() -1).extractMinimum();
                endOp =System.currentTimeMillis();
                commands.getExtract().addTime(endOp - startOp);
                break;
            case "MINIMUM" :
                startOp =System.currentTimeMillis();
                heaps.get(heaps.size() -1).minimum();
                endOp =System.currentTimeMillis();
                commands.getMinimum().addTime(endOp - startOp);
                break;
            case "UNION":
                startOp =System.currentTimeMillis();
                MergeableHeap<Integer> union = heaps.remove(0).union( heaps.remove(0));
                endOp =System.currentTimeMillis();
                commands.getUnion().addTime(endOp - startOp);
                heaps.add(0, union);
                break;

        }
        if(debugMode){
            for(int i = 0; i< heaps.size(); i++) {
                System.out.println("heap " + i + heaps.get(i).getLinkedList());
                System.out.println("heap " + i + heaps.get(i).getLinkedList().toStringPrev());
        }

        }
    }

    /**
     * return heap type
     * @param type
     * @return
     */
    private static String getHeapType(int type){
        switch (type){
            case 1 :
                return HeapType.SortedHeap.name();
            case 2 :
                return HeapType.UnsortedHeap.name();
            case 3 :
                return HeapType.DisjointHeap.name();
            default:
                break;
        }
        return "error";
    }


    /**
     * user insert the type of the heap(1,2,3) and then insert the command he want to run.
     * the program will termiante for input "exit"
     */
    private static void userInputCommand() {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter(";|\r?\n|\r");

        System.out.println("Enter number to speficy theMergeableHeap type you want to work with. 1 for SortedHeap, 2 for UnsortedHeap, 3 for DisjointHeap");
        int type = scan.nextInt();
        Parser parser = new Parser(getHeapType(type));
        while (true) {
            System.out.println("Enter command : MakeHeap, Insert, ExtractMin, MINIMUM, UNION or exit for exit");
            String command = scan.next();
            if (command == "exit") {
                return;
            }
            parser.executeCommand(command);
        }
    }

    /**
     * user required to Insert type and file and run, the method will run the file command
     */
    private static void userFileCommand(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number to speficy theMergeableHeap type you want to work with. 1 for SortedHeap, 2 for UnsortedHeap, 3 for DisjointHeap");
        int type = scan.nextInt();
        System.out.println("Enter file path");

        String insertFilePath = scan.next();
        userFileCommand(type, insertFilePath);

    }

    /**
     * run commands from file
     * @param type
     * @param path
     */
    public static void userFileCommand(int type, String path){
        Parser parser = new Parser(getHeapType(type));
        long start = System.currentTimeMillis();
        int count = 0;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                parser.executeCommand(data);
            }
            myReader.close();
            System.out.println(count);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("file " + path +  " type " + type + " time " + (end - start));


    }







}
