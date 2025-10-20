

public class Person {

   private int adressid;
   private String firstname;
   private String lastname;
   private String emial;
   private String phonenumber;
   
   public Person(){
      
    }
   public Person(int id,String first,String last, String emailadress,String phone){
       setadressId(id);
       setfirstName(first);
       setlastName(last);
       setEmail(emailadress);
       setPhoneNumber(phone);
       
    } 
   public void setadressId(int id){
       adressid=id;
   }
   public int getadressId(){
       return adressid;
   }
   public void setfirstName(String first){
       firstname=first;
   }
   public String getfirstName(){
       return firstname;
   }
   public void setlastName(String last){
       lastname=last;
   }
   public String getlastName(){
       return lastname;
   }
   public void setEmail(String email){
        emial=email;
   }
   public String getEmail(){
       return emial;
   }
   public void setPhoneNumber(String phone){
       phonenumber=phone;
   }
   public String getphone(){
       return phonenumber;
   }
}
