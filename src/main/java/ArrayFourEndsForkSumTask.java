import java.util.concurrent.RecursiveTask;

public class ArrayFourEndsForkSumTask extends RecursiveTask<Integer> {
    private int[] arr;
    private int start, end;

    public ArrayFourEndsForkSumTask(int[] arrayToCount, int start, int end) {
        arr = arrayToCount;
        this.start = start;
        this.end = end;
    }

    private int forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        final int firstQuarter = (middle - start) / 2 + start;
        final int thirdQuarter = (end - middle) / 2 + middle;
        ArrayFourEndsForkSumTask firstQuarterTask = new ArrayFourEndsForkSumTask(arr, start, firstQuarter);
        ArrayFourEndsForkSumTask secondQuarterTask = new ArrayFourEndsForkSumTask(arr, firstQuarter, middle);
        ArrayFourEndsForkSumTask thirdQuarterTask = new ArrayFourEndsForkSumTask(arr, middle, thirdQuarter);
        ArrayFourEndsForkSumTask fourthQuarterTask = new ArrayFourEndsForkSumTask(arr, thirdQuarter, end);
        invokeAll(firstQuarterTask, secondQuarterTask, thirdQuarterTask, fourthQuarterTask);
        return firstQuarterTask.join() + secondQuarterTask.join() + thirdQuarterTask.join() + fourthQuarterTask.join();
    }


    @Override
    public Integer compute() {
        final int diff = end - start;
        switch (diff) {
            case (0):
                return 0;
            case (1):
                return arr[start];
            case (2):
                return arr[start] + arr[start + 1];
            default:
                return forkTasksAndGetResult();
        }
    }
}
