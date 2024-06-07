package org.example.utility;

import org.example.exception.NotFoundException;
import org.example.model.*;
import org.example.model.enums.Role;
import org.example.model.enums.TechnicianStatus;
import org.example.service.commentService.CommentService;
import org.example.service.creditService.CreditService;
import org.example.service.customerService.CustomerService;
import org.example.service.offerService.OfferService;
import org.example.service.personService.PersonService;
import org.example.service.requestService.RequestService;
import org.example.service.serveService.ServeService;
import org.example.service.subServeService.SubServeService;
import org.example.service.technicianService.TechnicianService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;
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
            case 3 -> exit(0);

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
        if (memberRole == Role.CUSTOMER) {
            singUpCustomer(person);
        }
        if (memberRole == Role.TECHNICIAN) {
            signUpTechnician(person);
        }
    }

    public void singUpCustomer(Person person) {
        Customer customer = new Customer(person.getFirstname(), person.getLastname(), person.getEmail()
                , person.getUsername(), person.getPassword(), person.getSignUpTime(), person.getRole());
        customerService.saveOrUpdate(customer);

        signIn();
    }

    public void signUpTechnician(Person person) {
        TechnicianStatus status = TechnicianStatus.NEW;
        byte[] bytes = new byte[0];
        boolean flag = true;
        while (flag) {
            System.out.println(" Please add your picture: (insert picture file path)");
            String picPath = scanner.nextLine();
            Path path = Paths.get(picPath);
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (bytes.length <= 300000)
                flag = false;
        }
        Technician technician = new Technician(person.getFirstname(), person.getLastname(), person.getEmail()
                , person.getUsername(), person.getPassword(), person.getSignUpTime(), person.getRole());
        technician.setStatus(status);
        technician.setPicture(bytes);
        technicianService.saveOrUpdate(technician);

        String fileName = technician.getUsername();
        String pathDes = "D:\\Maktab_Sharif\\finalProject_part1\\images_destination\\" + fileName + ".jpg";
        Path pathD = Paths.get(pathDes);
        try {
            Files.write(pathD, technician.getPicture());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        signIn();
    }

    public void signIn() {
        System.out.println("To signIn please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("enter your password: ");
        String password = scanner.nextLine();
        personSignIn = personService.findByUsername(username);
        if (personSignIn != null && password.equals(personSignIn.getPassword())) {
            switch (personSignIn.getRole()) {
                case ADMIN -> adminMenu();
                case CUSTOMER -> customerMenu();
                case TECHNICIAN -> technicianMenu();
                default -> System.out.println("something is wrong.");
            }
        } else {
            System.out.println("Username or password is incorrect!");
            mainMenu();
        }
    }

    public void adminMenu() {
        int choice = 0;
        System.out.println("ADMIN : " + personSignIn.getUsername());
        System.out.println("1-REGISTER NEW SERVE ");
        System.out.println("2-REGISTER NEW SUB_SERVE");
        System.out.println("3-EDIT SERVE");
        System.out.println("4-EDIT SUB_SERVE");
        System.out.println("5-VIEW TECHNICIAN LIST");
        System.out.println("6-BACK");
        System.out.println(" choose a number: ");
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        switch (choice) {
            case 1 -> registerServe();
            case 2 -> registerSubServe();
            case 3 -> editServe();
            case 4 -> editSubServe();
            case 5 -> changeTechnicianStatus();
            case 6 -> mainMenu();
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                mainMenu();
            }
        }
    }

    public void registerServe() {
        serveService.loadAllServe();
        System.out.println("Enter new Serve name: ");
        String title = getInput();
        if (serveService.isExistServe(title)) {
            System.out.println("this title is exist in table, try again.");
            registerServe();
        } else {
            System.out.println("Write a description about this serve: ");
            String description = getInput();
            Serve serve = new Serve(title,description);
            serveService.saveOrUpdate(serve);
            mainMenu();
        }
    }

    public void registerSubServe() {
        Serve serve = new Serve();
        System.out.println("please enter sub service title: ");
        String subTitle = getInput();
        if (subServeService.isExistSubServe(subTitle)) {
            System.out.println("this title is exists, please try again.");
            registerSubServe();
        } else {
            System.out.println("enter a price for this sub service: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Write a description about this serve: ");
            String description = getInput();
            boolean isNotExistServe = true;
            while (isNotExistServe) {
                System.out.println("enter Serve id for this sub serve: ");
                serveService.loadAllServe();
                Long idServe = scanner.nextLong();
                scanner.nextLine();
                serve = serveService.findById(idServe);
                if (serve != null) {
                    isNotExistServe = false;
                } else {
                    System.out.println("this Service is not exist, you should first create Service");
                    adminMenu();
                }
            }
            SubServe subServe = new SubServe(subTitle, price, description, serve);
            subServeService.saveOrUpdate(subServe);
            mainMenu();
        }
    }

    public void editServe() {
        System.out.println("to edit a Service choose it's id: ");
        serveService.loadAllServe();
        Long idServe = scanner.nextLong();
        scanner.nextLine();
        Serve serve=serveService.findById(idServe);
        System.out.println("enter new title: ");
        String newTitle = scanner.nextLine();
        if(Objects.equals(newTitle, ""))
            newTitle=serve.getTitle();
        scanner.nextLine();
        System.out.println(" enter new description: ");
        String newDescription = scanner.nextLine();
        if(Objects.equals(newDescription, ""))
            newDescription=serve.getDescription();
        Serve newServe=new Serve(idServe,newTitle,newDescription);
        serveService.saveOrUpdate(newServe);
        System.out.println("change is saved.");
        adminMenu();

    }

    public void editSubServe() {
    }

    public void changeTechnicianStatus() {
    }

    public void customerMenu() {
        System.out.println(" hello customer");
    }

    public void technicianMenu() {
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
        if (personService.isExistEmail(email)) {
            System.out.println(" this email is exists! please try again.");
            getEmail();
        }
        return email;
    }
    public String getInput() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (Validation.isValidStringInsert(input))
                break;
            else {
                System.out.println("input is not valid!!!!!!!!");
                System.out.println("please try again: ");
            }
        }
        return input;
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
