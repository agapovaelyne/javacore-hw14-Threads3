import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] someArr = new int[size];
        for (int i = 0; i < someArr.length; i++) {
            someArr[i] = Math.abs(rand.nextInt(1000));
        }
        return someArr;
    }

    public static String oneThreadCountMethod(int[] arrayToCount) {
        int sum = 0;
        for (int el : arrayToCount) {
            sum += el;
        }
        int average = sum / arrayToCount.length;
        return String.format("Сумма элементов массива: %d\nСреднее арифметическое: %d", sum, average);
    }

    public static String doubleForkCountMethod(int[] arrayToCount) {
        Integer sum = new ForkJoinPool().invoke(new ArrayDoubleForkSumTask(arrayToCount, 0, arrayToCount.length));
        int average = sum / arrayToCount.length;
        return String.format("Сумма элементов массива: %d\nСреднее арифметическое: %d", sum, average);
    }

    public static String fourEndsForkCountMethod(int[] arrayToCount) {
        Integer sum = new ForkJoinPool().invoke(new ArrayFourEndsForkSumTask(arrayToCount, 0, arrayToCount.length));
        int average = sum / arrayToCount.length;
        return String.format("Сумма элементов массива: %d\nСреднее арифметическое: %d", sum, average);
    }

    public static void main(String[] args) {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("HH:mm:ss:SS");

        System.out.println("1.Большой массив");
        int[] arrayToCountLarge = generateArray(10000);

        System.out.println("Вычисление одним потоком:");
        Date dateTimeStart11 = new Date();
        System.out.println("Начало выполнения: " + formatForDateNow.format(dateTimeStart11));
        System.out.println(oneThreadCountMethod(arrayToCountLarge));
        Date dateTimeEnd11 = new Date();
        System.out.println("Конец выполнения: " + formatForDateNow.format(dateTimeEnd11));
        long diff11 = dateTimeEnd11.getTime() - dateTimeStart11.getTime();
        System.out.println("Время выполнения в миллисекундах: " + diff11);

        System.out.println("\nВычисление c рекурсивным делением на 2:");
        Date dateTimeStart12 = new Date();
        System.out.println("Начало выполнения: " + formatForDateNow.format(dateTimeStart12));
        System.out.println(doubleForkCountMethod(arrayToCountLarge));
        Date dateTimeEnd12 = new Date();
        System.out.println("Конец выполнения: " + formatForDateNow.format(dateTimeEnd12));
        long diff12 = dateTimeEnd12.getTime() - dateTimeStart12.getTime();
        System.out.println("Время выполнения в миллисекундах: " + diff12);

        System.out.println("\nВычисление c рекурсивным делением на 4:");
        Date dateTimeStart13 = new Date();
        System.out.println("Начало выполнения: " + formatForDateNow.format(dateTimeStart13));
        System.out.println(fourEndsForkCountMethod(arrayToCountLarge));
        Date dateTimeEnd13 = new Date();
        System.out.println("Конец выполнения: " + formatForDateNow.format(dateTimeEnd13));
        long diff13 = dateTimeEnd13.getTime() - dateTimeStart13.getTime();
        System.out.println("Время выполнения в миллисекундах: " + diff13);

        System.out.println("___________________________________________");

        System.out.println("2.Маленький массив");
        int[] arrayToCountSmall = generateArray(50);

        //System.currentTimeMillis()
        System.out.println("Вычисление одним потоком:");
        Date dateTimeStart21 = new Date();
        System.out.println("Начало выполнения: " + formatForDateNow.format(dateTimeStart21));
        System.out.println(oneThreadCountMethod(arrayToCountSmall));
        Date dateTimeEnd21 = new Date();
        System.out.println("Конец выполнения: " + formatForDateNow.format(dateTimeEnd21));
        long diff21 = dateTimeEnd21.getTime() - dateTimeStart21.getTime();
        System.out.println("Время выполнения в миллисекундах: " + diff21);

        System.out.println("\nВычисление c рекурсивным делением на 2:");
        Date dateTimeStart22 = new Date();
        System.out.println("Начало выполнения: " + formatForDateNow.format(dateTimeStart22));
        System.out.println(doubleForkCountMethod(arrayToCountSmall));
        Date dateTimeEnd22 = new Date();
        System.out.println("Конец выполнения: " + formatForDateNow.format(dateTimeEnd22));
        long diff22 = dateTimeEnd22.getTime() - dateTimeStart22.getTime();
        System.out.println("Время выполнения в миллисекундах: " + diff22);

        System.out.println("\nВычисление c рекурсивным делением на 4:");
        Date dateTimeStart23 = new Date();
        System.out.println("Начало выполнения: " + formatForDateNow.format(dateTimeStart23));
        System.out.println(fourEndsForkCountMethod(arrayToCountSmall));
        Date dateTimeEnd23 = new Date();
        System.out.println("Конец выполнения: " + formatForDateNow.format(dateTimeEnd13));
        long diff23 = dateTimeEnd23.getTime() - dateTimeStart23.getTime();
        System.out.println("Время выполнения в миллисекундах: " + diff23);

    }
}
