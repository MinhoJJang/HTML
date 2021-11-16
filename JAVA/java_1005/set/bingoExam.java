package java_1005.set;

import java.util.HashSet;
import java.util.Set;

public class bingoExam {
    // 오류남.. set으로 배열을 만들고싶은데 ㅠㅠ 
    public static void main(String[] args) {

        Set set = new HashSet();
        
        int[][] board = new int[5][5];
        int start = 0;
        int end = 5;
        int cnt=0;
        int idx=0;

        while (end <=25){
            int i;
            idx =0;
            for (i=start; set.size()<end; i++){
                set.add((int)(Math.random()*50)+1+"");
                board[cnt][idx++] = (int)((Math.random()*50)+1);
            }
            idx =0;
            for (int t = start; t<end; t++){
                System.out.print(board[cnt][idx++]);
            }
            start+=5;
            end+=5;          
            cnt++;
        }


    }
}
