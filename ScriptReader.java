import java.io.*;

class ScriptReader implements Runnable
{
	private File f;
	private Thread th;
	private java.util.Scanner r;
	private boolean running = false;
	public ScriptReader(String args)
	{
		r = new java.util.Scanner(System.in);
		f = new File(args);

		running = true;
		th = new Thread(this);
		th.start();
	}	

	@Override
	public void run()
	{
		try{	
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s = "";
			while(running)
			{
				if((s = br.readLine())!=null)
				{
					if(!s.equals(""))
					{
						printCharacters(s);
						String a = r.nextLine();
						if(a.equals("#"))
							stop();
					}
				}
				else
				{
					stop();
				}
			}
			br.close();
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		}
	}

	private void stop()
	{
		running = false;
	}

	private synchronized void printCharacters(String s)
	{
		for(int i = 0;i<s.length();i++)
		{
			System.out.print(s.charAt(i));

			try{Thread.sleep(70);}catch(Exception ex){ex.printStackTrace();}
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		new ScriptReader(args[0]);
	}
}