package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   List<User> listUsers();

   //не нужен этот метод addCar(User user, Car car) , используй связь @OneToOne и каскадирование
   //void addCar(User user, Car car);

   //В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
   User getUserForCarModelAndSeries(String model, int series);

}