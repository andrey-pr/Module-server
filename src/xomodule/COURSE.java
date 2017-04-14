package xomodule;

import java.util.Arrays;

public class COURSE implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		RoomSqlManager rsm = RoomSqlManager.getInstance();
		Room r = rsm.get(str[3]);
		if(r == null)
			return;
		if(!r.next_course.equals(str[4]))
			return;
		int target = Integer.parseInt(str[2]);
		if(target > 9 || target < 1)
			return;
		target--;
		if(r.map[target/3][target%3] != ' ')
			return;
		NetworkManager nm = NetworkManager.getInstance();
		if(str[4].equals(r.first.id))
		{
			r.map[target/3][target%3] = 'x';
			r.next_course = r.second.id;
			nm.sendAuto("YOUR_COURSE", r.second.id);
		}
		else
		{
			r.map[target/3][target%3] = 'o';
			r.next_course = r.first.id;
			nm.sendAuto("YOUR_COURSE", r.first.id);
		}
		if(isWin(r, 'x'))
		{
			nm.sendAuto("WIN;", r.first.id);
			nm.sendAuto("LOSE;", r.second.id);
			rsm.remove(r);
			UserList.getInstance().users.register(r.first);
			UserList.getInstance().users.register(r.second);
		}
		else if(isWin(r, 'o'))
		{
			nm.sendAuto("LOSE;", r.first.id);
			nm.sendAuto("WIN;", r.second.id);
			rsm.remove(r);
			UserList.getInstance().users.register(r.first);
			UserList.getInstance().users.register(r.second);
		}
		else if(isDraw(r))
		{
			nm.sendAuto("DRAW;", r.first.id);
			nm.sendAuto("DRAW;", r.second.id);
		}
		nm.sendAuto("NEW_COURSE;"+target, r.first.id);
		nm.sendAuto("NEW_COURSE;"+target, r.second.id);
		System.out.println(Arrays.toString(r.map[0]));
		System.out.println(Arrays.toString(r.map[1]));
		System.out.println(Arrays.toString(r.map[2]));

	}
	
	private boolean isWin(Room r, char c)
	{
		return (r.map[0][0] == c && r.map[0][1] == c && r.map[0][2] == c) || (r.map[1][0] == c && r.map[1][1] == c && r.map[1][2] == c) || (r.map[2][0] == c && r.map[2][1] == c && r.map[2][2] == c) || (r.map[0][0] == c && r.map[1][0] == c && r.map[2][0] == c) || (r.map[0][1] == c && r.map[1][1] == c && r.map[2][1] == c) || (r.map[0][2] == c && r.map[1][2] == c && r.map[2][2] == c) || (r.map[0][0] == c && r.map[1][1] == c && r.map[2][2] == c) || (r.map[0][2] == c && r.map[1][1] == c && r.map[2][0] == c);

	}
	
	private boolean isDraw(Room r)
	{
		for(int i = 0; i < 3; i++)
			for(int a = 0; a < 3; a++)
				if(r.map[i][a] == ' ')
					return false;
		return true;

	}

}
