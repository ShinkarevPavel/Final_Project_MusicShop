package com.shinkarev.musicshop.main;

import com.shinkarev.musicshop.dao.impl.InstrumentDaoImpl;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import com.shinkarev.musicshop.service.impl.UserServiceImpl;
import com.shinkarev.musicshop.util.PasswordHashGenerator;

import java.util.Optional;


public class MainProject {
    public static void main(String[] args) {
//        String sql = "SELECT instrument_id, name, brand, country, price, description, rating, status, type FROM instruments" +
//                " LEFT JOIN instruments_statuses ON instruments.status_id=id LEFT JOIN instruments_types ON instruments.instrument_type=type_id";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url = "jdbc:mysql://localhost:3306/musician_instruments";
//        Properties properties = new Properties();
//        properties.put("user", "root");
//        properties.put("password", "241987");
//        properties.put("autoReconnect", "true");
//        properties.put("characterEncoding", "UTF-8");
//        properties.put("useUnicode", "true");
//        properties.put("useSSL", "true");
//
//        /*This is the protect from SQL injections*/
//        try (Connection connection = DriverManager.getConnection(url, properties)){
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("brand"));
//                System.out.println(resultSet.getString("price"));
//                System.out.println(resultSet.getString("rating"));
//                System.out.println(resultSet.getString("description").length());
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        UserDaoImpl userDao = new UserDaoImpl();
//        try {
//            List<User> users = userDao.findAll();
//            for (User u : users) {
//                System.out.println(u);
//            }
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//        InstrumentDaoImpl instrumentDao = new InstrumentDaoImpl();

////        List<Instrument> instruments = null;
////        Optional<Instrument> instrument = null;
//        try {
//            Optional<Instrument> optional = instrumentDao.findEntityById(101L);
//            Optional<User> optionalUser = userDao.findUserByLogin("voodoopavel");
//            User user = optionalUser.get();
//            System.out.println(user);
//            Instrument instrument = optional.get();
//            System.out.println(instrumentDao.getInstrumentRating(instrument));
//
////            System.out.println(instrumentDao.create(new Instrument("Gibson Explorer", "Gibson", "USA", 1540, 5, "J.Hatfields guitar", InstrumentStatusType.AVAILABLE, InstrumentType.GUITARS )));
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//        System.out.println(instrument.get());

//        List<String> list1 = new ArrayList<>();
//        list1.add("Ira");
//        list1.add("Pavel");
//        list1.add("Masha");
////        list1.add("Goni");
//
//        List<String> list2 = new ArrayList<>();
//        list2.add("Masha");
//        list2.add("Ira");
//        list2.add("Pavel");
//        System.out.println(list1.containsAll(list2));
    }
}
