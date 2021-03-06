package xomodule;

import java.util.ArrayList;

public class UserSqlManager
{
	private ArrayList<User> mock = new ArrayList<User>();
	
	public User get(String s)
	{
		for(int i = 0; i < mock.size(); i++)
			if(mock.get(i).login.equals(s))
				return mock.get(i);
		return null;
	}
	
	public boolean isExist(String login)
	{
		if(get(login) != null)
			return true;
		return false;
	}
	
	public String register(User m)
	{
		m.login = m.login.trim();
		if(!isExist(m.login))
			mock.add(m);
		else
		{
			int i = 0;
			while(isExist(m.login+i))
				i++;
			m.login+=i;
			mock.add(m);
		}
		return m.login;
	}

	public void show()
	{
		mock.forEach((m)->{System.out.println("\"" + m.login + "\"");});
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> all()
	{
		return (ArrayList<User>) mock.clone();
	}

	public void remove(User u)
	{
		mock.remove(u);
		
	}

	public User getById(String s)
	{
		for(int i = 0; i < mock.size(); i++)
			if(mock.get(i).id.equals(s))
				return mock.get(i);
		return null;
	}
}
