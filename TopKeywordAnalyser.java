package tech.codingclub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopKeywordAnalyser implements Runnable{

    private final String filePath;
    public TopKeywordAnalyser(String filePath){
        this.filePath = filePath;
    }
    public void run(){
        ArrayList<String> data = FileUtility.readFileAsList(filePath);

        HashMap<String,Integer> keywordCounter = new HashMap<String, Integer>();
        for(String row: data){
            String[] keywords = row.split(" ");
            for(String keyword: keywords){
                String str = keyword.toLowerCase();
                if(!keywordCounter.containsKey(str)){
                    keywordCounter.put(str,1);
                }else{
                    Integer value = keywordCounter.get(str);
                    keywordCounter.put(str,value+1);
                }
            }
        }
        ArrayList<KeywordCount> keywordCountArrayList = new ArrayList<KeywordCount>();

        for(String keyword:keywordCounter.keySet()){
            keywordCountArrayList.add(new KeywordCount(keyword,keywordCounter.get(keyword)));
        }
        Collections.sort(keywordCountArrayList, new Comparator<KeywordCount>() {
            public int compare(KeywordCount o1, KeywordCount o2) {
                if(o2.count == o1.count)
                    return o1.KeywordCount.compareTo(o2.KeywordCount);
                return o2.count-o1.count;
            }
        });
//        for(KeywordCount keywordCount: keywordCountArrayList){
//            System.out.println(keywordCount.KeywordCount + " "+ keywordCount.count);
//        }
        String json = new Gson().toJson(keywordCountArrayList);
        System.out.println(json);

        String convertJson = "\"KeywordCount\":\"Hello GSON\",\"count\":100}}";
        KeywordCount keywordCount = new Gson().fromJson(convertJson,KeywordCount.class);
        System.out.println("Converted to object "+ keywordCount.KeywordCount+" : "+keywordCount.count);
        String convertJsonArray = "[{\"KeywordCount\":\"jaya\",\"count\":10},{\"KeywordCount\":\"he\",\"count\":6}]";
        ArrayList<KeywordCount> convertArrayList = new Gson().fromJson(convertJsonArray, new TypeToken<ArrayList<KeywordCount>>(){}.getType());
        System.out.println("Converting JSON Array");
        for(KeywordCount keywordCountTemp:convertArrayList){
            System.out.println(keywordCountTemp.KeywordCount+" : "+keywordCountTemp.count);
        }
    }
    public static void main(String[] args) {
        
        TaskManager taskManager = new TaskManager(1);
        taskManager.waitTillQueueIsFreeAndAddTask(new TopKeywordAnalyser("/home/tanaysri_/Downloads/Anthem.txt"));

    }
}
