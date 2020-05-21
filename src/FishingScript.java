import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.logging.*;

public class FishingScript implements NativeKeyListener
{
	private static boolean hasStarted;
	private static boolean ongoingThread;
	private static FishingBot bot;
	
	public static void main(String[] args)
	{	 
		// Text to Inform
		System.out.println("Welcome to the Minecraft AFK Fishing Script!");
		System.out.println("[`|~] = Exit out of Script");
		System.out.println("[I] = Start Script");
		System.out.println("[P] = Pause Script");
		System.out.println("[O] = UnPause Script");
		System.out.println("*YOU CAN IGNORE THE RED TEXT*");
		
		// Enable/Adds Native Hook
        try{
     		GlobalScreen.registerNativeHook();
     	}
     	catch (NativeHookException e){
     		e.printStackTrace();
     	}		
     	GlobalScreen.addNativeKeyListener(new FishingScript());
     	
     	// Turns off Mouse Console Display
     	Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
     	logger.setLevel(Level.OFF);
     	logger.setUseParentHandlers(false);
				
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		try
		{
			if(e.getKeyCode() == 41) // Graves Key [`|~]
			{
				System.out.println("\nFishingScript will now exit...");
				System.exit(0);
			}
			else if(e.getKeyCode() == 23 && hasStarted == false)
			{
				ongoingThread = true;
				// Timer to start
				System.out.print("Fishing will begin in...");
				for(int i = 3; i>0; i--)
				{
					try{
						Thread.sleep(1000);
					} catch (Exception n) {
						n.printStackTrace();
					}
					System.out.print(i+"...");
				}
				hasStarted = true;
				// Initialize robot
				bot = new FishingBot();
			}
			else if(e.getKeyCode() == 25 && ongoingThread == true)
			{
				//System.out.println("Being pressed....");
				bot.exit();
				ongoingThread = false;
			}
			else if(e.getKeyCode() == 24 && ongoingThread == false)
			{
				System.out.println("Thread will resume in: ");
				for(int i = 3; i>0; i--)
				{
					System.out.print(i + "...");
					try {
						Thread.sleep(1000);
					} catch(Exception n) {}
				}
				
				bot = new FishingBot();
				ongoingThread = true;
			}	
		}catch (Exception n) {
			System.out.println("Bruh wait for it to load...");
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e)
	{}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e)
	{}
}
