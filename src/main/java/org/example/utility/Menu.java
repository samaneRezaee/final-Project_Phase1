package org.example.utility;

import org.example.exception.NotFoundException;
import org.example.model.*;
import org.example.model.enums.RequestStatus;
import org.example.model.enums.Role;
import org.example.model.enums.TechnicianStatus;
import org.example.service.addressService.AddressService;
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
    private final AddressService addressService = ApplicationContext.getAddressService();
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
        System.out.println("Please enter your password : ");
        String password = getPassword();
        LocalDate singUpTime = LocalDate.now();
        System.out.println("choose person role : ");
        Role memberRole = chooseRole();
        Person person = Person.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .username(email)
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
        System.out.println("please write about your skills: ");
        String skills = scanner.nextLine();
        byte[] bytes = new byte[0];
        boolean flag = true;
        while (flag) {
            System.out.println(" Please add your picture: (insert picture file path)");
            System.out.println("picture with size less than: 300K and jpg format.");
            String picPath = scanner.nextLine();
            Path path = Paths.get(picPath);
            try {
                bytes = Files.readAllBytes(path);
                if (bytes.length <= 300000)
                    flag = false;
            } catch (IOException e) {
                System.out.println("Invalid input.");
            }
        }
        Technician technician = new Technician(person.getFirstname(), person.getLastname(), person.getEmail()
                , person.getUsername(), person.getPassword(), person.getSignUpTime(), person.getRole());
        technician.setStatus(status);
        technician.setPicture(bytes);
        technician.setSkills(skills);
        technicianService.saveOrUpdate(technician);

        String fileName = technician.getUsername();
        String pathDes = "D:\\Maktab_Sharif\\finalProject_part1\\images_destination\\" + fileName + ".jpg";
        Path pathD = Paths.get(pathDes);
        try {
            Files.write(pathD, technician.getPicture());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("Invalid input.");


        }
        signIn();
    }

    public void signIn() {
        System.out.println("To signIn please enter your username (email address): ");
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
        System.out.println("5-TECHNICIAN LIST");
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
                adminMenu();
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
            Serve serve = new Serve(title, description);
            serveService.saveOrUpdate(serve);
            System.out.println("new serve is saved.");
            adminMenu();
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
                System.out.println("id: ");
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
            System.out.println(" the sub service is saved.");
            adminMenu();
        }
    }

    public void editServe() {
        System.out.println("to edit a Service choose it's id: ");
        serveService.loadAllServe();
        long idServe = 0L;
        boolean validInput = false;
        while (!validInput) {
            try {
                idServe = scanner.nextLong();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric id.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        try {
            serveService.findById(idServe);
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            editServe();
        }
        System.out.println("enter new title: ");
        String newTitle = getInput();
        System.out.println(" enter new description: ");
        String newDescription = getInput();
        Serve newServe = new Serve(idServe, newTitle, newDescription);
        serveService.saveOrUpdate(newServe);
        System.out.println("change is saved.");
        adminMenu();
    }

    public void editSubServe() {
        System.out.println("to edit a SubService choose it's id: ");
        subServeService.loadAllSubServe();
        long idSubServe = 0L;
        while (true) {
            try {
                idSubServe = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric id.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        try {
            subServeService.findById(idSubServe);
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            editSubServe();
        }
        System.out.println("enter new title: ");
        String newTitle = getInput();
        System.out.println("enter new price: ");
        double newPrice = 0;
        while (true) {
            try {
                newPrice = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric price.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.println(" enter new description: ");
        String newDescription = getInput();
        System.out.println("choose a new Serve for this sub service: ");
        serveService.loadAllServe();
        Long newServeId = scanner.nextLong();
        scanner.nextLine();
        Serve serve = serveService.findById(newServeId);
        SubServe newSubServe = new SubServe(idSubServe, newTitle, newPrice, newDescription, serve);
        subServeService.saveOrUpdate(newSubServe);
        System.out.println("change is saved.");
        adminMenu();
    }

    public void changeTechnicianStatus() {
        int choice = 0;
        System.out.println("ADMIN : " + personSignIn.getUsername());
        System.out.println("1-REGISTER NEW TECHNICIAN ");
        System.out.println("2-ADD TECHNICIAN TO SUB SERVICE ");
        System.out.println("3-DELETE TECHNICIAN FROM SUB SERVICE ");
        System.out.println("4-DELETE TECHNICIAN ");
        System.out.println("5-BACK");
        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric input.");
            }
        }
        scanner.nextLine();
        switch (choice) {
            case 1 -> registerNewTechnician();
            case 2 -> addTechnicianToSubService();
            case 3 -> deleteTechnicianFromSubService();
            case 4 -> deleteTechnician();
            case 5 -> adminMenu();
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                changeTechnicianStatus();
            }
        }
    }

    private void deleteTechnician() {
        System.out.println("Please insert technician id to delete: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Technician technician = technicianService.findById(id);
            technicianService.delete(technician);
            System.out.println("technician is deleted.");
            changeTechnicianStatus();
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            changeTechnicianStatus();
        }
    }

    public void registerNewTechnician() {
        technicianService.showTechnicianWithStatus(TechnicianStatus.NEW);
        System.out.println("Enter technician id: ");
        long id = 0;
        while (true) {
            try {
                id = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric id.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        try {
            Technician technician = technicianService.findById(id);
            technician.setStatus(TechnicianStatus.CONFIRMED);
            technicianService.saveOrUpdate(technician);
            System.out.println("Technician status is changed.");
            changeTechnicianStatus();
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            registerNewTechnician();
        }
    }

    public void addTechnicianToSubService() {
        subServeService.loadAllSubServe();
        System.out.println("enter sub service id: ");
        long subServeId = scanner.nextLong();
        scanner.nextLine();
        SubServe subServe = subServeService.findById(subServeId);
        System.out.println(" enter technician id to add the sub service: ");
        long id = 0;
        while (true) {
            try {
                id = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric id.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        try {
            Technician technician = technicianService.findById(id);
            technician.setSubServe(subServe);
            technicianService.saveOrUpdate(technician);
            System.out.println("sub service is added to technician information.");
            changeTechnicianStatus();
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            addTechnicianToSubService();
        }
    }

    public void deleteTechnicianFromSubService() {
        technicianService.showTechnicianWithStatus(TechnicianStatus.CONFIRMED);
        System.out.println(" enter technician id to delete from sub service: ");
        long id = 0;
        while (true) {
            try {
                id = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric id.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        try {
            Technician technician = technicianService.findById(id);
            technician.setSubServe(null);
            technicianService.saveOrUpdate(technician);
            System.out.println("sub service is deleted from technician.");
            changeTechnicianStatus();
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            deleteTechnicianFromSubService();
        }
    }

    public void customerMenu() {
        int choice = 0;
        System.out.println("CUSTOMER : " + personSignIn.getUsername());
        System.out.println("1-REGISTER AN ORDER: ");
        System.out.println("2-EDIT PROFILE: ");
        System.out.println("3-CHANGE PASSWORD: ");
        System.out.println("4-BACK");
        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric input.");
            }
        }
        scanner.nextLine();
        switch (choice) {
            case 1 -> registerRequest();
            case 2 -> editProfile();
            case 3 -> changePassword();
            case 4 -> mainMenu();
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                customerMenu();
            }
        }
    }

    private void registerRequest() {
        Customer customer = customerService.findById(personSignIn.getId());
        showSubServe();
        System.out.println("do you want to register an order? (y/n)");
        String input = scanner.nextLine();
        if (input.equals("y")) {
            System.out.println("enter sub serve id: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            SubServe subServe = subServeService.findById(id);
            System.out.println("insert price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("write a description about your order: ");
            String description = scanner.nextLine();
            System.out.println("insert a date for your order: (yyyy/M/d)");
            LocalDate date = Validation.isValidDate();
            RequestStatus status = RequestStatus.WAIT_FOR_PROPOSAL;
            Request request = new Request(price, description, date, status, customer, subServe);
            Request requestWithId = requestService.saveOrUpdate(request);
            addressOfRequest(requestWithId);
            System.out.println("request is saved.");
            customerMenu();
        } else
            customerMenu();
    }

    private void addressOfRequest(Request request) {
        System.out.println("enter city name: ");
        String city = getInput();
        System.out.println("enter avenue name: ");
        String avenue = getInput();
        System.out.println("enter allay: ");
        String allay = getInput();
        System.out.println("enter doorplate: ");
        String doorplate = scanner.nextLine();
        Address address = new Address(city, avenue, allay, doorplate, request);
        addressService.saveOrUpdate(address);
    }

    private void showSubServe() {
        System.out.println("to see each sub service of this services insert it's id: ");
        serveService.loadAllServe();
        long idServe = 0;
        while (true) {
            try {
                idServe = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric id.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        try {
            Serve serve = serveService.findById(idServe);
            subServeService.findByServeId(serve.getId());
        } catch (NotFoundException e) {
            System.out.println("id is not found!");
            registerRequest();
        }
    }

    private void technicianMenu() {
        int choice = 0;
        System.out.println("TECHNICIAN : " + personSignIn.getUsername());
        System.out.println("1-EDIT PROFILE: ");
        System.out.println("2-CHANGE PASSWORD: ");
        System.out.println("3-BACK");
        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric input.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        switch (choice) {
            case 1 -> editProfile();
            case 2 -> changePassword();
            case 3 -> mainMenu();
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                technicianMenu();
            }
        }
    }

    private void editProfile() {
        System.out.println("role: " + personSignIn.getRole() + ", firstname: " + personSignIn.getFirstname() + ", lastname: "
                + personSignIn.getLastname() + ", email: " + personSignIn.getEmail());
        System.out.println("choose a number to edit: ");
        System.out.println("1-firstname \n2-lastname \n3-email \n4-back");
        int inputNumber = 0;
        while (true) {
            try {
                inputNumber = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric input.");
            }
        }
        scanner.nextLine();
        switch (inputNumber) {
            case 1 -> editFirstname();
            case 2 -> editLastname();
            case 3 -> editEmail();
            case 4 -> mainMenu();
            default -> {
                System.out.println("input number is wrong!!!!!!!!");
                editProfile();
            }
        }

    }

    private void editFirstname() {
        System.out.println("enter new firstname: ");
        String newFirstname = getFirstname();
        personSignIn.setFirstname(newFirstname);
        personService.saveOrUpdate(personSignIn);
        editProfile();
    }

    private void editLastname() {
        System.out.println("enter new lastname: ");
        String newLastname = getLastname();
        personSignIn.setLastname(newLastname);
        personService.saveOrUpdate(personSignIn);
        editProfile();
    }

    private void editEmail() {
        System.out.println("enter new email: ");
        String newEmail = getEmail();
        personSignIn.setEmail(newEmail);
        personSignIn.setUsername(newEmail);
        personService.saveOrUpdate(personSignIn);
        System.out.println("please sign in again");
        mainMenu();
    }

    private void changePassword() {
        System.out.println("role: " + personSignIn.getRole() + ", username: " + personSignIn.getUsername());
        System.out.println("enter new password: ");
        String newPassword1 = getPassword();
        System.out.println("confirm your new password: ");
        String newPassword2 = getPassword();
        if (newPassword2.equals(newPassword1)) {
            personSignIn.setPassword(newPassword2);
            personService.saveOrUpdate(personSignIn);
            System.out.println("password is changed.");
            mainMenu();
        } else {
            System.out.println("try again");
            changePassword();
        }
    }


    private String getLastname() {
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

    private String getFirstname() {
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

    private String getEmail() {
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

    private String getInput() {
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


    private String getPassword() {
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

    private Role chooseRole() {
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
