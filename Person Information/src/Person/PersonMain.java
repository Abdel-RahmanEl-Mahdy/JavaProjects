package Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class PersonMain {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Methods method = new Methods();
		method.AddNewPerson();
		method.ReadJsonFile();
		method.Delete("Ahmed");
		method.ReadJsonFile();
	}
}
