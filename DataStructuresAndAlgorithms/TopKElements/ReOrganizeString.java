import java.util.*;

public class ReOrganizeString {
        public static void main(String[] args) {

       String[] inputs = {
            "preplanning",
            "hello",
            "fofjjb",
            "axxaxdde",
            "aba",
            "",
            "aaab",
            "abb"
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput string: \"" + inputs[i] + "\"");

            String output = reorganizeString(inputs[i]);
            output = (output.length() == 0) ? "''" : output;

            System.out.println("\tReorganized string: \"" + output + "\"");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
	}

    		public static String reorganizeString(String string1) {
      //Create Map of Strings and their Frequency (This was my first thought)
      // HashMap<String, Integer> characterFrequencyMap = new Hashmap();

      // for(int i = 0; i < string1.length(), i++){
      //   String currentChar = string1.getChar(i);
      //   if (characterFrequencyMap.get(currentChar)) == null) {
      //     characterFrequencyMap.add(currentChar, 1);
      //   } else {
      //     charFrequencyMap.add(currentChar, 
      //       characterFrequencyMap.get(currentChar) +1);
      //   }
      // }

      // I realized later I could do like so:
      HashMap<Character, Integer> characterFrequencyMap = new HashMap<Character, Integer>();
      for(char c: string1.toCharArray()) {
        int freq = characterFrequencyMap.getOrDefault(c, 0 ) + 1;
        characterFrequencyMap.put(c, freq);
      }

      //Create a MaxHeap using the frequency data for the characters
      PriorityQueue<Map.Entry <Character, Integer>> maxFreqChars = 
        new PriorityQueue<Map.Entry<Character, Integer>> (
          (a, b) -> b.getValue() - a.getValue());

      maxFreqChars.addAll(characterFrequencyMap.entrySet());

      Map.Entry<Character, Integer> previous = null;
      StringBuilder result = new StringBuilder(string1.length());

      while (!maxFreqChars.isEmpty() || previous != null) {
        //Can't make a reorganized String
        if (maxFreqChars.isEmpty() && previous != null) {
          return "";
        }
        Map.Entry<Character, Integer> current = maxFreqChars.poll();
        result.append(current.getKey());
        int currentFreq = current.getValue() - 1;

        if (previous != null) {
            maxFreqChars.add(previous);
            previous = null;
        }
        if (currentFreq != 0) {
          previous = new AbstractMap.SimpleEntry<>(current.getKey(), currentFreq);

        }
      }

        return result.toString();
    }
}
