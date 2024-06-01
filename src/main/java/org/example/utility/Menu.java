package org.example.utility;

import org.example.model.Person;
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
import java.util.Scanner;

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

    //todo sample to save image
//    public void testMenu() throws IOException {
//        String pathSource="D:\\Maktab_Sharif\\finalProject_part1\\images_source\\samaneh.jpg";
//        Path pathS= Paths.get(pathSource);
//        byte[] data= Files.readAllBytes(pathS);
//        Person person=Person.builder()
//                .firstname("samane")
//                .data(data)
//                .build();
//        personService.saveOrUpdate(person);
//        String fileName=person.getId().toString();
//        String pathDes="D:\\Maktab_Sharif\\finalProject_part1\\images_destination\\"+fileName+".jpg";
//        Path pathD=Paths.get(pathDes);
//        Files.write(pathD,person.getData());
//    }
}
