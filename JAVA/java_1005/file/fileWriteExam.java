package java_1005.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class fileWriteExam {
    public static void main(String[] args) throws IOException{
        PrintWriter pw = new PrintWriter("out2.txt");
        for(int i=1; i<11; i++){
            String data = i + "��° ���Դϴ�." ;
            pw.println(data);
        }
        pw.close();

        PrintWriter pw2 = new PrintWriter(new FileWriter("out.txt", true));
        for(int i=11; i<21; i++){
            String data = i + "��° ���Դϴ�." ;
            pw2.println(data);
        }
        pw2.close();
    }
}
