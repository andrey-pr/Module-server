package chatmodule;

import java.io.IOException;

public class Listener implements Runnable
{

	public static void main(String[] args)
	{
		new Thread(new Listener()).start();

	}

	@Override
	public void run()
	{
		NetworkManager net = NetworkManager.getInstance();
		MainDispatcher md = MainDispatcher.getInstance();
		try
		{
			net.dos.writeUTF("Registration;CHAT;qwerty;v1.0;");
			net.dos.flush();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)
		{
			try
			{
				String str = net.dis.readUTF();
				System.out.println(str);
				if(!str.equals("ok"))
						md.command.add(str);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
