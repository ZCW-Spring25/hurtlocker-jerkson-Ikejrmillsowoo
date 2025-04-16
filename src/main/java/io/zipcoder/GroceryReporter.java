package io.zipcoder;


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
        List<String> listOfStrings1 = new ArrayList<>();
        for (Item list: listOfStrings) {
            String[] listItem = list.toString().split(" ");
            for (String item : listItem) {
                String[] thing = (item.split(":"));


           if (thing[0].equals("name")) {
                map.put(thing[0], thing[1]);
                count++;
                trackingMap.put(thing[0], count);
            }
                if (thing[0].equals("price")) {
                    map.put(thing[0], thing[1]);
                    count++;
                    trackingMap.put(thing[0], count);
                }

//            else {
//                map.put(thing[0], thing[1]);
//                trackingMap.put(thing[0], count);
//            }
//            if (map.get(thing[0]).equals(thing[1])) {
//                map.put(thing[0], thing[1]);
//                count++;
//                trackingMap.put(thing[0], count);
//            } else {
//                map.put(thing[0], thing[1]);
//                trackingMap.put(thing[0], count);
//            }
//            if (map.get(thing[0]).equals(thing[1])) {
//                map.put(thing[0], thing[1]);
//                count++;
//                trackingMap.put(thing[0], count);
//            } else {
//                map.put(thing[0], thing[1]);
//                trackingMap.put(thing[0], count);
//            }
//            if (map.get(thing[0]).equals(thing[1])) {
//                map.put(thing[0], thing[1]);
//                count++;
//                trackingMap.put(thing[0], count);
//            } else {
//                map.put(thing[0], thing[1]);
//                trackingMap.put(thing[0], count);
//            }
        }
        }
        System.out.println(map.toString());
        System.out.println(trackingMap.toString());
        //stringFormatter(map, trackingMap);
        return stringFormatter(map, trackingMap);
    }


    public String stringFormatter(Map regMap, Map tracking) {
        String answer ="";

        for (int i = 0; i < map.size(); i++) {

            answer = ("\n" +
                    "name:  " + map.get("name") + "\t\t seen:  " + tracking.get("name") + "  times\n" +
                    "============= \t \t =============\n" +
                    "Price:  " + map.get("price") + "\t\t seen:  " + tracking.get("name") + "   times\n" +
                    "-------------\t\t -------------\n" +
                    "Price:  " + map.get("price") + "\t\t seen:  " + tracking.get("name") + "   times");
        }


        return answer;
    }
}
