package main;

import java.sql.*;
import java.util.Scanner;
//import java.util.Date;

public class DatabaseController extends Config {
    Connection dbConnection = null;
    // метод для подключения к БД
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }
    // метод чтобы добавить человека в БД
    public void addingHuman(){
        System.out.println("Добавляем нового человека в БД");
        Scanner in_name = new Scanner(System.in);
        Scanner in_birthday = new Scanner(System.in);

        System.out.print("Укажите имя нового человека (ФИО полностью): ");
        String new_name = in_name.nextLine();

        System.out.print("Укажите дату рождения нового человека в формате (ГГГГ-ММ-ДД): ");
        String new_birthdayInLine = in_birthday.nextLine();
        java.sql.Date new_birthday = java.sql.Date.valueOf(new_birthdayInLine);

        String insert = "INSERT INTO " + Constant.APP_TABLE + "(" + Constant.HUMAN_NAME + ","
                + Constant.BIRTHDAY_DATE + ")" + "VALUES(?,?)";
        try {
            PreparedStatement prepStat = getDbConnection().prepareStatement(insert);
            prepStat.setString(1,new_name);
            prepStat.setDate(2,new_birthday);
            prepStat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // метод чтобы удалить человека из БД
    public void removingHuman(){
        Scanner rem_id = new Scanner(System.in);
        System.out.println("Введите ИД человека, которого вы желаете удалить из списка: ");
        Integer id = rem_id.nextInt();

        String insert = "DELETE FROM " + Constant.APP_TABLE + " WHERE " + Constant.ID +" = (?)";
        PreparedStatement prepStat = null;
        try {
            prepStat = getDbConnection().prepareStatement(insert);
            prepStat.setInt(1,id);
            prepStat.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Возникла ошибка SQLException");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Возникла ошибка ClassNotFoundException");
            e.printStackTrace();
        }
        finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // метод изменения данных человека в БД
    public void updateTable(){
        Scanner p_id = new Scanner(System.in);
        Scanner p_choice = new Scanner(System.in);
        System.out.println("Введите ИД человека, данные которого желаете изменить: ");
        Integer in_id = p_id.nextInt();
        System.out.println("Вы желаете изменить имя (1) или дату дня рождения(2)?");
        Integer choice = p_choice.nextInt();
        switch (choice){
            case 1:{
                Scanner p_name = new Scanner(System.in);
                System.out.println("Введите новое имя: ");
                String ch_name = p_name.nextLine();
                String insert = "UPDATE " + Constant.APP_TABLE + " SET name " + "= (?) " + "WHERE id " + "= (?)";
                PreparedStatement prepStat = null;
                try {
                    prepStat = getDbConnection().prepareStatement(insert);
                    prepStat.setString(1,ch_name);
                    prepStat.setInt(2,in_id);
                    prepStat.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        dbConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 2:{
                Scanner p_birthdate = new Scanner(System.in);
                System.out.println("Введите новую дату в формате (ГГГГ-ММ-ДД): ");
                String new_birthdayInLine = p_birthdate.nextLine();
                java.sql.Date ch_date = java.sql.Date.valueOf(new_birthdayInLine);

                String insert = "UPDATE " + Constant.APP_TABLE + " SET birthday " + "= (?) " + "WHERE id " + "= (?)";
                PreparedStatement prepStat = null;
                try {
                    prepStat = getDbConnection().prepareStatement(insert);
                    prepStat.setDate(1,ch_date);
                    prepStat.setInt(2,in_id);
                    prepStat.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        dbConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            default:
                System.out.println("Некорректно, попробуйте ещё раз.");

        }
    }
    // метод получения и вывода данных из БД
    public void showingTable(){
        System.out.println("Полный список дней рождения: ");
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM birthdays");

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date date = resultSet.getDate(3);
                String dataBaseStr = String.format("%d. %s, - %te %tB, %tY\n",id,name,date,date,date);
                System.out.println(dataBaseStr);
            }
            Scanner userInput = new Scanner(System.in);
            Menu.secondMenuPrint();
            while (userInput.hasNext()){
                Menu.secondMenuChoice(userInput.nextInt());
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // метод ближайших дней рождения
    public void nearestDates(){
        System.out.println("Список ближайших дней рождения (ближайшие 30 дней): ");
        try {
            getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " +Constant.APP_TABLE +
                    " WHERE  DATE_ADD("+Constant.BIRTHDAY_DATE+", INTERVAL YEAR(CURDATE())-YEAR("+Constant.BIRTHDAY_DATE+") " +
                    "+ IF(DAYOFYEAR(CURDATE()) > DAYOFYEAR("+Constant.BIRTHDAY_DATE+"),1,0)YEAR)" +
                    "BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) order by dayofyear("+Constant.BIRTHDAY_DATE+")");

            while ((resultSet.next())){
                String name = resultSet.getString("name");
                Date birthday = resultSet.getDate("birthday");
                System.out.println("Имя:" + name);
                System.out.print("День рождения: " + birthday + "\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // метод сортировки БД по дате рождения
    public void databaseSortByBirthday(){
        // SQL запрос: select * from birthdays order by dayofyear(birthday), birthday;

        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from "+Constant.APP_TABLE +
                    " order by dayofyear("+ Constant.BIRTHDAY_DATE+")," + Constant.BIRTHDAY_DATE);

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date date = resultSet.getDate(3);
                String dataBaseStr = String.format("%d. %s, - %te %tB, %tY\n",id,name,date,date,date);
                System.out.println(dataBaseStr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // метод для вывода БД
    public static void databasePrint(){
        DatabaseController dbController = new DatabaseController();
        try {
            dbController.getDbConnection();
            dbController.showingTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("error: ClassNotFound exception");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: SQL exception");
        }
    }
}

