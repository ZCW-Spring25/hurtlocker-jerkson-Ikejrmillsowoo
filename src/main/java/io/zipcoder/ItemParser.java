package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private String parsedValue;

    public List<Item> parseItemList(String valueToParse) throws ItemParseException {
        List<Item> list = new ArrayList<>();
        String[] splitValues = valueToParse.split("##");

            for (String value : splitValues) {
                    list.add(parseSingleItem(value));
            }
        //loop through the given string
        //separate it into objects and key value pairs
        //any key missing a value should throw and excpetion.
        //add item to list.

        return list;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {

        Map<String, String> map = new HashMap<>();
        Pattern separateKeyAndValue = Pattern.compile("[:@^%]", Pattern.CASE_INSENSITIVE);
        Pattern separateKeyValue = Pattern.compile(";");
        Pattern separateObjects = Pattern.compile("##");
        Matcher matchObjects = separateKeyAndValue.matcher(singleItem);
        Matcher matchObjects1 = separateKeyValue.matcher(singleItem);
        Matcher matchObjects2 = separateObjects.matcher(singleItem);

        try{
//            if (!singleItem.contains(";")){
//                throw new ItemParseException();
//            } else {
                String stripped = (matchObjects1.replaceAll(" ").replace("##", " "));
                String[] pairs = stripped.split(" ");
                //System.out.println(Arrays.toString(pairs));
                for (String pair : pairs) {
                    String[] keyValue = pair.split("[:@^%]", 2);
                    if (keyValue.length != 2) {
                        throw new ItemParseException();
                    }

                    map.put(keyValue[0].toLowerCase(), keyValue[1].toLowerCase());
                }

        } catch (ItemParseException e){
            throw new ItemParseException();
        }

//        for (String key : map.keySet()) {
//            System.out.println(key + "=" + map.get(key));
//        }

        Item item = new Item(map.get("name"), Double.parseDouble(map.get("price")), map.get("type"), map.get("expiration"));

        //Pattern pattern = Pattern
        //loop through the given string
        //separate it into objects and key value pairs
        //any key missing a value should throw and excpetion.
        //return single item parsed.
        return item;
    }
}
