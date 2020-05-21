import java.awt.Robot;
import java.awt.event.InputEvent;


public class FishingBot extends Thread 
{
    private boolean isRunning;
    private boolean isSleeping;
    private Robot bot;

    public FishingBot()
    {
        // Initialize private variables
        isRunning = true;
        isSleeping = false;
        
        try{
            bot = new Robot();
        }
        catch(Exception e){
            e.printStackTrace();
        }
          
     	this.start();
    }
    
    public void exit()
    {
    	while(true)
    	{
    		// Buffer for Inputs
    		try{
    			Thread.sleep(50);
    		} catch(Exception e) {}
    		
    		// Checks Sleeping Status to Avoid Sleep Error
    		if (!isSleeping)
    		{	
        		isRunning = false;
        		break;
			}
    	}
    	bot.mouseRelease(InputEvent.BUTTON3_MASK);
    	System.out.println("\nFishing Script Paused...");
	}
     
    @Override
    public void run()
    {
        while(isRunning)
        {
        	bot.mousePress(InputEvent.BUTTON3_MASK);
	
	        try{
	        	isSleeping = true;
	            Thread.sleep(100);
	            isSleeping = false;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
        }    
    }
}