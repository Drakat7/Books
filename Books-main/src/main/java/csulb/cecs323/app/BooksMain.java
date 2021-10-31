/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.model.*;

import java.io.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * CECS 323 Project: JPA - Books
 * Getting introduced to working with the Java Persistence API (JPA)
 * By creating a JPA project and data to store books and their related information
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class BooksMain {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(BooksMain.class.getName());

   /**
    * The constructor for the BooksMain class which stores the EntityManager to be used later
    * @param manager    The EntityManager that we will use.
    */
   public BooksMain(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("BooksJPA");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of BooksMain and store our new EntityManager as an instance variable.
      BooksMain booksMain = new BooksMain(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();
      Scanner in = new Scanner(System.in);
      List<String> authoring_types = new ArrayList<>();
      authoring_types.add("WRITING GROUP");
      authoring_types.add("INDIVIDUAL AUTHOR");
      authoring_types.add("AD HOC TEAM");
      List<Publishers> publishers = new ArrayList<>();
      publishers = booksMain.getPublishers();

      List<Authoring_entities> authoring_entities = new ArrayList<>();
      authoring_entities = booksMain.getAuthoringEntities();

      List<Books> books = new ArrayList<>();
      books = booksMain.getBooks();

      List<Ad_hoc_teams_member> ad_hoc_teams_members = new ArrayList<>();
      ad_hoc_teams_members = booksMain.getAdHocTeamsMembers();
      boolean finished = false;
      String choice;
      while(!finished){
         prompt(authoring_entities, publishers, books, ad_hoc_teams_members, authoring_types, booksMain,in);
         System.out.println("Would you like to perform another task? (Y/N)");
         choice = in.nextLine();
         if(!choice.equalsIgnoreCase("y")){
            finished = true;
         }
      }


      booksMain.createEntity(publishers);
















      in.close();
      // Commit the changes so that the new data persists and is visible to other users.
      tx.commit();
      LOGGER.fine("End of Transaction");

   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   /**
    * returns a list of Publishers in our data for viewing or malipulation
    * @return List<Publishers>
    */
   public List<Publishers> getPublishers(){
      List<Publishers> publishers = this.entityManager.createNamedQuery("ReturnPublishers",
              Publishers.class).getResultList();
      return publishers;
   }

   /**
    * Checks to see if a publisher name is already in our database
    * @param name
    * @return Publisher
    */
   public Publishers checkPublisherName(String name){
      List<Publishers> publishers = this.entityManager.createNamedQuery("CheckPublishersName",
              Publishers.class).setParameter(1, name).getResultList();
      if (publishers.size() == 0) {
         return null;
      } else {
         return publishers.get(0);
      }
   }

   /**
    * Checks to see if a publisher email is already in our database
    * @param email
    * @return Publisher
    */
   public Publishers checkPublisherEmail(String email){
      List<Publishers> publishers = this.entityManager.createNamedQuery("CheckPublishersEmail",
              Publishers.class).setParameter(1, email).getResultList();
      if (publishers.size() == 0) {
         return null;
      } else {
         return publishers.get(0);
      }
   }

   /**
    *
    * Checks to see if a publisher Phone number is already in our database
    * @param phone
    * @return Publisher
    */
   public Publishers checkPublisherPhone(String phone){
      List<Publishers> publishers = this.entityManager.createNamedQuery("CheckPublishersPhone",
              Publishers.class).setParameter(1, phone).getResultList();
      if (publishers.size() == 0) {
         return null;
      } else {
         return publishers.get(0);
      }
   }

   /**
    * Returns a list of books already in our database
    * @return List<Books>
    */
   public List<Books> getBooks(){
      List<Books> books = this.entityManager.createNamedQuery("ReturnBooks",
              Books.class).getResultList();
      return books;
   }

   /**
    * Returns a list of Authoring Entities already in our database
    * @return List<Authoring_entities>
    */
   public List<Authoring_entities> getAuthoringEntities(){
      List<Authoring_entities> authoring_entities = this.entityManager.createNamedQuery("ReturnAuthoringEntities",
              Authoring_entities.class).getResultList();
      return authoring_entities;
   }

   /**
    * Checks to see if there is already an AuthoringEntity with this
    * email
    * @param email
    * @return Authoring_entities
    */
   public Authoring_entities checkAuthoringEntitiesEmail(String email){
      List<Authoring_entities> authoring_entities = this.entityManager.createNamedQuery("CheckAuthoringEntitiesEmail",
              Authoring_entities.class).setParameter(1, email).getResultList();
      if (authoring_entities.size() == 0) {
         return null;
      } else {
         return authoring_entities.get(0);
      }
   }

   /**
    * Checks to see if there is already a book with the same ISBN in our database
    * @param ISBN
    * @return Books
    */
   public Books checkISBN(String ISBN){
      List<Books> books = this.entityManager.createNamedQuery("CheckISBN",
              Books.class).setParameter(1, ISBN).getResultList();
      if (books.size() == 0){
         return null;
      }else{
         return books.get(0);
      }
   }

   /**
    * Checks to see if there is already an authoring entity with this name
    * @param name
    * @return Authoring_entities
    */
   public Authoring_entities checkAuthoringEntitiesName(String name){
      List<Authoring_entities> authoring_entities = this.entityManager.createNamedQuery("CheckAuthoringEntitiesName",
              Authoring_entitites.class).setParameter(1, email).getResultList();
      if (authoring_entities.size() == 0) {
         return null;
      }else{
         return authoring_entities.get(0);
      }
   }

   /**
    * Returns a list of Ad hoc team members
    * @return List<Ad_hoc_teams_member>
    */
   public List<Ad_hoc_teams_member> getAdHocTeamsMembers(){
      List<Ad_hoc_teams_member> ad_hoc_teams_members = this.entityManager.createNamedQuery("ReturnAdHocTeamsMembers",
              Ad_hoc_teams_member.class).getResultList();
      return ad_hoc_teams_members;
   }

   /**
    * Where most of logic user input logic is done
    * Prompts the user for what they wish to view or manipulte in our database
    * @param authoring_entities     A list of Authoring_entities in our database
    * @param publishers             A list of Publishers in our database
    * @param books                  A list of Books in our database
    * @param ad_hoc_teams_members   A list of Ad_hoc_team_members in our database
    * @param authoring_types        A list of Authoring_types in our database
    * @param booksMain              An instance of the Entity manager
    * @param in                     A Scanner object for userinput
    */
   static void prompt(List<Authoring_entities> authoring_entities, List<Publishers> publishers, List<Books> books,
                      List<Ad_hoc_teams_member> ad_hoc_teams_members, List<String> authoring_types, BooksMain booksMain, Scanner in){
      int input = 0;
      boolean valid = false;
      while(!valid){
         System.out.println("What would you like to do?");
         System.out.println("(1) Add new Authoring Entity");
         System.out.println("(2) Add new Publisher");
         System.out.println("(3) Add new Book");
         System.out.println("(4) List Information");
         System.out.println("(5) Delete a Book");
         System.out.println("(6) Update a Book");
         System.out.println("(7) List Primary Keys");
         try{
            input = in.nextInt();
            in.nextLine();
            if(input > 0 && input < 8)
            {
               valid = true;
            }else{
               System.out.println("That is not a valid input. Please try again.");
            }
         }catch(InputMismatchException e){
            System.out.println("That is not a valid input. Please try again.");
         }
      }
      switch(input){
         case 1:
            valid = false;
            while(!valid){
               System.out.println("(1) Add new Authoring Entity");
               System.out.println("(2) Add an Individual Author to an existing Ad Hoc Team");
               try{
                  input = in.nextInt();
                  in.nextLine();
                  if(input > 0 && input < 2)
                  {
                     valid = true;
                  }else{
                     System.out.println("That is not a valid input. Please try again.");
                  }
               }catch(InputMismatchException e){
                  System.out.println("That is not a valid input. Please try again.");
               }
            }
            switch(input){
               case 1:
                  addAuthoringEntity(authoring_entities, authoring_types, booksMain, in);
                  break;
               case 2:
                  addAuthorToTeam(authoring_entities, ad_hoc_teams_members, booksMain, in);
                  break;
               default:
                  System.out.println("A critical error has occurred, shutting down.");
                  System.exit(1);
            }
            break;
         case 2:
            addPublisher(publishers, booksMain, in);
            break;
         case 3:
            addBook(books, booksMain, in);
            break;
         case 4:
            break;
         case 5:
            break;
         case 6:
            break;
         case 7:
            break;
         default:
            System.out.println("A critical error has occurred, shutting down.");
            System.exit(1);
      }
   }

   /**
    * A method for adding Authoring_entities to our database
    * @param authoring_entities
    * @param authoring_types
    * @param booksMain
    * @param in
    */
   public static void addAuthoringEntity(List<Authoring_entities> authoring_entities, List<String> authoring_types,
                                         BooksMain booksMain, Scanner in){
      String email = "";
      String authoring_entity_type = "";
      String name = "";
      String head_writer = "";
      int year_formed = 0;
      boolean emailSuccess = false;
      boolean authoringEntityTypeSuccess = false;
      boolean nameSuccess = false;
      boolean headWriterSuccess = false;
      boolean yearFormedSuccess = false;
      int type = -1;

      for(int i=0; i<authoring_entities.size(); i++){
         System.out.println(authoring_entities.get(i).toString());
      }
      while(!emailSuccess){
         System.out.println("Please enter the authoring entities email (Max length: 30 chars:");
         email = in.nextLine();
         if(email.length() > 30){
            System.out.println("That email is too long. Please try again.");
         }else{
            emailSuccess = true;
         }
      }
      while(!authoringEntityTypeSuccess){
         System.out.println("Please enter the authoring entities type (Max length: 31 chars:");
         authoring_entity_type = in.nextLine();
         for(int i=0; i<authoring_types.size(); i++){
            if(authoring_entity_type.equalsIgnoreCase(authoring_types.get(i))){
                  authoringEntityTypeSuccess = true;
                  type = i;
            }else{
               System.out.println("That is not a valid type. Please try again.");
            }
         }
      }
      while(!nameSuccess){
         System.out.println("Please enter the publishers name (Max length: 80 chars): \n");
         name = in.nextLine();
         if(name.length() > 80){
            System.out.println("That name is too long. Please try again.");
         }else{
            nameSuccess = true;
         }
      }
      if(type == 0){
         while(!headWriterSuccess){
            System.out.println("Please enter the head writers name (Max length: 80 chars): \n");
            head_writer = in.nextLine();
            if(head_writer.length() > 80){
               System.out.println("That name is too long. Please try again.");
            }else{
               headWriterSuccess = true;
            }
         }
         while(!yearFormedSuccess){
            System.out.println("Please enter the year that the authoring entity was formed: \n");
            try{
               year_formed = in.nextInt();
               in.nextLine();
               if(year_formed > 0){
                  yearFormedSuccess = true;
               }
            }catch (InputMismatchException e){
               System.out.println("That is not a valid year. Please try again.");
            }
         }
      }
      if(booksMain.checkAuthoringEntitiesEmail(email) != null){
         switch(type){
            case 0:
               authoring_entities.add(new Writing_group(email, authoring_entity_type, name, head_writer, year_formed));
               break;
            case 1:
               authoring_entities.add(new Individual_author(email, authoring_entity_type, name));
               break;
            case 2:
               authoring_entities.add(new Ad_hoc_team(email, authoring_entity_type, name));
               break;
            default:
               System.out.println("A critical error has occurred, shutting down.");
               System.exit(1);
         }
      }else{
         System.out.println("There is already an authoring entity with that email. Please try again.");
      }
   }

   /**
    * A method for adding an author to an Ad_hoc_team
    * @param authoring_entities
    * @param ad_hoc_teams_members
    * @param booksMain
    * @param in
    */
   public static void addAuthorToTeam(List<Authoring_entities> authoring_entities, List<Ad_hoc_teams_member> ad_hoc_teams_members,
                                      BooksMain booksMain, Scanner in){
      String individual_authors_email;
      String ad_hoc_teams_email;

   }


   /**
    * For adding a Publisher to the database
    * @param publishers
    * @param booksMain
    * @param in
    */
   public static void addPublisher(List<Publishers> publishers, BooksMain booksMain, Scanner in){
      String name= "";
      String email = "";
      String phone = "";
      boolean nameSuccess = false;
      boolean emailSuccess = false;
      int phoneSuccess = 0;
      for(int i=0; i<publishers.size(); i++){
         System.out.println(publishers.get(i).toString());
      }
      while(!nameSuccess){
         System.out.println("Please enter the publishers name (Max length: 80 chars): \n");
         name = in.nextLine();
         if(name.length() > 80){
            System.out.println("That name is too long. Please try again.");
         }else{
            nameSuccess = true;
         }
      }
      while(!emailSuccess){
         System.out.println("Please enter the publishers email (Max length: 80 chars:");
         email = in.nextLine();
         if(email.length() > 80){
            System.out.println("That email is too long. Please try again.");
         }else{
            emailSuccess = true;
         }
      }
      while (phoneSuccess!=2) {
         phoneSuccess = 0;
         System.out.println("Please enter the publishers phone number (Max length: 24 chars:");
         phone = in.nextLine();
         if (phone.length() > 24) {
            System.out.println("That phone number is too long. Please try again.");
         } else {
            phoneSuccess++;
         }
         if (phone.matches(".*[a-z].*")) {
            System.out.println("That is not a valid phone number. Letters are not allowed. Please try again.");
         }else {
            phoneSuccess++;
         }
      }
      if(booksMain.checkPublisherName(name) != null){
         if(booksMain.checkPublisherEmail(email) != null){
            if(booksMain.checkPublisherPhone(phone) != null){
               publishers.add(new Publishers(name, email, phone));
            }else{
               System.out.println("There is already a publisher with that phone number. Please try again.");
            }
         }else{
            System.out.println("There is already a publisher with that email. Please try again.");
         }
      }else{
         System.out.println("There is already a publisher with that name. Please try again.");
      }
   }//end of addPublisher

   /**
    * Adds a book to our existing database
    * making sure not to add the same book twice
    * making sure the publiser exist
    * making sure the authoring entity exist
    * @param books
    * @param booksMain
    * @param in
    */
   public static void addBook(List<Books> books, BooksMain booksMain, Scanner in){
     String ISBN = "";
     String title = "";
     int yearPublished = "";
     String authoringEntities = "";
     String publisher = "";
     Authoring_entities entity;
     Publishers thePublisher;

     boolean ISBNSuccess = false;
     boolean titleSuccess = false;
     boolean yPSuccess = false;
     boolean aESuccess = false;
     boolean publisherSuccess = false;

     while(!ISBNSuccess){
        System.out.println("Please enter an ISBN (Max length: 17 chars");
        ISBN = in.nextLine();
        if (ISBN.length() > 17){
           System.out.println("ISBN is too long, Please try again");
        }else{
           ISBNSuccess = true;
        }
     }

     while (!titleSuccess){
        System.out.println("Please enter a title (Max length: 80 chars");
        title = in.nextLine();
        if (title.length() > 80){
           System.out.println("Title is too long, Please try again");
        }else{
           titleSuccess = true;
        }
     }

     while(!yPSuccess){
        System.out.println("Please enter the year of publication (Max length: 4 chars");
        try {
           yearPublished = in.nextInt();
        }catch (InputMismatchException e) {
           in.nextLine();
           System.out.println("That's not a number! Try again\n");
           continue;
        }
        if (yearPublished > 9999 || yearPublished < 0){
           System.out.println("Year is to big or too small");
        }else{
           yPSuccess = true;
        }
     }

     while(!aESuccess){
        System.out.println("Please enter the name of the authoring entity (Max length: 80 ");
        authoringEntities = in.nextLine();
        if (authoringEntities.length() > 80){
           System.out.println("Name is too long, Please try again");
        }else{
           aESuccess = true;
        }
     }

     while(!publisherSuccess){
        System.out.println("Please enter the name of the publishers name (Max length: 80 ");
        publisher = in.nextLine();
        if (publisher.length() > 80){
           System.out.println("Name is too long, Please try again");
        }else{
           publisherSuccess = true;
        }
     }

      if(booksMain.checkISBN(ISBN) = null){
         if(booksMain.checkPublisherName(publisher) != null){
            if(booksMain.CheckAuthoringEntitiesName(authoringEntities) != null){
               thePublisher = booksMain.checkPublisherName(publisher);
               entity = booksMain.checkAuthoringEntitiesName(authoringEntities);
               books.add(new Books(ISBN, title, yearPublished, entity, thePublisher));
            }else{
               System.out.println("There is no authoring entity with that name. Please try again.");
            }
         }else{
            System.out.println("There is no publisher with that name. Please try again.");
         }
      }else{
         System.out.println("There is already a book with that ISBN. Please try again.");
      }
   }//end of addBook()


} // End of BooksMain class
