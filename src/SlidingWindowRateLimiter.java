import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowRateLimiter
{
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final Deque<Long> requestTimestamps;

    public SlidingWindowRateLimiter(int maxRequests, long windowSizeInMillis)
    {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.requestTimestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest()
    {
        long now = System.currentTimeMillis();

        // Remove timestamps outside the window
        while(!requestTimestamps.isEmpty() && now - requestTimestamps.peekFirst() > windowSizeInMillis)
        {
            requestTimestamps.pollFirst();
        }

        if(requestTimestamps.size() < maxRequests)
        {
            requestTimestamps.addLast(now);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        // Allow 5 requests per 2 seconds
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(5, 2000);

        for(int i = 1; i <= 10; i++)
        {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(300); // 300ms between requests
        }
    }
}
