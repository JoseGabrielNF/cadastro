package database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author joseg github.com/JoseGabrielNF
 */
public class Conexao {

    public static void firebase() throws FileNotFoundException, IOException {
        FileInputStream serviceAccount = new FileInputStream("cadastrarusuarios.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
  .setDatabaseUrl("https://cadastrarusuarios-5185f-default-rtdb.firebaseio.com")
  .build();

        FirebaseApp.initializeApp(options);
        System.out.println("Conectado");
    }

}
