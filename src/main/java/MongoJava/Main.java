package MongoJava;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

import org.bson.Document;

public class Main {

	public static void main(String[] args) {
		insertData("first" , "firstValue");
		updateData("first" ,"firstValue", "updatedFirst" , "updatedValue");
		deleteData("key" , "value");
		System.out.print(getData());
	}
	static ArrayList<Document> getData() {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		ArrayList<Document>docs = new ArrayList<>();
		for(Document document : collection.find()) {
			docs.add(document);
		}
		return docs;
	}
	static void insertData(String key , String value) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		Document doc = new Document(key , value);
		collection.insertOne(doc);
	}
	
	static void updateData(String key ,String value ,  String newKey , String newValue) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		collection.updateOne(Filters.eq(key , value) , Updates.set(newKey, value) );
	}
	
	static void deleteData(String key , String value) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		collection.deleteOne(Filters.eq(key , value));
	}
	
}
