public class ThreadExamples
{
    public static void main(String[] args)
    {
        Runnable task = () -> {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Executing : " + Thread.currentThread().getName());
        };

        for (int idx = 0; idx < 6; idx++)
        {
            Thread test = new Thread(task);
            test.start();
        }
        Thread test_something = new Thread(() -> { System.out.println(Thread.currentThread().getName()); });
        test_something.start();
    }
}
