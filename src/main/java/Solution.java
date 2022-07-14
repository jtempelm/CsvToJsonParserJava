/*
 * Click `Run` to execute the snippet below!
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    final static String COMMA_DELIMITER = ",";
    final static String FILENAME = "input.csv";
    final static String ATTR_PREFIX = "attr_";
    final static String ATTR_TRUE = "TRUE";
    final static String ATTRS = "attrs";

    public static void main(String[] args) {

        final List<String> headers = new ArrayList<>();
        final List<List<String>> rows = new ArrayList<>();
        List<ObjectNode> allDocuments = new ArrayList<ObjectNode>();

        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            headers.addAll(getRecordFromLine(scanner.nextLine()));

            while (scanner.hasNextLine()) {
                rows.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        for (List<String> row : rows) {
            ObjectNode document = mapper.createObjectNode();

            for (int i = 0; i < headers.size(); i++) {
                String columnHeader = headers.get(i);
                String field = row.get(i);
                if (columnHeader.lastIndexOf(ATTR_PREFIX) != -1 && field.equals(ATTR_TRUE)) {
                    final ArrayNode attrs;
                    if (document.get("attrs") == null) {
                        attrs = mapper.createArrayNode();
                        document.set(ATTRS, attrs);
                    } else {
                        attrs = (ArrayNode) document.get("attrs");
                    }
                    attrs.add(
                        columnHeader.split(ATTR_PREFIX)[1]
                    );
                } else if (columnHeader.lastIndexOf(ATTR_PREFIX) == -1) {
                    document.put(headers.get(i), row.get(i));
                }
            }

            allDocuments.add(document);
        }

        System.out.println(allDocuments);
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
