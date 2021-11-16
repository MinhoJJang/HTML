package java_1005.file;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileExam {
    public static void main(String[] args) throws IOException{
        FileOutputStream output = new FileOutputStream("out.txt");
        output.close();
    }
}
