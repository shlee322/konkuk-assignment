import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 컴퓨터 응용 및 실습 1
 *
 * File Merge
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.05.11
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/ca/filemerge/
 *
 */
public class Merge1 {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.err.println("Usage: output_path input1_path input2_path [input_path]...");
            return;
        }

        String[] input_paths = new String[args.length-1];
        System.arraycopy(args, 1, input_paths, 0, input_paths.length);

        merge(args[0], input_paths);
    }

    private static void merge(String output_path, String[] input_paths) {
        PrintWriter output = null;
        Scanner[] inputs = new Scanner[input_paths.length];

        try {
            output = new PrintWriter(new File(output_path));
            for(int i=0; i<input_paths.length; i++) {
                inputs[i] = new Scanner(new File(input_paths[i]));
            }

            int writeOp = 1;
            while (writeOp > 0) {
                writeOp = 0;
                for(int i=0; i<input_paths.length; i++) {
                    if(!inputs[i].hasNext()) continue;

                    output.println(inputs[i].next());
                    writeOp++;
                }
            }

        } catch (IOException e) {
            System.err.println("Error in merge()\n" + e.getMessage());
        } finally {
            if(output != null) output.close();
            for(int i=0; i<input_paths.length; i++) {
                if(inputs[i] != null) inputs[i].close();
            }
        }
    }
}
