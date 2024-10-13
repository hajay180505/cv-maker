package com.cvmaker.cvmaker.database;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.cvmaker.cvmaker.config.CONNECTION_CREDENTIALS;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import com.cvmaker.cvmaker.exception.InsertionException;
import com.cvmaker.cvmaker.exception.NoUserFoundException;
import com.cvmaker.cvmaker.schema.ResumeDetail;

import java.util.ArrayList;
import java.util.List;

public class Database {

    /*
        ajay : {
            name: ok
            resumedetails : [
                {

                }, ..
            ]
        }


     */

    String uri;
    CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    String dbName, collectionName;

    public Database(String uri, String dbName, String collectionName) {
        this.uri = uri;
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    public Database(){
        this.uri = CONNECTION_CREDENTIALS.URI;
        this.dbName = CONNECTION_CREDENTIALS.DATABASE_NAME;
        this.collectionName = CONNECTION_CREDENTIALS.COLLECTION_NAME;
    }

    public boolean ping(){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(collectionName, User.class);
            return true;
        }
        catch (MongoException e) {
            return false;
        }
    }

    public ResumeDetail getFillerResumeDetail() throws NoUserFoundException {
        return getResumeDetail("static", "TwoPagerOfficial");
    }

    public void upsertUser(User u) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(collectionName, User.class);

            // Filter to find the user by name
            Bson filter = Filters.eq("name", u.getName());

            // Prepare the update operation with $set to update specific fields
            Bson update = Updates.set("resumeDetails", u.getResumeDetails()) ;

            // Set upsert to true (update if exists, insert if not)
            UpdateOptions options = new UpdateOptions().upsert(true);

            // Update the document or insert if not exists
            collection.updateOne(filter, update, options);

        }
    }

    public void insertUser(User u) throws InsertionException {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(collectionName, User.class);

            // Create filter to check if a user with the same name already exists
            Bson filter = Filters.eq("name", u.getName());

            // Check if user already exists
            User existingUser = collection.find(filter).first(); // `first()` gets the first matching document

            if (existingUser == null) {
                collection.insertOne(u);
            } else {
                throw new InsertionException("User with that name already exists.");
            }

        }
    }

    public List<ResumeDetail> getAllResumeDetails(String username) throws NoUserFoundException {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Filter to find the user by their name
            Bson filter = Filters.eq("name", username);

            // Project only the resumeDetails array
            Bson projection = Projections.fields(Projections.include("resumeDetails"), Projections.excludeId());

            // Find the user and get their resumeDetails array
            Document userDoc = collection.find(filter).projection(projection).first();

            if (userDoc != null) {
                List<ResumeDetail> resumeDetails = userDoc.getList("resumeDetails", ResumeDetail.class);
                return resumeDetails != null ? resumeDetails : new ArrayList<>();
            } else {
                throw new NoUserFoundException("User not found with the name: " + username);
            }

        }
    }

    public ResumeDetail getResumeDetail(String username, String resumeDetailTitle) throws NoUserFoundException {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Filter to find the user by their name
            Bson filter = Filters.eq("name", username);

            // Project only the resumeDetails array
            Bson projection = Projections.fields(Projections.include("resumeDetails"), Projections.excludeId());

            // Find the user and get their resumeDetails array
            Document userDoc = collection.find(filter).projection(projection).first();

            if (userDoc != null) {
                List<ResumeDetail> resumeDetails = userDoc.getList("resumeDetails", ResumeDetail.class);

                if (resumeDetails != null) {
                    for (ResumeDetail detail : resumeDetails) {
                        if (detail.getTitle().equalsIgnoreCase(resumeDetailTitle)) {
                            return detail;  // Return the specific resume detail
                        }
                    }
                }

                System.out.println("Resume detail not found with the title: " + resumeDetailTitle);
                return null;
            } else {
                throw new NoUserFoundException("User not found with the name: " + username);
            }

        } 
    }
    
    public void addResumeDetail(String username, ResumeDetail newDetail) throws NoUserFoundException {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(collectionName, User.class);

            // Filter to find the user by name
            Bson filter = Filters.eq("name", username);

            // Update operation to push the new resume detail to the resumeDetails array
            Bson update = Updates.push("resumeDetails", newDetail);

            // Execute the update
            UpdateResult result = collection.updateOne(filter, update);

            // Check if any document was updated
            if (result.getMatchedCount() == 0) {
                throw new NoUserFoundException("User not found with the name: " + username);
            } else {
                System.out.println("Resume detail added successfully to user: " + username);
            }

        }
    }

}