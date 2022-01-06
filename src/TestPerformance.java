import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


/**
 * tests the run time of every method.
 * create different input files - asceding, meaning the input time are in  ascending order, descding, and randomize(ramdom values)
 *
 *
 */
public class TestPerformance {
    public static void createAscFile(){
        File fout = new File("ascending.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("MakeHeap");
            bw.newLine();

            for (int i = 0; i < 100; i++) {
                bw.write("Insert " + i);
                bw.newLine();
            }
            for (int i = 0; i < 20; i++) {
                bw.write("ExtractMin");
                bw.newLine();
            }

            bw.write("MakeHeap");
            bw.newLine();

            for (int i = 100; i < 200; i++) {
                bw.write("Insert " + i);
                bw.newLine();
            }
            for (int i = 0; i < 20; i++) {
                bw.write("ExtractMin");
                bw.newLine();
            }
            bw.write("UNION");
            bw.newLine();


            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void createDescFile(){
        File fout = new File("descending.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("MakeHeap");
            bw.newLine();

            for (int i = 10000; i >0; i--) {
                bw.write("Insert " + i);
                bw.newLine();
            }
            for (int i = 0; i < 20; i++) {
                bw.write("ExtractMin");
                bw.newLine();
            }

            bw.write("MakeHeap");
            bw.newLine();

            for (int i = 10000; i >0; i--) {
                bw.write("Insert " + i);
                bw.newLine();
            }
            for (int i = 0; i < 20; i++) {
                bw.write("ExtractMin");
                bw.newLine();
            }
            bw.write("UNION");
            bw.newLine();


            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void randomizeFile() {
        File fout = new File("randomize.txt");
        FileOutputStream fos = null;
        int min = 0;
        int max = 1000;
        try {
            fos = new FileOutputStream(fout);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("MakeHeap");
            bw.newLine();

            for (int i = 10000; i > 0; i--) {
                int num = min + (int) (Math.random() * ((max - min) + 1));

                bw.write("Insert " + num);
                bw.newLine();
            }
            for (int i = 0; i < 20; i++) {
                bw.write("ExtractMin");
                bw.newLine();
            }

            bw.write("MakeHeap");
            bw.newLine();

            for (int i = 10000; i > 0; i--) {
                int num = min + (int) (Math.random() * ((max - min) + 1));

                bw.write("Insert " + num);
                bw.newLine();
            }
            for (int i = 0; i < 20; i++) {
                bw.write("ExtractMin");
                bw.newLine();
            }
            bw.write("UNION");
            bw.newLine();


            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        createAscFile();
        createDescFile();
        randomizeFile();
        Parser.debugMode = false;
        Parser.userFileCommand(1, "ascending.txt");

        Parser.userFileCommand(3, "randomize.txt");
        Parser.userFileCommand(3, "descending.txt");
        Parser.userFileCommand(3, "ascending.txt");




    }
}
