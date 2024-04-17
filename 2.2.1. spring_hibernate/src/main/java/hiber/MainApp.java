package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"),new Car("Car1", 1));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"),new Car("Car2", 2));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"),new Car("Car3", 3));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"),new Car("Car4", 4));

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("Car1", 1);
        Car car2 = new Car("Car2", 2);
        Car car3 = new Car("Car3", 3);
        Car car4 = new Car("Car4", 4);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar()); // по заданию "добавьте их в базу данных, вытащите обратно."
            System.out.println();
        }

        //В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
        User userToSearch = userService.getUserForCarModelAndSeries("car2", 2);
        System.out.println("Достаем конкретного юзера по модели и номеру машины: ");
        System.out.println("Id = " + userToSearch.getId());
        System.out.println("First Name = " + userToSearch.getFirstName());
        System.out.println("Last Name = " + userToSearch.getLastName());
        System.out.println("Email = " + userToSearch.getEmail());
        System.out.println("Car = " + userToSearch.getCar().getModel() + " " + userToSearch.getCar().getSeries());
        context.close();
    }
}