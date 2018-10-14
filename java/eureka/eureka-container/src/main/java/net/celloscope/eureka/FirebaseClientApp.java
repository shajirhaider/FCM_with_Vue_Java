
package net.celloscope.eureka;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

public class FirebaseClientApp {

	public static void main(String[] args) throws Exception {
		
		String file = "< Firebase admin sdk json file path >";	
		String db = "< firestore url >";
		init(file, db);
		sendMessage();
		
	}
	
	private static void sendMessage() throws Exception  {
		// This registration token comes from the client FCM SDKs.
		String registrationToken = "< from client app >" ;
		// See documentation on defining a message payload.
		Message message = Message.builder()
		    .putData("score", "710")
		    .putData("time", "2:45")
		    .setToken(registrationToken)
		    //.setTopic(registrationToken)
		    .build();

		// Send a message to the device corresponding to the provided
		// registration token.
		String response = FirebaseMessaging.getInstance().send(message);
		// Response is a message ID string.
		System.out.println("Successfully sent message: " + response);
	}
	
	private static void init(String file, String db)throws Exception  {
		File file2 = new File(file);
		InputStream serviceAccount = new FileInputStream(file2);
		FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl(db)
			.build();

		FirebaseApp.initializeApp(options);
	}
	
	private static void writeToDB()throws Exception  {
		Firestore db = FirestoreClient.getFirestore();
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("score", "710");
		fields.put("time", "2:45");
		ApiFuture<WriteResult> future = db.collection("sampleData").document("inspiration").set(fields);
		System.out.println("Successfullyupdated at : " + future.get().getUpdateTime());
	}
	
	private static void readToDB()throws Exception  {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("sampleData").document("inspiration");
		ApiFuture<DocumentSnapshot> future2 = docRef.get();
		DocumentSnapshot document = future2.get();
		if(document.exists()) {
			System.out.println("Found : "+document);
		} else {
			System.out.println("Not found");			
		}
	}

}

