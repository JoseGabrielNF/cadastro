package database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import model.Usuario;
import view.VerUsuarios;

/**
 *
 * @author joseg github.com/JoseGabrielNF
 */
public class BDUsuario {

    private static List<Usuario> usuarios= new ArrayList<>();

    public static List<Usuario> lerUsuarios() {
        return usuarios;
    }
    public static boolean excluir(String id){
       FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference ref = database.getReference("Usuario").child(id);
       ref.setValueAsync(null);
       return true;
    }
    public static int cadastrar(Usuario usuario){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Usuario");
        Map<String, Object> local = new HashMap<>();
        local.put("login", usuario.getLogin());
        local.put("senha", usuario.getSenha());
        local.put("email", usuario.getEmail());
        local.put("dataNascimento",usuario.getDataNascimento());
        ref.push().setValueAsync(local);
        return 1;
    }
    public static int atualizar(Usuario usuario){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Usuario").child(usuario.getId());//diferença entre o cadastrar e atualizar
        Map<String, Object> local = new HashMap<>();
        local.put("login", usuario.getLogin());
        local.put("senha", usuario.getSenha());
        local.put("email", usuario.getEmail());
        local.put("dataNascimento",usuario.getDataNascimento());
        ref.setValueAsync(local);
        return 1;
    }
    
    static CountDownLatch done ;
    public static void start(){
        //CountDown adicionado para esperar a conexao inicial   
        System.out.println("Conectando ao firebase");
        done= new CountDownLatch(1);
           
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
       
        ref.addValueEventListener(listener);
        
        
        try {
            done.await(); //esperar dados do firebase
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
      
    
        
    }
       public static ValueEventListener listener=(new ValueEventListener() {
        
        @Override
        public void onDataChange(DataSnapshot dataSnapShot) {
            usuarios.clear();
            
            //Le os dados de todos os usuários do banco
            for (DataSnapshot child : dataSnapShot.child("Usuario").getChildren()) {
               
                Usuario usuario=new Usuario();
                usuario.setId(child.getKey());
                usuario.setLogin((String) child.child("login").getValue(String.class));
                usuario.setSenha((String) child.child("senha").getValue(String.class));
                usuario.setEmail((String) child.child("email").getValue(String.class));
                usuario.setDataNascimento((String) child.child("dataNascimento").getValue(String.class));
                
                usuarios.add(usuario);
            }
            done.countDown();
            
            //Percorre por todas as janelas abertas e aciona a função de listar da janela. 
            Frame[] allFrames = Frame.getFrames();
            for (Frame frame : allFrames) {
                String specificFrameName = frame.getClass().getName();
                System.out.println(specificFrameName+"1");
                if (specificFrameName.equals("view.VerUsuarios")) {
                    VerUsuarios janela = (VerUsuarios) frame;
                    janela.preencher();
                    System.out.println("Preencher");
                }
            }

            System.out.println("Usuários lidos");
         }

        @Override
        public void onCancelled(DatabaseError arg0) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
       });

    static Usuario usuario= new Usuario();
    public static Usuario buscarUsuario(String id) {
           FirebaseDatabase database = FirebaseDatabase.getInstance();
           DatabaseReference ref = database.getReference("Usuario").child(id);
           done= new CountDownLatch(1);
           ref.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
           //se existir um usuario com o id especificado
           if(dataSnapshot.getKey()!=null)
           {
               usuario.setLogin("vazio"); 
               usuario.setId("nulo");
           }else{
               //Usuario encontrado também pode ser lido desta forma
               usuario=dataSnapshot.getValue(Usuario.class);
               usuario.setId(dataSnapshot.getKey());
           }
                      
           done.countDown();
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
             // ...
           }
        });
           
        try {
            done.await(); //esperar dados do firebase
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
           
        return usuario;
    }

   


    
}
