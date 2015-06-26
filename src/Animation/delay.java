package Animation;

public class delay {
	public delay(int x)
	{
		try {
			Thread.currentThread();
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
