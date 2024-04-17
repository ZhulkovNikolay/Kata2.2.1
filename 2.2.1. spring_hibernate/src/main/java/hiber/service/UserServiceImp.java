package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private UserDao userDao;

   @Autowired //Добавил связь Бинов через Сеттер
   public void setUserDao(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   //не нужен этот метод addCar(User user, Car car) , используй связь @OneToOne и каскадирование
  // @Override
  // public void add(User user, Car car) {
  //    userDao.addCar(user, car);
  // }

   //В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
   @Transactional(readOnly = true) //разобрался что такое @Transactional и перенес его из ДАО слоя сюда
   @Override
   public User getUserForCarModelAndSeries(String model, int series) {
      return userDao.getUserForCarModelAndSeries(model, series);
   }


}