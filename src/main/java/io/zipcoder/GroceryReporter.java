package io.zipcoder;


import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroceryReporter {
    private final String originalFileText;

    Map<String, String> map = new HashMap<>();
    Map<String, Integer> trackingMap = new HashMap<>();
    String[] things;
    Pattern pattern = Pattern.compile("[:@^%*]");


    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    public void parseList(){
        ItemParser parser = new ItemParser();
        List<Item> listOfStrings = new ArrayList<>(parser.parseItemList(this.originalFileText));
        for (Item list: listOfStrings) {
            String[] listItem = list.toString().split(" ");

            for (String itemStr : listItem) {
                Matcher matcher = pattern.matcher(itemStr);
                if (matcher.find()){
                        this.things = (itemStr.split(":"));
                }

            }
        }System.out.println("here" + Arrays.toString(this.things));
        System.out.println(this.things[0] + this.things[1]);
    }
// will post item name and item in map and then use same keys to map on trackingMap
    // call each map for the string processing
    @Override
    public String toString() {
        int countName = 1;
        int countPrice = 1;
        parseList();
       // ItemParser parser = new ItemParser();
        //List<Item> listOfStrings = new ArrayList<>(parser.parseItemList(this.originalFileText));
       // for (Item list: listOfStrings) {
         //   String[] listItem = list.toString().split(" ");
          //  for (String itemStr : listItem) {
                //String[] things = (itemStr.split(":"));


                if (things[0].equals("name")) {
                    map.put(things[0], things[1]);
                    countName++;
                    trackingMap.put(things[0], countName);
                }
                if (things[0].equals("price")) {
                    map.put(things[0], things[1]);
                    countPrice++;
                    trackingMap.put(things[0], countPrice);
                }




       // System.out.println(map.toString());
        //System.out.println(trackingMap.toString());
        //stringFormatter(map, trackingMap);
        return stringFormatter(map, trackingMap);
    }


    public String stringFormatter(Map map, Map tracking) {
        String answer ="";

        for (int i = 0; i < map.size(); i++) {

            answer = ("\n" +
                    "name:  " + map.get("name") + "\t\t seen:  " + tracking.get("name") + "  times\n" +
                    "============= \t \t =============\n" +
                    "Price:  " + map.get("price") + "\t\t seen:  " + tracking.get("name") + "   times\n" +
                    "-------------\t\t -------------\n" +
                    "Price:  " + map.get("price") + "\t\t seen:  " + tracking.get("name") + "   times");
        }

        System.out.println("Answer" + answer);
        return answer;
    }
}
