package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private String parsedValue;
    private int errorCounter;

    public List<Item> parseItemList(String valueToParse) {
        List<Item> list = new ArrayList<>();
        String[] splitValues = valueToParse.split("##");

    for (String value : splitValues) {
        try {
            list.add(parseSingleItem(value));
        } catch (ItemParseException e) {
            errorCounter++;
        }
    }

        return list;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {

        Map<String, String> map = new HashMap<>();
        Pattern separateKeyAndValue = Pattern.compile("[:@^%*]", Pattern.CASE_INSENSITIVE);
        Pattern separateKeyValue = Pattern.compile(";");
        Pattern separateObjects = Pattern.compile("##");
        Matcher matchObjects = separateKeyAndValue.matcher(singleItem);
        Matcher matchObjects1 = separateKeyValue.matcher(singleItem);
        Matcher matchObjects2 = separateObjects.matcher(singleItem);


        String stripped = (matchObjects1.replaceAll(" ").replace("##", " "));
        String[] pairs = stripped.split(" ");

            for (String pair : pairs) {
                String[] keyValue = pair.split("[:@^%*]", 2);
                if (keyValue.length != 2 || keyValue[0] == null || keyValue[1] == null || keyValue[0].trim().isEmpty() || keyValue[1].trim().isEmpty()) {
                    throw new ItemParseException();
                } else {
                    String key = keyValue[0].trim().toLowerCase();
                    String value = keyValue[1].trim().toLowerCase();
                    map.put(key, value);
                }
            }

//        for (String key : map.keySet()) {
//            System.out.println(key + "=" + map.get(key));
//        }

        Item item = new Item(map.get("name"), Double.parseDouble(map.get("price")), map.get("type"), map.get("expiration"));

        return item;
    }
}
