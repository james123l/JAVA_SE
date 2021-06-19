package io.stream.questions;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*判断一个字符在文件内出现的次数
* */

public class CalculateCharTimes {
    private static final String defaultReadpath = "test.txt";
    private static final String defaultWritepath = "data.txt";
    public static void main(String[] args) {
        File defaultReadFile = new File(defaultReadpath);
        File defaultWriteFile = new File(defaultWritepath);
        saveData(calculateFunc(defaultReadFile),defaultWriteFile);

    }
    //计算次数
    public static Map<Character,Integer> calculateFunc(File file){
        HashMap<Character,Integer> hashMap = null;
        BufferedReader bufferedReader = null;
        try {
            hashMap = new HashMap<>();
            bufferedReader = new BufferedReader( new FileReader(file));
            char[] buffer = new char[1024];
            int length;
            while((length=bufferedReader.read(buffer))!= -1) {
                for (int i = 0; i < length; i++) {
                    if(hashMap.containsKey(buffer[i])){
                        hashMap.put(buffer[i],hashMap.get(buffer[i])+1);
                    }else{
                        hashMap.put(buffer[i],1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
    //写入文件
    public static void saveData(Map<Character,Integer> hashMap,File file){
        BufferedWriter bufferedWriter =null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator;
                bufferedWriter.write(entry.getKey()+"\t times \t"+entry.getValue()+'\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedWriter!=null) bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
