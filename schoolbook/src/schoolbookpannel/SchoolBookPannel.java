package schoolbookpannel;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import schoolbookpannel.dto.Student;
import schoolbookpannel.student.AddStudentViewModel;
import schoolbookpannel.SchoolBookPannel;
public class SchoolBookPannel {
	private AddStudentViewModel addStudentViewModel;
	private Scanner in = new Scanner(System.in);
	static SchoolBookPannel schoolbookpannel;
	private Result result;

	public SchoolBookPannel() {
		result = new AddStudent();
		addStudentViewModel = new AddStudentViewModel(this);
	}

	public static void main(String[] args) throws SQLException {

		schoolbookpannel = new SchoolBookPannel();
		schoolbookpannel.init();

	}

	public void init() throws SQLException {
		int choice=0;
		while(true) {

		System.out.println("--------WELCOME TO SCHOOL MANAGEMENT SYSTEM--------");
		System.out.println(" 1.Admin\n 2.Student \n 3.exit");
         System.out.println("Enter the option:");

         try {
             choice = in.nextInt(); // Moved this line inside the try block
         } catch (InputMismatchException e) {
             System.out.println("Invalid input. Please enter a number.");
             in.next(); 
             continue; 
         }


		switch (choice) {
		case 1:
			  handleAdminOptions();
			break;

		case 2:
			studentfun();
			break;

		case 3:
			System.out.println("Goodbye! Have a nice day!");
			System.exit(0);
			break;

		default:
			System.out.println("Invalid choice.");
			
		}
	}
	}
	private boolean checkAdminExistence() {
		return addStudentViewModel.loginCount();

	}

	private void signUp() throws SQLException {

		System.out.println("You Already Have Account Then Login:");
		System.out.println("Enter the id:");
		int id = in.nextInt();
		System.out.println("Enter name:");
		String name = in.next();
		System.out.println("Enter Email:");
		String email = in.next();
		addStudentViewModel.isValidEmail(email);
		System.out.println("Enter Password:");
		String password = in.next();
		Student student = new Student(id, name, email, password);
		result.signup(student);

	}

	private boolean login() throws SQLException {

		System.out.println("Enter Email:");
		String email = in.next();
		System.out.println("Enter Password:");
		String password = in.next();
		in.nextLine();
		Student student = new Student(email, password);
		return addStudentViewModel.login(student);

	}
	
	

	private void adminfun() {
		// AddStudent student1 = new AddStudent();
int option=0;
		while (true) {

			System.out.println(" 1.AddStudent\n 2.ViewStudent \n 3.UpdateStudent \n 4.DeleteStudent \n 5.exit");
			try {
			 option = in.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
	             in.next(); 
	             continue; 
			}

			switch (option) {
			case 1:
				result.addStudentInfo();
				break;
			case 2:
				result.viewStudent();
				break;
			case 3:
				result.updateStudent();
				break;
			case 4:
				result.deleteStudent();
				break;
			case 5:
				System.out.println("back to admin and Student!");
				try {
					schoolbookpannel.init();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid choice.");
				
			}

		}

	}
	
	 private void handleAdminOptions() throws SQLException {
	        System.out.println("1. Login\n2. Sign Up");
	        System.out.print("Enter the option: ");
	        int adminChoice = getUserInput();
	        if (adminChoice == -1) return;

	        switch (adminChoice) {
	            case 1:
	                if (login()) {
	                    System.out.println("Login successful:");
	                    adminfun();
	                } else {
	                    System.out.println("Login failed. Exiting....");
	                }
	                break;
	            case 2:
	                signUp();
	                if (login()) {
	                    System.out.println("Login successful!");
	                    adminfun();
	                } else {
	                    System.out.println("Login failed. Exiting....");
	                }
	                break;
	            default:
	                System.out.println("Invalid choice.");
	        }
	    }

	 
	 private int getUserInput() {
	        try {
	            return in.nextInt();
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a number.");
	            in.next();
	            return -1; 
	        }
	    }

	private void studentfun() throws SQLException {
	
			System.out.println("Student only Read the file:");
			schoolbookpannel.result.viewStudent();
			init();


	}
}
