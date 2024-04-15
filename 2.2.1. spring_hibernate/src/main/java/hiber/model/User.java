package hiber.model;

import javax.persistence.*;


//Uni-directional отношение. У Юзера может быть много машин. Но у машины может быть 1 Юзер.
@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   //У Юзера будет ссылка на машину по связи через id. Все события в БД связанные с Юзером будут (CascadeType) отражаться и на машине
   @OneToOne (cascade = CascadeType.ALL)
   @JoinColumn(name = "car_id",referencedColumnName = "id")
   private Car car;

   public User() {}

   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   //Добавим методы, чтобы у Юзера можно будет установить/забрать его машину.
   public Car getCar() {
      if (car == null) {
         throw new NullPointerException("User has no Car");
      } else {
         return car;
      }

   }

   public void setCar(Car car) {
      this.car = car;
   }
}