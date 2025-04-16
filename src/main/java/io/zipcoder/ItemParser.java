package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private int errorCounter;

    public List<Item> parseItemList(String valueToParse) {
        List<Item> list = new ArrayList<>();
        String[] splitValues = valueToParse.split("##");

    for (String value : splitValues) {
        try {
            list.add(parseSingleItem(value));
        } catch (ItemParseException e) {
            this.errorCounter++;
        }
    }

        return list;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {

        Map<String, String> map = new HashMap<>();
        Pattern splitObj = Pattern.compile("[:@^%*]");


        String stripped = (singleItem.replaceAll("##", " ").replace(";", " "));
        String[] pairs = stripped.split("\\s+");

            for (String pair : pairs) {
                if (pair.trim().isEmpty()) continue;
                Matcher matchSplit = splitObj.matcher(pair);
                if (matchSplit.find()) {
                    String[] keyValue = pair.split("[:@^%*]", 2);
                    if (keyValue.length != 2 || keyValue[0] == null || keyValue[1] == null || keyValue[0].trim().isEmpty() || keyValue[1].trim().isEmpty()) {
                        throw new ItemParseException();
                    }

                        String key = keyValue[0].trim().toLowerCase();
                        String value = keyValue[1].trim().toLowerCase();
                        map.put(key, value);
                    } else {
                    throw new ItemParseException();
                }
            }


try {
   return new Item(map.get("name"), Double.parseDouble(map.get("price")), map.get("type"), map.get("expiration"));
} catch (Exception e){
    throw new ItemParseException();
}
    }
}
