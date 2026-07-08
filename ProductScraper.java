package Task4;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProductScraper {

    public static void main(String[] args) {

        String inputFile = "products.txt";
        String outputFile = "products.csv";
        String tableFile = "products_table.txt";

        String[] names = new String[100];
        String[] prices = new String[100];
        String[] ratings = new String[100];
        int count = 0;

        try {
            Scanner fileScanner = new Scanner(new File(inputFile));

            String name = "";
            String price = "";
            String rating = "";

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                if (line.startsWith("Name:")) {
                    name = getValue(line);
                } else if (line.startsWith("Price:")) {
                    price = getValue(line);
                } else if (line.startsWith("Rating:")) {
                    rating = getValue(line);

                    names[count] = name;
                    prices[count] = price;
                    ratings[count] = rating;
                    count++;
                }
            }
            fileScanner.close();

            String table = buildTable(names, prices, ratings, count);

            System.out.println(table);

            PrintWriter tableWriter = new PrintWriter(tableFile, "UTF-8");
            tableWriter.write(table);
            tableWriter.close();

            writeCsv(outputFile, names, prices, ratings, count);

            System.out.println("\nSaved " + count + " products to "
                    + outputFile + " and " + tableFile);

        } catch (IOException e) {
            System.out.println("Could not read/write the file: " + e.getMessage());
        }
    }

    static String buildTable(String[] names, String[] prices, String[] ratings, int count) {
        String line = "+---------------------------------------+----------+--------+";

        String table = "";
        table += line + "\n";
        table += String.format("| %-37s | %-8s | %-6s |%n", "Name", "Price", "Rating");
        table += line + "\n";

        for (int i = 0; i < count; i++) {
            table += String.format("| %-37s | %-8s | %-6s |%n",
                    fit(names[i], 37), "₹" + prices[i], ratings[i]);
        }

        table += line + "\n";
        return table;
    }

    static String fit(String text, int width) {
        if (text.length() <= width) {
            return text;
        }
        return text.substring(0, width - 3) + "...";
    }

    static void writeCsv(String file, String[] names, String[] prices,
                         String[] ratings, int count) throws IOException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.write("Name,Price,Rating\n");
        for (int i = 0; i < count; i++) {
            writer.write(escape(names[i]) + ",₹" + prices[i] + "," + ratings[i] + "\n");
        }
        writer.close();
    }

    static String getValue(String line) {
        int colon = line.indexOf(":");
        return line.substring(colon + 1).trim();
    }

    static String escape(String value) {
        if (value.contains(",")) {
            return "\"" + value + "\"";
        }
        return value;
    }
}
