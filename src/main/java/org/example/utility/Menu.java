package org.example.utility;

import org.example.service.commentService.CommentService;
import org.example.service.creditService.CreditService;
import org.example.service.customerService.CustomerService;
import org.example.service.offerService.OfferService;
import org.example.service.personService.PersonService;
import org.example.service.requestService.RequestService;
import org.example.service.requestStatusService.RequestStatusService;
import org.example.service.serveService.ServeService;
import org.example.service.subServeService.SubServeService;
import org.example.service.technicianService.TechnicianService;
import org.example.service.technicianStatusService.TechnicianStatusService;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final CommentService commentService = ApplicationContext.getCommentService();
    private final CreditService creditService = ApplicationContext.getCreditService();
    private final CustomerService customerService = ApplicationContext.getCustomerService();
    private final OfferService offerService = ApplicationContext.getOfferService();
    private final PersonService personService = ApplicationContext.getPersonService();
    private final RequestService requestService = ApplicationContext.getRequestService();
    private final RequestStatusService requestStatusService = ApplicationContext.getRequestStatusService();
    private final ServeService serveService = ApplicationContext.getServeService();
    private final SubServeService subServeService = ApplicationContext.getSubServeService();
    private final TechnicianService technicianService = ApplicationContext.getTechnicianService();
    private final TechnicianStatusService technicianStatusService = ApplicationContext.getTechnicianStatusService();

}
