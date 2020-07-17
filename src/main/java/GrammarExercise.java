import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrammarExercise {

    private static final Pattern REGEX_PATTERN_OF_INVALID_INPUT = Pattern.compile("[^A-Za-z,]|,{2}");

    public static void main(String[] args) {

        String firstWordList = args[0];
        String secondWordList = args[1];

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        result.forEach(System.out::println);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {

        // Throw an exception if the two strings contain any invalid character
        if (REGEX_PATTERN_OF_INVALID_INPUT.matcher(firstWordList).find()
                || REGEX_PATTERN_OF_INVALID_INPUT.matcher(secondWordList).find()) {
            throw new RuntimeException("input not valid");
        }

        // Split string into array by comma
        List<String> arrayOfFirstWordList = Arrays.asList(firstWordList.toUpperCase().split(","));
        List<String> arrayOfSecondWordList = Arrays.asList(secondWordList.toUpperCase().split(","));

        return arrayOfFirstWordList.stream()
                // Discard repeated elements
                .distinct()
                // Filter those elements in first word list but not exists in the second one
                .filter(arrayOfSecondWordList::contains)
                .sorted()
                // Add a space between every two characters, and remove the space at the head or tail
                .map(s -> s.replace("", " ").trim())
                // Covert Stream to List
                .collect(Collectors.toList());
    }
}
