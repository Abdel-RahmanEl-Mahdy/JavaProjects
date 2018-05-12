package Person;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PersonPreview {

	private JFrame frmPersonInformationSystem;
	private JTextField FirstNameTxt;
	private JTextField LastNameTxt;
	private JTextField PhoneTxt;
	private JLabel lblAge;
	private JTextField AgeTxt;
	private JLabel lblEmail;
	private JTextField EmailTxt;
	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField JSONPath;
	private String FirstName,LastName,Title,Age,Phone,Email;
	private JComboBox SortColumnComboBox;
	private ImageIcon OrangeLogo;
	private JLabel OrangeLabel;
	private boolean AddDone=false,Update=false;
	List<String> PersonList = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonPreview window = new PersonPreview();
					window.frmPersonInformationSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public PersonPreview() {
		initialize();
	}
	//This function is to add a new element in the JSON file,
	//By creating a new line in it containing the new values.
	@SuppressWarnings("unchecked")
	private void WriteToFile(String Path)
	{
		BufferedReader br = null;
		JSONObject obj = new JSONObject ();
		try
		{
			obj.put("FirstName", FirstName);
			obj.put("LastName", LastName);
			obj.put("Title", Title);
			obj.put("Phone", Phone);
			obj.put("Age", Age);
			obj.put("Email", Email);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		try (FileWriter filewriter = new FileWriter(Path,true))
		{
			br = new BufferedReader(new FileReader(Path));
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
		catch(IOException e1)
		{
			e1.printStackTrace();
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
	//After deleting an element from the table changes need to be made in the JSON file.
	//The list contain all elements except the delete record is written in a new version of the file with the same name.
	private void WriteToNewFile(JSONObject obj,String PathToJSONFile,int i)
	{
		BufferedReader br = null;
		if (i==1)
		{
			try (FileWriter filewriter = new FileWriter(PathToJSONFile))
			{
				br = new BufferedReader(new FileReader(PathToJSONFile));
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
		else
		{
			try (FileWriter filewriter = new FileWriter(PathToJSONFile,true))
			{
				br = new BufferedReader(new FileReader(PathToJSONFile));
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

	}
	//Delete method is used to check if the element exists then deletes it from the file.
	//by reading the file content then removing the object then writing the new version of file with same elements.
	private void Delete(String FName,String LName,String TitleArg,String AgeArg,String PhoneArg,String EmailArg,String PathToFile)
	{
		JSONParser parser = new JSONParser();
		BufferedReader br = null;
		try {
			int i=1;
			String sCurrentLine;
			br = new BufferedReader(new FileReader(PathToFile));
			while ((sCurrentLine = br.readLine()) != null) 
			{
				Object obj = parser.parse(sCurrentLine);
				JSONObject jsonobj = (JSONObject) obj;
				if(FName.contains((String)jsonobj.get("FirstName"))&&LName.contains((String)jsonobj.get("LastName"))
						&&TitleArg.contains((String)jsonobj.get("Title"))&&AgeArg.contains((String)jsonobj.get("Age"))
						&&PhoneArg.contains((String)jsonobj.get("Phone"))&&EmailArg.contains((String)jsonobj.get("Email")))
				{
					jsonobj.remove("FirstName", (String)jsonobj.get("FirstName"));
					jsonobj.remove("LastName",(String)jsonobj.get("LastName"));
					jsonobj.remove("Title",(String)jsonobj.get("Title"));
					jsonobj.remove("Age",(String)jsonobj.get("Age"));
					jsonobj.remove("Phone",(String)jsonobj.get("Phone"));
					jsonobj.remove("Email",(String)jsonobj.get("Email"));
				}
				else
				{
					WriteToNewFile(jsonobj,PathToFile,i);
					i++;
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPersonInformationSystem = new JFrame();
		frmPersonInformationSystem.setResizable(false);
		frmPersonInformationSystem.setTitle("Person Information System");
		frmPersonInformationSystem.setBounds(0, 0, 1377, 726);
		frmPersonInformationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPersonInformationSystem.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Person Information", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(84, 29, 516, 317);
		frmPersonInformationSystem.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 37, 126, 25);
		panel.add(lblNewLabel);

		FirstNameTxt = new JTextField();
		FirstNameTxt.setToolTipText("Enter first name (Alphebts only allowed)");
		//Handle the irrelevant data entered in text field
		FirstNameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char iText=evt.getKeyChar();
				if(!(Character.isAlphabetic(iText))||(iText==KeyEvent.VK_BACK_SPACE)||(iText==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		FirstNameTxt.setFont(new Font("Arial", Font.BOLD, 24));
		FirstNameTxt.setBounds(146, 34, 200, 30);
		panel.add(FirstNameTxt);
		FirstNameTxt.setColumns(10);

		LastNameTxt = new JTextField();
		LastNameTxt.setToolTipText("Enter last name (Alphebts only allowed)");
		//Handle the irrelevant data entered in text field
		LastNameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char iText=evt.getKeyChar();
				if(!(Character.isAlphabetic(iText))||(iText==KeyEvent.VK_BACK_SPACE)||(iText==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		LastNameTxt.setFont(new Font("Arial", Font.BOLD, 24));
		LastNameTxt.setColumns(10);
		LastNameTxt.setBounds(146, 68, 200, 30);
		panel.add(LastNameTxt);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Arial", Font.BOLD, 24));
		lblLastName.setBounds(10, 71, 126, 25);
		panel.add(lblLastName);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setBounds(10, 101, 126, 25);
		panel.add(lblTitle);

		PhoneTxt = new JTextField();
		//Handle the irrelevant data entered in text field
		PhoneTxt.setToolTipText("Enter Phone(Digits only allowed)");
		PhoneTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char iText=evt.getKeyChar();
				if(!(Character.isDigit(iText))||(iText==KeyEvent.VK_BACK_SPACE)||(iText==KeyEvent.VK_DELETE)||PhoneTxt.getText().length()>10)
				{
					evt.consume();
				}
			}
		});
		PhoneTxt.setFont(new Font("Arial", Font.BOLD, 24));
		PhoneTxt.setColumns(10);
		PhoneTxt.setBounds(146, 165, 200, 30);
		panel.add(PhoneTxt);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Arial", Font.BOLD, 24));
		lblPhone.setBounds(10, 166, 126, 25);
		panel.add(lblPhone);

		lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Arial", Font.BOLD, 24));
		lblAge.setBounds(10, 135, 126, 29);
		panel.add(lblAge);

		AgeTxt = new JTextField();
		AgeTxt.setToolTipText("Enter age (Digits only allowed)");
		//Handle the irrelevant data entered in text field
		AgeTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if(Integer.parseInt(AgeTxt.getText().toString())<10)
				{
					JOptionPane.showMessageDialog(null,"Please enter valid age!","Person Information System",JOptionPane.OK_OPTION);
					AgeTxt.grabFocus();
				}
			}
		});
		AgeTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char iText=evt.getKeyChar();
				if(!(Character.isDigit(iText))||(iText==KeyEvent.VK_BACK_SPACE)||(iText==KeyEvent.VK_DELETE)||AgeTxt.getText().length()>1)
				{
					evt.consume();
				}
			}
		});
		AgeTxt.setFont(new Font("Arial", Font.BOLD, 24));
		AgeTxt.setColumns(10);
		AgeTxt.setBounds(146, 132, 200, 30);
		panel.add(AgeTxt);

		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 24));
		lblEmail.setBounds(10, 201, 126, 25);
		panel.add(lblEmail);

		EmailTxt = new JTextField();
		EmailTxt.setToolTipText("Enter email");
		EmailTxt.setFont(new Font("Arial", Font.BOLD, 24));
		EmailTxt.setColumns(10);
		EmailTxt.setBounds(146, 198, 200, 30);
		panel.add(EmailTxt);

		JComboBox TitleCombobox = new JComboBox();
		TitleCombobox.setModel(new DefaultComboBoxModel(new String[] {"Mr", "Miss", "Mrs", "Ms"}));
		TitleCombobox.setBounds(146, 101, 69, 30);
		panel.add(TitleCombobox);
		JButton button = new JButton("Add");
		//Add Button -----------------------------------------------------------------------------------------------
		//this method is used in 2 cases first: to add a new element to the file.
		//second to update any record in the file.
		//Read entered data -> check if it exists -> add data to jtable -> add data to file.
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String JSONPathfile;
				JSONPathfile= JSONPath.getText();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				FirstName=FirstNameTxt.getText().toString();
				LastName=LastNameTxt.getText().toString();
				Title=TitleCombobox.getSelectedItem().toString();
				Age=AgeTxt.getText().toString();
				Phone=PhoneTxt.getText().toString();
				Email=EmailTxt.getText().toString();
				if(!PersonList.contains(FirstName+LastName+Title+Age+Phone+Email))
				{
					AddDone=true;
					PersonList.add(FirstName+LastName+Title+Age+Phone+Email);
					model.addRow(new Object[]
							{
							FirstName,LastName,Title,Age,Phone,Email,
							});
					WriteToFile(JSONPathfile);
					if(Update==false)
					{
						JOptionPane.showMessageDialog(null,"Successfully added a new row!","Person Information System",JOptionPane.OK_OPTION);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"This row already exists!","Person Information System",JOptionPane.OK_OPTION);
				}
				JTextField temp=null;
				for(java.awt.Component c:panel.getComponents())
				{
					if(c.getClass().toString().contains("javax.swing.JTextField"))
					{
						temp=(JTextField)c;
						temp.setText(null);
					}
				}
			}

		});
		button.setFont(new Font("Arial", Font.BOLD, 24));
		button.setBounds(84, 352, 200, 50);
		frmPersonInformationSystem.getContentPane().add(button);
		JButton btnUpdate = new JButton("Update");
		//Update button code-------------------------------------------------------------------------------------------
		//this method is used to updata any record in the file
		//Read entered data -> check if it exists -> delete old record -> add new record.
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int SelectedRow=table.getSelectedRow();
				String FName=model.getValueAt(SelectedRow, 0).toString();
				String LName=model.getValueAt(SelectedRow, 1).toString();
				String TitleArg=model.getValueAt(SelectedRow, 2).toString();
				String AgeArg=model.getValueAt(SelectedRow, 3).toString();
				String PhoneArg=model.getValueAt(SelectedRow, 4).toString();
				String EmailArg=model.getValueAt(SelectedRow, 5).toString();
				if(table.getSelectedRow()==-1)
				{
					if(table.getRowCount()==0)
					{
						JOptionPane.showMessageDialog(null,"No data to Update","Person Information System",JOptionPane.OK_OPTION);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Select a row to Update","Person Information System",JOptionPane.OK_OPTION);
					}
				}
				else
				{
					Update=true;
					button.doClick();
					if(AddDone==true)
					{
						model.removeRow(table.getSelectedRow());
						Delete(FName, LName, TitleArg, AgeArg, PhoneArg, EmailArg,JSONPath.getText());
						JOptionPane.showMessageDialog(null,"Successfully Updated the row!","Person Information System",JOptionPane.OK_OPTION);
					}
				}
				JTextField temp=null;
				for(java.awt.Component c:panel.getComponents())
				{
					if(c.getClass().toString().contains("javax.swing.JTextField"))
					{
						temp=(JTextField)c;
						temp.setText(null);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 24));
		btnUpdate.setBounds(290, 352, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		//Delete Button -----------------------------------------------------------------------------------------------
		//This method is used to delete any record from the file
		//Check if there is any data first-> remove from jtable -> call Delete method to delete record from the file.
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int SelectedRow=table.getSelectedRow();
				String FName=model.getValueAt(SelectedRow, 0).toString();
				String LName=model.getValueAt(SelectedRow, 1).toString();
				String TitleArg=model.getValueAt(SelectedRow, 2).toString();
				String AgeArg=model.getValueAt(SelectedRow, 3).toString();
				String PhoneArg=model.getValueAt(SelectedRow, 4).toString();
				String EmailArg=model.getValueAt(SelectedRow, 5).toString();
				if(SelectedRow==-1)
				{
					if(table.getRowCount()==0)
					{
						JOptionPane.showMessageDialog(null,"No data to delete","Person Information System",JOptionPane.OK_OPTION);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Select a row to delete","Person Information System",JOptionPane.OK_OPTION);
					}
				}
				else
				{
					model.removeRow(table.getSelectedRow());
					Delete(FName, LName, TitleArg, AgeArg, PhoneArg, EmailArg,JSONPath.getText());
					JOptionPane.showMessageDialog(null,"Successfully deleted the row!","Person Information System",JOptionPane.OK_OPTION);
				}
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 24));
		btnDelete.setBounds(496, 352, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnDelete);

		//Import JSON File Button -----------------------------------------------------------------------------------------------
		//This method is to read the JSON file and show it in the jtable.
		//Get the JSON file path-> check if its correct -> read line by line -> one JSON object at a time ->
		//add to list to check for repeated records -> show data in Jtable
		JButton btnView = new JButton("Import JSON");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//C:/Users/badom/workspace/Person Information/myJSON.json
				PersonList.clear();
				List<String> RepeatedPersonList = new ArrayList<String>();
				String JSONPathfile;
				int RepeatedRows=0;
				JSONPathfile= JSONPath.getText();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);
				String sCurrentLine;
				JSONParser parser = new JSONParser();
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(JSONPathfile));
					while ((sCurrentLine = br.readLine()) != null) 
					{
						Object obj = parser.parse(sCurrentLine);
						JSONObject jsonobj = (JSONObject) obj;
						FirstName= (String) jsonobj.get("FirstName");
						LastName= (String) jsonobj.get("LastName");
						Title =(String) jsonobj.get("Title");
						Age=(String) jsonobj.get("Age");
						Phone= (String) jsonobj.get("Phone");
						Email=(String) jsonobj.get("Email");
						if(!PersonList.contains(FirstName+LastName+Title+Age+Phone+Email))
						{
							PersonList.add(FirstName+LastName+Title+Age+Phone+Email);
							model.addRow(new Object[]
									{
									FirstName,LastName,Title,Age,Phone,Email,
									});
						}
						else
						{
							RepeatedRows++;
							RepeatedPersonList.add("First Name: "+FirstName+", Last Name: "+LastName+", Title: "+Title+", Age: "+Age+", Phone: "+Phone+", Email: "+Email);
						}
					}
					if(RepeatedRows>0)
					{
						JOptionPane.showMessageDialog(null,"Repeated Rows in JSON file: "+RepeatedRows+"\n"+RepeatedPersonList,"Person Information System",JOptionPane.OK_OPTION);
					}
				}
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				} 
				catch (org.json.simple.parser.ParseException e1)
				{
					e1.printStackTrace();
				}
				catch (Exception e1) 
				{
					e1.printStackTrace();
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
		});
		btnView.setFont(new Font("Arial", Font.BOLD, 24));
		btnView.setBounds(84, 525, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnView);
		//column sort code-----------------------------------------------------------------------------------------------
		//Sorting in two ways by column headers or by this code by getting the index of desired column to be sorted.
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
				table.setRowSorter(sorter);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				int columnIndexToSort = SortColumnComboBox.getSelectedIndex();
				sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
				sorter.sort();
			}
		});
		btnSort.setFont(new Font("Arial", Font.BOLD, 24));
		btnSort.setBounds(84, 408, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnSort);
		//FilterBUTTON code ----------------------------------------------------------------
		//This method is to filter based on an entered value.
		//lets say user entered a value that he can't remember beginning or the end this code handles this case.
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableRowSorter sorter = new TableRowSorter(table.getModel());
				sorter.setRowFilter(RowFilter.regexFilter(".*"+textField.getText()+".*"));
				table.setRowSorter(sorter);
			}
		});
		btnFilter.setFont(new Font("Arial", Font.BOLD, 24));
		btnFilter.setBounds(84, 464, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnFilter);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.RED);
		//Exit Button -----------------------------------------------------------------------------------------------
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame= new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmPersonInformationSystem, "Confirm if you want to exit","Person Information System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
				{
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 24));
		btnExit.setBounds(1161, 636, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnExit);
		JButton btnReset = new JButton("Reset");
		//Reset Button -----------------------------------------------------------------------------------------------
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField temp=null;
				for(java.awt.Component c:panel.getComponents())
				{
					if(c.getClass().toString().contains("javax.swing.JTextField"))
					{
						temp=(JTextField)c;
						temp.setText(null);
					}
				}
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);
				textField.setText(null);
				JSONPath.setText(null);
			}
		});
		btnReset.setFont(new Font("Arial", Font.BOLD, 24));
		btnReset.setBounds(706, 352, 200, 50);
		frmPersonInformationSystem.getContentPane().add(btnReset);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(610, 36, 498, 310);
		frmPersonInformationSystem.getContentPane().add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		//enable the column's header sorting.
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"First Name", "Last Name", "Title", "Age", "Phone", "Email"
				}
				));

		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setToolTipText("Enter value to filter for it");
		textField.setFont(new Font("Arial", Font.BOLD, 24));
		textField.setBounds(290, 465, 200, 50);
		frmPersonInformationSystem.getContentPane().add(textField);
		textField.setColumns(10);

		JSONPath = new JTextField();
		JSONPath.setToolTipText("Enter Path to JSON file");
		JSONPath.setFont(new Font("Arial", Font.BOLD, 24));
		JSONPath.setColumns(10);
		JSONPath.setBounds(290, 525, 818, 50);
		frmPersonInformationSystem.getContentPane().add(JSONPath);
		//Browse Button code---------------------------------------------------------------------------
		//grabbing the file and writing down it's path to the text field.
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChoser= new JFileChooser();
				fileChoser.showOpenDialog(null);
				File f=fileChoser.getSelectedFile();
				String FilePath=f.getAbsolutePath();
				JSONPath.setText(FilePath);
			}
		});
		btnNewButton.setBounds(1113, 525, 94, 50);
		frmPersonInformationSystem.getContentPane().add(btnNewButton);

		SortColumnComboBox = new JComboBox();
		SortColumnComboBox.setModel(new DefaultComboBoxModel(new String[] {"First Name", "Last Name", "Title", "Age", "Phone", "Email"}));
		SortColumnComboBox.setFont(new Font("Arial", Font.BOLD, 24));
		SortColumnComboBox.setBounds(290, 408, 154, 50);
		frmPersonInformationSystem.getContentPane().add(SortColumnComboBox);
		Image img = new ImageIcon(this.getClass().getResource("/Orange_logo100.jpg")).getImage();
		OrangeLabel = new JLabel("");
		OrangeLabel.setIcon(new ImageIcon(img));
		OrangeLabel.setBounds(1199, 11, 162, 129);
		frmPersonInformationSystem.getContentPane().add(OrangeLabel);
	}
	protected JComboBox getSortColumnComboBox() {
		return SortColumnComboBox;
	}
	protected JLabel getOrangeLabel() {
		return OrangeLabel;
	}
}

