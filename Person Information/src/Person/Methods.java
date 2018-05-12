package Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Methods {
	public enum Title
	{
		Mr,Mrs,Miss,Ms
	}
	//declare variables
	String FirstName,LastName,Title,Email,Age,Phone,StrTest;
	int IntTest;
	//ReadJsonFile Reads a JSON file and prints to screen the file
	public void ReadJsonFile()
	{
		JSONParser parser = new JSONParser();
		BufferedReader br = null;
		try {
			int counter=1;
			String sCurrentLine;
			br = new BufferedReader(new FileReader("myJSON.json"));
			while ((sCurrentLine = br.readLine()) != null) 
			{
				Object obj = parser.parse(sCurrentLine);
				JSONObject jsonobj = (JSONObject) obj;
				System.out.println("Record "+counter+"\t" + sCurrentLine);
				FirstName = (String) jsonobj.get("FirstName");
				LastName = (String) jsonobj.get("LastName");
				Title = (String) jsonobj.get("Title");
				Phone = (String) jsonobj.get("Phone");
				Age = (String) jsonobj.get("Age");
				Email = (String) jsonobj.get("Email");
				System.out.println("FirstName: " + FirstName + " LastName: "
						+ LastName + " Title:" + Title + " Phone " + Phone
						+ " Age: " + Age + " Email: " + Email);
				counter++;
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (org.json.simple.parser.ParseException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
				{
					br.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void AddNewPerson()
	{
		BufferedReader br = null;
		JSONObject obj = new JSONObject ();
		System.out.println("Insert First Name:");
		try
		{
			//reading the new person information from user.
			Scanner scan=new Scanner(System.in);
			FirstName=scan.nextLine();
			obj.put("FirstName", FirstName);
			System.out.println("Insert Last Name:");
			scan=new Scanner(System.in);
			LastName=scan.nextLine();
			obj.put("LastName", LastName);
			//validating the whether the title is correct or not
			do
			{
				System.out.println("Insert Title:");
				scan=new Scanner(System.in);
				StrTest=scan.nextLine();
				switch (StrTest)
				{
				case "Mr":
					Title=StrTest;
					break;
				case "Mrs":
					Title=StrTest;
					break;
				case "Miss":
					Title=StrTest;
					break;
				case "Ms":
					Title=StrTest;
					break;
				default:
					System.out.println("Enter correct Title.");
					break;
				}
			}while(StrTest!=Title);
			obj.put("Title", Title);
			//validating whether the phone number consists of 11 numbers starts with '01'.
			do
			{
				System.out.println("Insert Phone Number:");
				scan=new Scanner(System.in);
				StrTest=scan.nextLine();
			}while(StrTest.length()!=11&&StrTest.charAt(0)!=0&&StrTest.charAt(1)!=1);
			Phone=StrTest;
			obj.put("Phone", Phone);
			//validating whether the age is between 10 & 70.
			do
			{
				System.out.println("Insert Age:");
				scan=new Scanner(System.in);
				IntTest=scan.nextInt();
			}while(IntTest<10||IntTest>70);
			Age=Integer.toString(IntTest);
			obj.put("Age", Age);

			System.out.println("Insert Email:");
			scan=new Scanner(System.in);
			Email=scan.nextLine();
			obj.put("Email", Email);
		}
		catch(Exception e)
		{

		}
		WriteToFile(obj);
		System.out.println("Successfuly add :"+obj);
	}
	public void UpdatePersonInformation()
	{

	}
	public void FilterBasedOnField()
	{

	}
	public void WriteToNewFile(JSONObject obj)
	{
		BufferedReader br = null;
		try (FileWriter filewriter = new FileWriter("myJSON.json"))
		{
			br = new BufferedReader(new FileReader("myJSON.json"));
			if (br.readLine() == null) {
				filewriter.write(obj.toString());
				filewriter.flush();
			}
			else
			{
				filewriter.write("\n"+obj.toString());
				filewriter.flush();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				if (br != null)
				{
					br.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	public void WriteToFile(JSONObject obj)
	{
		BufferedReader br = null;
		try (FileWriter filewriter = new FileWriter("myJSON.json",true))
		{
			br = new BufferedReader(new FileReader("myJSON.json"));
			if (br.readLine() == null) {
				filewriter.write(obj.toString());
				filewriter.flush();
			}
			else
			{
				filewriter.write("\n"+obj.toString());
				filewriter.flush();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				if (br != null)
				{
					br.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	public void Sort(String Field)
	{

	}
	public void Delete(String Name)
	{
		JSONParser parser = new JSONParser();
		BufferedReader br = null;
		try {
			Boolean Found=false;
			String sCurrentLine;
			ArrayList<String> list= new ArrayList<String>();
			br = new BufferedReader(new FileReader("myJSON.json"));
			while ((sCurrentLine = br.readLine()) != null) 
			{
				Object obj = parser.parse(sCurrentLine);
				JSONObject jsonobj = (JSONObject) obj;
				if(Name.contains((String)jsonobj.get("FirstName")))
				{
					jsonobj.remove("FirstName", (String)jsonobj.get("FirstName"));
					jsonobj.remove("LastName",(String)jsonobj.get("LastName"));
					jsonobj.remove("Title",(String)jsonobj.get("Title"));
					jsonobj.remove("Age",(String)jsonobj.get("Age"));
					jsonobj.remove("Phone",(String)jsonobj.get("Phone"));
					jsonobj.remove("Email",(String)jsonobj.get("Email"));
					Found=true;
					System.out.println("Successfully Deleted!");
				}
				else
				{
					WriteToNewFile(jsonobj);
					
				}
				if(Found=false)
				{
					System.out.println("This Name doesnt exist in File!");
				}
			}
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (org.json.simple.parser.ParseException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
				{
					br.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
}

