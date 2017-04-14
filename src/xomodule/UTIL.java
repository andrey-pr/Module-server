package xomodule;

import java.util.ArrayList;

public class UTIL
{
	public void sendAll(String str)
	{
		ArrayList<User> al = UserList.getInstance().users.all();
		NetworkManager md = NetworkManager.getInstance();
		for(int i = 0; i < al.size(); i++)
			md.sendAuto(str, al.get(i).id);
	
	}
}
