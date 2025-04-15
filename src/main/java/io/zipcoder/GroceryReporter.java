package io.zipcoder;

import com.sun.tools.javac.jvm.Items;
import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.*;

public class GroceryReporter {
    private final String originalFileText;

    Map<String, String> map = new HashMap<>();
    Map<String, Integer> trackingMap = new HashMap<>();

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }
// will post item name and item in map and then use same keys to map on trackingMap
    // call each map for the string processing
    @Override
    public String toString() {
        int count = 1;
        ItemParser parser = new ItemParser();
        List<Item> listOfStrings = new ArrayList<>(parser.parseItemList(this.originalFileText));
        for (Item list: listOfStrings){
            String[] listToSort = list.toString().split(" ");
           // Collections.addAll();
            System.out.println((Arrays.toString(list.toString().split(" "))));
            if (map.get("name").equals(list.getName())){
                map.put("name", list.getName());
                count++;
                trackingMap.put("name", count);
            } else {
                map.put("name", list.getName());
                trackingMap.put("name", count);
            }
            if (map.get("price").equals(Double.toString(list.getPrice()))){
                map.put("price", Double.toString(list.getPrice()));
                count++;
                trackingMap.put("name", count);
            } else {
                map.put("price", Double.toString(list.getPrice()));
                trackingMap.put("price", count);
            }
            if (map.get("type").equals(list.getType())){
                map.put("type", list.getType());
                count++;
                trackingMap.put("type", count);
            } else {
                map.put("type", list.getType());
                trackingMap.put("type", count);
            }
            if (map.get("expiration").equals(list.getExpiration())){
                map.put("expiration", list.getExpiration());
                count++;
                trackingMap.put("expiration", count);
            } else {
                map.put("expiration", list.getExpiration());
                trackingMap.put("expiration", count);
            }
        }
        System.out.println(map.toString());
        System.out.println(trackingMap.toString());
        return originalFileText;
    }

//    public void populateMaps(List<Items> list) {
//        Integer count=1;
//        if (map.get("name").equals(list.getName())){
//            map.put("name", list.getName());
//            count++;
//            trackingMap.put("name", count);
//        } else {
//            map.put("name", list.getName());
//            trackingMap.put("name", count);
//        }
//        if (map.get("price").equals(Double.toString(list.getPrice()))){
//            map.put("price", Double.toString(list.getPrice()));
//            count++;
//            trackingMap.put("name", count);
//        } else {
//            map.put("price", Double.toString(list.getPrice()));
//            trackingMap.put("price", count);
//        }
//        if (map.get("type").equals(list.getType())){
//            map.put("type", list.getType());
//            count++;
//            trackingMap.put("type", count);
//        } else {
//            map.put("type", list.getType());
//            trackingMap.put("type", count);
//        }
//        if (map.get("expiration").equals(list.getExpiration())){
//            map.put("expiration", list.getExpiration());
//            count++;
//            trackingMap.put("expiration", count);
//        } else {
//            map.put("expiration", list.getExpiration());
//            trackingMap.put("expiration", count);
//        }
//    }

    public String stringFormatter(Map map, HashMap tracking) {
        return null;
    }
}
