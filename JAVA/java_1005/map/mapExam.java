package java_1005.map;

import java.util.HashMap;

public class mapExam {
    public static void main(String[] args) {
        
        HashMap<String, String>map = new HashMap<String, String>();

        map.put("people", "���");
        map.put("baseball", "�߱�");

        System.out.println(map.size());   
        System.out.println(map.get("people"));   
        System.out.println(map.containsKey("people"));   
        System.out.println(map.remove("people"));   
        System.out.println(map.size());   

    }   
}
