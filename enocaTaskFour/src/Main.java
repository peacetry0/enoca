import java.util.*;

public class Main {


    public static void main(String[] args) {

        List<Integer> originalList = randomList(100);

        List<Integer> copyList = new ArrayList<>(originalList);

        System.out.println("Original List: " + originalList);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choose a number from the list :");

        int chooseNumber = scanner.nextInt();

        System.out.println("Choose Number : " + chooseNumber);

        copyList.remove(Integer.valueOf(chooseNumber));


        int missNumber = findMissingNumber(originalList, copyList);

        System.out.println("Missing Number: " + missNumber);
    }

    public static List<Integer> randomList(int size) {

        Random random = new Random();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(101));
        }
        return list;
    }

    public static int findMissingNumber(List<Integer> originalList, List<Integer> copyList) {

        Map<Integer, Integer> numberMap = new HashMap<>();

        for (int number : originalList) {

            numberMap.put(number, numberMap.getOrDefault(number, 0) + 1 );
        }

        for (int number : copyList) {
            if (numberMap.containsKey(number)) {
                numberMap.put(number, numberMap.get(number) - 1);
            }
        }


        for (Map.Entry<Integer, Integer> entry : numberMap.entrySet()) {
            if (entry.getValue() > 0) {
                return entry.getKey();
            }
        }

        return -1;
    }
}