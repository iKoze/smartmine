package eu.floriware.minecraft.Smartmine;

public class Association
{
	private String list [][];
	private String empty;
	private int maxCount;
	
	public Association (int maxContents, String emptyContent)
	{
		list = new String[maxContents][2];
		empty = emptyContent;
		maxCount = maxContents;
		for (int i = 0; i < maxCount; i++)
		{
			list[i][0] = empty;
			list[i][1] = empty;
		}
	}
	
	public int newAssociation (String identifier, String content)
	{
		// Überprüfen, ob identifier nicht schon mal benuzt wurde
		if(getIDByIdentifier(identifier) != -1){return -1;}
		int isEmpty = findEmpty();
		if(isEmpty == -1){return isEmpty;}
		list[isEmpty][0]=identifier;
		list[isEmpty][1]=content;
		// gibt ID der Assoziation zurück
		return isEmpty;
	}
	
	public boolean delAssociationByIdentifier (String identifier)
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][0].equals(identifier))
			{
				list[i][0]=empty;
				list[i][1]=empty;
				return true;
			}
		}
		return false;
	}
	
	public boolean delAssociationByContent (String content)
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][1].equals(content))
			{
				list[i][0]=empty;
				list[i][1]=empty;
				return true;
			}
		}
		return false;
	}
	
	public boolean delAssociationByID (int i)
	{
				list[i][0]=empty;
				list[i][1]=empty;
				return true;
	}
	
	public String getContentByIdentifier(String Identifier)
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][0].equals(Identifier))
			{
				return list[i][1];
			}
		}
		return empty;
	}
	
	public String getIdentifierByContent(String Content)
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][1].equals(Content))
			{
				return list[i][0];
			}
		}
		return empty;
	}
	
	public int getIDByIdentifier(String Identifier)
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][0].equals(Identifier))
			{
				return i;
			}
		}
		return -1;
	}
	
	public int getIDByContent(String Content)
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][1].equals(Content))
			{
				return i;
			}
		}
		return -1;
	}
	
	public String getContentByID(int i)
	{
		return list[i][1];
	}
	
	public String getIdentifierByID(int i)
	{
		return list[i][0];
	}
	
	private int findEmpty()
	{
		for (int i = 0; i < maxCount; i++)
		{
			if(list[i][0].equals(empty) && list[i][1].equals(empty))
			{
				return i;
			}
		}
		return -1;
	}
}
