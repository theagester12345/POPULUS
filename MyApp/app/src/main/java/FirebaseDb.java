import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Dangote on 1/10/2017.
 */

public class FirebaseDb {
public Firebase fbase;

   public void  connectToFireDb(){

      fbase=new Firebase("https://fir-d-934c2.firebaseio.com/");


   }
}
