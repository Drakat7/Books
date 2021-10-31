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
      List<Publishers> publishers = new ArrayList<>();
      publishers.add(new Publishers("Harry", "a@.com", "222-222-2222"));
      booksMain.createEntity(publishers);
      //publishers = booksMain.getPublishers();
      //addPublisher(publishers, in);

















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

   public List<Publishers> getPublishers(){
      List<Publishers> publishers = this.entityManager.createNamedQuery("ReturnPublishers",
              Publishers.class).getResultList();
      return publishers;
   }

   public List<Books> getBooks(){
      List<Books> books = this.entityManager.createNamedQuery("ReturnBooks",
              Books.class).getResultList();
      return books;
   }

   public List<Authoring_entities> getAuthoringEntities(){
      List<Authoring_entities> authoring_entities = this.entityManager.createNamedQuery("ReturnAuthoringEntities",
              Authoring_entities.class).getResultList();
      return authoring_entities;
   }

   public List<Ad_hoc_teams_member> getAdHocTeamsMembers(){
      List<Ad_hoc_teams_member> ad_hoc_teams_members = this.entityManager.createNamedQuery("ReturnAdHocTeamsMembers",
              Ad_hoc_teams_member.class).getResultList();
      return ad_hoc_teams_members;
   }

   public static void addPublisher(List<Publishers> publishers, Scanner in){
      String name= "";
      String email = "";
      String phone = "";
      boolean nameSuccess = false;
      boolean emailSuccess = false;
      int phoneSuccess = 0;
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
         if (phone.matches(".*[a-z]*.")) {
            System.out.println("That is not a valid phone number. Letters are not allowed. Please try again.");
         } else {
            phoneSuccess++;
         }
      }
      Publishers newPublisher = new Publishers(name, email, phone);
      if(!publishers.contains(newPublisher)){
         publishers.add(newPublisher);
      }else{
         System.out.println("There is already a publisher with that information.");
      }
   }


} // End of CarClub class
