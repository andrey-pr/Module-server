package xomodule;

public class EXIT implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		UserList.getInstance().users.remove(UserList.getInstance().users.getById(str[3]));
		NetworkManager nm = NetworkManager.getInstance();
		for(Room r: RoomSqlManager.getInstance())
			if(r.first.id == str[3])
			{
				nm.sendAuto("OPPONENT_EXIT", r.second.id);
				RoomSqlManager.getInstance().remove(r);
				return;
			}
			else if(r.second.id == str[3])
			{
				nm.sendAuto("OPPONENT_EXIT", r.first.id);
				RoomSqlManager.getInstance().remove(r);
				return;
			}
		
	}

}
