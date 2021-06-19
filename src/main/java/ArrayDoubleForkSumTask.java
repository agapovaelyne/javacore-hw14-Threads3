import java.util.concurrent.RecursiveTask;

public class ArrayDoubleForkSumTask extends RecursiveTask<Integer> {
    private int[] arr;
    private int start, end;


    public ArrayDoubleForkSumTask(int[] arrayToCount, int start, int end) {
        arr = arrayToCount;
        this.start = start;
        this.end = end;
    }

    private int forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        ArrayDoubleForkSumTask taskForLeft = new ArrayDoubleForkSumTask(arr, start, middle);
        ArrayDoubleForkSumTask taskForRight = new ArrayDoubleForkSumTask(arr, middle, end);
        invokeAll(taskForLeft, taskForRight);
        return taskForLeft.join() + taskForRight.join();
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
