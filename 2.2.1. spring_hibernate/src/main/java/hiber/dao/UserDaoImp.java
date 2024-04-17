package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired //Добавил связь Бинов через Сеттер
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    //не нужен этот метод addCar(User user, Car car) , используй связь @OneToOne и каскадирование
//    @Override
//    public void addCar(User user, Car car) {
//        user.setCar(car);
//        sessionFactory.getCurrentSession().save(user);
//    }

    //В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
    @Override
    public User getUserForCarModelAndSeries(String model, int series) {
        String hql = "select user FROM User user WHERE user.car.model =:model and user.car.series =:series";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model);
        query.setParameter("series", series).uniqueResult();
        return (User) query.getSingleResult();
    }

}