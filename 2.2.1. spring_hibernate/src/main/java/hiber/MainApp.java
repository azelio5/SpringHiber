package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@gmail.com");
      User user2 = new User("User2", "Lastname2", "user2@gmail.com");
      User user3 = new User("User3", "Lastname3", "user3@gmail.com");
      User user4 = new User("User4", "Lastname4", "user4@gmail.com");

      Car car1 = new Car("Car1", 2021);
      Car car2 = new Car("Car2", 2022);
      Car car3 = new Car("Car3", 2023);
      Car car4 = new Car("Car4", 2024);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println(" ");
      }


      System.out.println(userService.getUserByCar("Car1", 2021));
      System.out.println(" ");


      try {
         User notFoundUser = userService.getUserByCar("Car5", 2022);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println(" ");
      }

      context.close();
   }
}
