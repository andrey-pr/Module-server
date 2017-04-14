package xomodule;

public class GAME_RESPONSE implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		UserList ul = UserList.getInstance();
		User u = ul.users.get(str[2]);
		User user = ul.users.getById(str[4]);
		if(u == null)
			return;
		if(!u.lastreq.equals(UserList.getInstance().users.getById(str[4]).login))
			return;
		if(str[3].equals("0"))
		{
			u.lastreq = "";
			return;
		}
		RoomSqlManager.getInstance().register(
				new Room(u.id
						+user.id,
						u, user));
		ul.users.remove(u);
		ul.users.remove(user);
		NetworkManager nm = NetworkManager.getInstance();
		nm.sendAuto("NEW_ROOM;" + u.id+user.id, u.id);
		nm.sendAuto("NEW_ROOM;" + u.id+user.id, user.id);
		
	}

}
