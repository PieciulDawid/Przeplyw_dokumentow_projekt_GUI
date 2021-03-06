package bb.dd.dp.przeplyw_dokumentow_projekt_gui.Models;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;
import javafx.beans.property.SimpleStringProperty;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeModel implements Model {
	private int Id;
	private String Name;
	private String Surname;
	private String Login;
	private String Password;
	
	public EmployeeModel(int id, String name, String surname, String login, String password){
		Id = id;
		Name = name;
		Surname = surname;
		Login = login;
		Password = password;
	}
	
	//Getters
	public int getId(){
		return Id;
	}
	public String getName(){
		return Name;
	}
	public String getSurname(){
		return Surname;
	}
	public String getLogin(){
		return Login;
	}
	public String getPassword(){
		return Password;
	}
	
	//Setters
	public void setId(int id) {
		this.Id = id;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public void setSurname(String Surname) {
		this.Surname = Surname;
	}
	public void setLogin(String Login) {
		this.Login = Login;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	@Override
	public String toString() {
		return "EmployeeModel{" + "Id=" + Id + ", Name='" + Name + '\'' + ", Surname='" + Surname + '\'' + ", Login='" + Login + '\'' + ", Password='" + Password + '\'' + '}';
	}
	
	public Object[] toTableRow() {
		return new Object[] { Id, Name, Surname};
	}
	
	private static TreeMap<Integer, EmployeeModel> employeeModels = null;
	private static EmployeeModel loggedUser = null;
	
	public static EmployeeModel login(String login, String password) {
		try {
			List<String[]> users = new CSVReader(new FileReader("users.csv"))
					.readAll()
					.stream()
					.filter((String[] attr)->attr[3].equals(login) && attr[4].equals(password))
					.collect(Collectors.toList());
			if(users.isEmpty()) {
				return null;
			}
			String[] userRaw = users.get(0);
			loggedUser = new EmployeeModel(
					Integer.parseInt(userRaw[0]),
					userRaw[1],
					userRaw[2],
					userRaw[3],
					userRaw[4]
			);
		}
		catch(IOException | CsvException e) {
			e.printStackTrace();
		}
		
		return loggedUser;
	}
	
	public static EmployeeModel getLoggedUser() {
		return loggedUser;
	}
	
	public static void logout() {
		loggedUser = null;
	}
	
	public static TreeMap<Integer,EmployeeModel> getAll() {
		if(employeeModels != null) {
			return employeeModels;
		}
		
		try {
			List<String[]> employeesRaw = new CSVReader(new FileReader("users.csv")).readAll();
			employeeModels = employeesRaw.stream()
					.map((String[] raw)->
							new EmployeeModel(
									Integer.parseInt(raw[0]),
									raw[1],
									raw[2],
									raw[3],
									raw[4]
							))
					.collect(Collectors.toMap(
							EmployeeModel::getId,
							Function.identity(),
							(a, b)-> a,
							TreeMap::new));
		}
		catch(IOException | CsvException e) {
			e.printStackTrace();
		}
		
		return employeeModels;
	}
	
	public static TreeMap<Integer,EmployeeModel> search(String keyword) {
		String keywordLowerCase = keyword.toLowerCase(Locale.ROOT);
		TreeMap<Integer,EmployeeModel> result = getAll().values().stream()
				.filter((EmployeeModel item)->{
					return item.getName().toLowerCase(Locale.ROOT).startsWith(keywordLowerCase) ||
							item.getSurname().toLowerCase(Locale.ROOT).startsWith(keywordLowerCase);
				})
				.collect(Collectors.toMap(
						EmployeeModel::getId,
						Function.identity(),
						(a, b)-> a,
						TreeMap::new));
		return result;
	}
	
	public static EmployeeModel get(int id) {
		return getAll().get(id);
	}
	
	public static void add(EmployeeModel employee) {
		TreeMap<Integer,EmployeeModel> employees = getAll();
		employee.setId(employees.lastKey()+1);
		employees.put(employee.getId(), employee);
		saveChanges();
	}
	
	public static void modify(EmployeeModel employee) {
		getAll().replace(employee.getId(), employee);
		saveChanges();
	}
	
	
	public static void delete(EmployeeModel employee) {
		getAll().remove(employee.getId());
		saveChanges();
	}
	
	public static void delete(int id) {
		getAll().remove(id);
		saveChanges();
	}
	
	public static void saveChanges() {
		TreeMap<Integer,EmployeeModel> employees = getAll();
		try {
			ICSVWriter writer = new CSVWriterBuilder(new FileWriter("users.csv")).build();
			List<String[]> rawEmployees = employees.values().stream()
					.map((EmployeeModel employee)->{
						String[] rawEmployee = new String[5];
						rawEmployee[0] = String.valueOf(employee.getId());
						rawEmployee[1] = employee.getName();
						rawEmployee[2] = employee.getSurname();
						rawEmployee[3] = employee.getLogin();
						rawEmployee[4] = employee.getPassword();
						return rawEmployee;
					})
					.collect(Collectors.toList());
			
			writer.writeAll(rawEmployees);
			writer.flushQuietly();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
