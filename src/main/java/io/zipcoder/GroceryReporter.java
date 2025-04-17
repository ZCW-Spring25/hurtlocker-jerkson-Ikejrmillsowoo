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
    List <String[]> things = new ArrayList<>();
    Pattern pattern = Pattern.compile("[:@^%*!]");


    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    public void parseList(){
        ItemParser parser = new ItemParser();
        List<Item> listOfStrings = new ArrayList<>(parser.parseItemList(this.originalFileText));
        for (Item list: listOfStrings) {
            String[] listItem = list.toString().split(" ");
//work here
            for (String itemStr : listItem) {
                Matcher matcher = pattern.matcher(itemStr);

                if (!matcher.matches()){
                    String[] arr = itemStr.split(":");
                        this.things.add(arr);
                }

            }
        }
//        System.out.println("here" + this.things);
//        System.out.println("ope" +this.things.get(0) + this.things.get(1));
    }
// will post item name and item in map and then use same keys to map on trackingMap
    // call each map for the string processing
    @Override
    public String toString() {
        int countName = 1;
        int countPrice = 1;
        parseList();

        for (String[] thing: things){
            //System.out.println(things.size());
            //String[] newThingPair = thing.replaceAll(" ","").split(",");
            System.out.println(Arrays.toString(thing));
          //  for (int i = 0; i < thing.size(); i++) {
            try{
                if (thing.length == 2) {
                    String key = thing[0].toLowerCase();
                    String value = thing[1];

                    if (key.equals("name")) {
                        map.put(key, value);
                        trackingMap.put(key, countName++);
                    }
                    if (key.equals("price")) {
                        map.put(key, value);
                        trackingMap.put(key, countPrice++);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }





        //System.out.println(map);
       // System.out.println(trackingMap);
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
