import java.text.DecimalFormat;

public class TokenBucketRateLimiter
{
    private final int maxTokens;
    private final double refillRatePerSecond;
    private double availableTokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(int maxTokens, double refillRatePerSecond)
    {
        this.maxTokens = maxTokens;
        this.refillRatePerSecond = refillRatePerSecond;
        this.availableTokens = maxTokens;
        this.lastRefillTimestamp = System.nanoTime();
    }

    // Synchronized to make it thread-safe
    public synchronized boolean allowRequest()
    {
        refill();

        if(availableTokens >= 1)
        {
            availableTokens -= 1;
            return true;
        }
        else
        {
            return false;
        }
    }

    private void refill()
    {
        long now = System.nanoTime();
        double secondsSinceLast = (now - lastRefillTimestamp) / 1_000_000_000.0;
        //        DecimalFormat df = new DecimalFormat("#.###########");
        //        System.out.println(df.format(secondsSinceLast));
        if(secondsSinceLast > 1)
        {
            availableTokens = maxTokens;
            lastRefillTimestamp = now;
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 5); // 5 requests per second

        for(int i = 0; i < 10; i++)
        {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(100); // simulate 100ms between requests
        }
    }
}

