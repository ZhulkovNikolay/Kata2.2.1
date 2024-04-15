package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

//добавил сюда аннотацию @Transactional. Без него вылетает ошибка
//Could not obtain transaction-synchronized Session for current thread
@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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

    //добавляем машину Юзеру
    @Override
    public void addCar(User user, Car car) {
        user.setCar(car);
        sessionFactory.getCurrentSession().save(user);
    }

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