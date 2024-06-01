package org.example.utility;

import lombok.experimental.SuperBuilder;
import org.example.model.Person;
import org.example.model.enums.Role;
import org.example.service.commentService.CommentService;
import org.example.service.creditService.CreditService;
import org.example.service.customerService.CustomerService;
import org.example.service.offerService.OfferService;
import org.example.service.personService.PersonService;
import org.example.service.requestService.RequestService;
import org.example.service.serveService.ServeService;
import org.example.service.subServeService.SubServeService;
import org.example.service.technicianService.TechnicianService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final CommentService commentService = ApplicationContext.getCommentService();
    private final CreditService creditService = ApplicationContext.getCreditService();
    private final CustomerService customerService = ApplicationContext.getCustomerService();
    private final OfferService offerService = ApplicationContext.getOfferService();
    private final PersonService personService = ApplicationContext.getPersonService();
    private final RequestService requestService = ApplicationContext.getRequestService();
    private final ServeService serveService = ApplicationContext.getServeService();
    private final SubServeService subServeService = ApplicationContext.getSubServeService();
    private final TechnicianService technicianService = ApplicationContext.getTechnicianService();

    Person personSignIn = new Person();


    public void mainMenu() {
        System.out.println("***** welcome *****");
        int choice = 0;
        System.out.println("1-SIGN UP");
        System.out.println("2-SIGN IN");
        System.out.println("3-EXIT");
        System.out.println(" choose your number: ");
        try {
            choice = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        switch (choice) {
            case 1 -> signUpPerson();
            case 2 -> signIn();
            case 3 -> {
                exit(0);
            }
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                mainMenu();
            }
        }
    }

    public void signUpPerson() {
        System.out.println("Please enter firstname: ");
        String firstname = getFirstname();
        System.out.println("Please enter lastname: ");
        String lastname = getLastname();
        System.out.println("Please enter your email: ");
        String email = getEmail();
        String username = email;
        System.out.println("Please enter your password : ");
        String password = getPassword();
        LocalDate singUpTime = LocalDate.now();
        System.out.println("choose person role : ");
        Role memberRole = chooseRole();
        Person person = Person.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .username(username)
                .password(password)
                .signUpTime(singUpTime)
                .role(memberRole)
                .build();

        if (memberRole == Role.CUSTOMER)
            singUpCustomer(person);
        if (memberRole == Role.TECHNICIAN)
            signUpTechnician(person);
    }

    public void singUpCustomer(Person person) {
    }

    public void signUpTechnician(Person person) {
    }

    public void signIn() {
    }


    public String getLastname() {
        String lastname;
        while (true) {
            lastname = scanner.nextLine();
            if (Validation.isValidLastname(lastname))
                break;
            else {
                System.out.println("Last name is not valid!!!!!!!!");
                System.out.println("please try again: ");
            }
        }
        return lastname;
    }

    public String getFirstname() {
        String firstname;
        while (true) {
            firstname = scanner.nextLine();
            if (Validation.isValidFirstname(firstname))
                break;
            else {
                System.out.println("firstName is not valid!!!!!!!!");
                System.out.println("please try again: ");
            }
        }
        return firstname;
    }

    public String getEmail() {
        String email;
        while (true) {
            email = scanner.nextLine();
            if (Validation.isValidEmail(email))
                break;
            else {
                System.out.println("email is not valid!!!!!!!!");
                System.out.println("please try again: ");
            }
        }
        return email;
    }

    public String getPassword() {
        String password;
        while (true) {
            password = scanner.nextLine();
            if (Validation.isValidPassword(password))
                break;
            else {
                System.out.println("password is not valid!!!!!!!!");
                System.out.println("please try again: ");
            }
        }
        return password;
    }

    public Role chooseRole() {
        Role role = null;
        int choice = 0;
        System.out.println("1- CUSTOMER");
        System.out.println("2- TECHNICIAN");
        System.out.println(" choose your role: ");
        try {
            choice = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        switch (choice) {
            case 1 -> role = Role.CUSTOMER;
            case 2 -> role = Role.TECHNICIAN;
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                System.out.println("try again");
            }
        }
        return role;
    }

}
