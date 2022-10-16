package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the full file path: ");
		String path = sc.next();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.println("Enter salary to apply the filter: ");
			double sal = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > sal)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
			System.out.println("Email of people whose salary is more than " + sal + ": ");
			System.out.println(emails);
			
			double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			System.out.println("Sum of salary of people whose name stats with 'M': " + String.format("%.2f", sum));
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			sc.close();
		}

	}

}
