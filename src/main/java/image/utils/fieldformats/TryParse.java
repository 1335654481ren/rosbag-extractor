package image.utils.fieldformats;


public class TryParse {

    public static Integer TryParseInt(String someText) {
        try {
            return Integer.parseInt(someText);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Double TryParseDouble(String someText) {
        try {
            return Double.parseDouble(someText);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}