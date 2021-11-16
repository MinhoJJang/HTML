package java_1005.file;

import java.io.*;

public class fileRead {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("out2.txt"));
        while(true) {
            String line = br.readLine();
            if (line == null) break;
            System.out.println(line);
        }
        br.close();
    }
}
