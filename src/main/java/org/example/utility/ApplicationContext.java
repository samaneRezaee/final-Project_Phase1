package org.example.utility;

import org.example.conncetion.SessionFactorySingleton;

import org.example.repository.commentRepository.CommentRepository;
import org.example.repository.commentRepository.CommentRepositoryImpl;
import org.example.repository.creditRepository.CreditRepository;
import org.example.repository.creditRepository.CreditRepositoryImpl;
import org.example.repository.customerRepository.CustomerRepository;
import org.example.repository.customerRepository.CustomerRepositoryImpl;
import org.example.repository.offerRepository.OfferRepository;
import org.example.repository.offerRepository.OfferRepositoryImpl;
import org.example.repository.personRepository.PersonRepository;
import org.example.repository.personRepository.PersonRepositoryImpl;
import org.example.repository.requestRepository.RequestRepository;
import org.example.repository.requestRepository.RequestRepositoryImpl;
import org.example.repository.requestStatusRepository.RequestStatusRepository;
import org.example.repository.requestStatusRepository.RequestStatusRepositoryImpl;
import org.example.repository.serveRepository.ServeRepository;
import org.example.repository.serveRepository.ServeRepositoryImpl;
import org.example.repository.subServeRepository.SubServeRepository;
import org.example.repository.subServeRepository.SubServeRepositoryImpl;
import org.example.repository.technicianRepository.TechnicianRepository;
import org.example.repository.technicianRepository.TechnicianRepositoryImpl;
import org.example.repository.technicianStatusRepository.TechnicianStatusRepository;
import org.example.repository.technicianStatusRepository.TechnicianStatusRepositoryImpl;
import org.example.service.commentService.CommentService;
import org.example.service.commentService.CommentServiceImpl;
import org.example.service.creditService.CreditService;
import org.example.service.creditService.CreditServiceImpl;
import org.example.service.customerService.CustomerService;
import org.example.service.customerService.CustomerServiceImpl;
import org.example.service.offerService.OfferService;
import org.example.service.offerService.OfferServiceImpl;
import org.example.service.personService.PersonService;
import org.example.service.personService.PersonServiceImpl;
import org.example.service.requestService.RequestService;
import org.example.service.requestService.RequestServiceImpl;
import org.example.service.requestStatusService.RequestStatusService;
import org.example.service.requestStatusService.RequestStatusServiceImpl;
import org.example.service.serveService.ServeService;
import org.example.service.serveService.ServeServiceImpl;
import org.example.service.subServeService.SubServeService;
import org.example.service.subServeService.SubServeServiceImpl;
import org.example.service.technicianService.TechnicianService;
import org.example.service.technicianService.TechnicianServiceImpl;
import org.example.service.technicianStatusService.TechnicianStatusService;
import org.example.service.technicianStatusService.TechnicianStatusServiceImpl;
import org.hibernate.SessionFactory;

public class ApplicationContext {
    private static final SessionFactory SESSION_FACTORY;

    private static final CommentRepository COMMENT_REPOSITORY;

    private static final CreditRepository CREDIT_REPOSITORY;

    private static final CustomerRepository CUSTOMER_REPOSITORY;

    private static final OfferRepository OFFER_REPOSITORY;

    private static final PersonRepository PERSON_REPOSITORY;

    private static final RequestRepository REQUEST_REPOSITORY;

    private static final RequestStatusRepository REQUEST_STATUS_REPOSITORY;

    private static final ServeRepository SERVE_REPOSITORY;

    private static final SubServeRepository SUB_SERVE_REPOSITORY;

    private static final TechnicianRepository TECHNICIAN_REPOSITORY;

    private static final TechnicianStatusRepository TECHNICIAN_STATUS_REPOSITORY;
    //===============================================================================
    private static final CommentService COMMENT_SERVICE;

    private static final CreditService CREDIT_SERVICE;

    private static final CustomerService CUSTOMER_SERVICE;

    private static final OfferService OFFER_SERVICE;

    private static final PersonService PERSON_SERVICE;

    private static final RequestService REQUEST_SERVICE;

    private static final RequestStatusService REQUEST_STATUS_SERVICE;

    private static final ServeService SERVE_SERVICE;

    private static final SubServeService SUB_SERVE_SERVICE;

    private static final TechnicianService TECHNICIAN_SERVICE;

    private static final TechnicianStatusService TECHNICIAN_STATUS_SERVICE;


    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();

        COMMENT_REPOSITORY = new CommentRepositoryImpl(SESSION_FACTORY);
        CREDIT_REPOSITORY = new CreditRepositoryImpl(SESSION_FACTORY);
        CUSTOMER_REPOSITORY = new CustomerRepositoryImpl(SESSION_FACTORY);
        OFFER_REPOSITORY = new OfferRepositoryImpl(SESSION_FACTORY);
        PERSON_REPOSITORY = new PersonRepositoryImpl(SESSION_FACTORY);
        REQUEST_REPOSITORY = new RequestRepositoryImpl(SESSION_FACTORY);
        REQUEST_STATUS_REPOSITORY = new RequestStatusRepositoryImpl(SESSION_FACTORY);
        SERVE_REPOSITORY = new ServeRepositoryImpl(SESSION_FACTORY);
        SUB_SERVE_REPOSITORY = new SubServeRepositoryImpl(SESSION_FACTORY);
        TECHNICIAN_REPOSITORY = new TechnicianRepositoryImpl(SESSION_FACTORY);
        TECHNICIAN_STATUS_REPOSITORY = new TechnicianStatusRepositoryImpl(SESSION_FACTORY);

        COMMENT_SERVICE = new CommentServiceImpl(COMMENT_REPOSITORY, SESSION_FACTORY);
        CREDIT_SERVICE = new CreditServiceImpl(CREDIT_REPOSITORY, SESSION_FACTORY);
        CUSTOMER_SERVICE = new CustomerServiceImpl(CUSTOMER_REPOSITORY, SESSION_FACTORY);
        OFFER_SERVICE = new OfferServiceImpl(OFFER_REPOSITORY, SESSION_FACTORY);
        PERSON_SERVICE = new PersonServiceImpl(PERSON_REPOSITORY, SESSION_FACTORY);
        REQUEST_SERVICE = new RequestServiceImpl(REQUEST_REPOSITORY, SESSION_FACTORY);
        REQUEST_STATUS_SERVICE = new RequestStatusServiceImpl(REQUEST_STATUS_REPOSITORY, SESSION_FACTORY);
        SERVE_SERVICE = new ServeServiceImpl(SERVE_REPOSITORY, SESSION_FACTORY);
        SUB_SERVE_SERVICE = new SubServeServiceImpl(SUB_SERVE_REPOSITORY, SESSION_FACTORY);
        TECHNICIAN_SERVICE = new TechnicianServiceImpl(TECHNICIAN_REPOSITORY, SESSION_FACTORY);
        TECHNICIAN_STATUS_SERVICE = new TechnicianStatusServiceImpl(TECHNICIAN_STATUS_REPOSITORY, SESSION_FACTORY);

    }
    public static CommentService getCommentService(){
        return COMMENT_SERVICE;
    }
    public static CreditService getCreditService(){
        return CREDIT_SERVICE;
    }
    public static CustomerService getCustomerService(){
        return CUSTOMER_SERVICE;
    }
    public static OfferService getOfferService(){
        return OFFER_SERVICE;
    }
    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
    public static RequestService getRequestService(){
        return REQUEST_SERVICE;
    }
    public static RequestStatusService getRequestStatusService(){
        return REQUEST_STATUS_SERVICE;
    }
    public static ServeService getServeService(){
        return SERVE_SERVICE;
    }
    public static SubServeService getSubServeService(){
        return SUB_SERVE_SERVICE;
    }
    public static TechnicianService getTechnicianService(){
        return TECHNICIAN_SERVICE;
    }
    public static TechnicianStatusService getTechnicianStatusService(){return TECHNICIAN_STATUS_SERVICE;
    }


}
