package xomodule;

public class GET_USERS implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		StringBuffer sb = new StringBuffer("GET_USERS;");
		UserList.getInstance().users.all().forEach((r)->{sb.append(r.login);sb.append(";");});
		NetworkManager.getInstance().sendAuto(sb.toString(), command.split("[;]")[3]);
		
	}

}
