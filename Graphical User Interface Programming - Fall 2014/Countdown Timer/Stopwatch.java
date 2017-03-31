public class Stopwatch
{
    private long lastUpdateTime;
    private long totalRunningTime;
    
    private int state;
    
    public static final int DEFAULT = 0;
    public static final int RUNNING = 1;
    public static final int PAUSED  = 2;
    
    // constructor
    public Stopwatch()
    {
        
    }
    
    
    
    public void pause()
    {
        state = PAUSED;
    }
    
    public void resume()
    {
        state = RUNNING;
    }
   
    public void reset()
    {
        state = DEFAULT;
        totalRunningTime = 0;
        lastUpdateTime = System.currentTimeMillis();
    }
    
    public void update(long currentTime)
    {
        long elapsedTime = currentTime - lastUpdateTime;
        
        if (state == RUNNING)
            totalRunningTime += elapsedTime;
            
        lastUpdateTime = currentTime;
    }
    
    public long getRunningTime()
    {
        return totalRunningTime;
    }
    
    public int getState()
    {
        return state;
    }
    

}