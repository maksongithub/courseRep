package main;
import java.sql.SQLException;

public class Menu {
    public static void mainMenuPrint(){
        DatabaseController dbController = new DatabaseController();

        System.out.println("Программа 'Поздравлятор' \n Программа успешно запущена\n");
        dbController.nearestDates();
        System.out.println("1.Чтобы вывести в консоль БД нажмите 1\n" +
                "*Взаимойдествовать с БД возможно только после выполнения пункта №1\n" +
                "\n" +
                "2.Чтобы закрыть программу нажмите 0\n");
    }
    public static void secondMenuPrint(){
        System.out.println("1.Чтобы отобразить БД (применяется после изменения БД) нажмите 1\n" +
                "2.Чтобы добавить человека в БД нажмите 2\n" +
                "3.Чтобы удалить человека из БД нажмите 3\n" +
                "4.Чтобы изменить данные человека в БД нажмите 4\n" +
                "5.Чтобы отсортировать БД по датам рождения в году нажмите 5\n" +
                "\n" +
                "6.Чтобы закрыть программу нажмите 0\n");
    }
    public static void mainMenuChoice(int numMenu) {
        DatabaseController dbController = new DatabaseController();
        switch (numMenu) {
            case 0 : System.exit(0);
                break;
            case 1 : dbController.databasePrint();
                break;
            default:
                System.out.println("???");
        }
    }
    public static void secondMenuChoice(int numMenu) {
        DatabaseController dbController = new DatabaseController();
        switch (numMenu) {
            case 0 : System.exit(0);
                break;
            case 1 : dbController.databasePrint();
                break;
            case 2 : {
                try {
                    dbController.getDbConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    dbController.addingHuman();
                    System.out.println("Новый человек добавлен.");
                    Menu.secondMenuPrint();
                }
                break;
            }
            case 3: {
                try {
                    dbController.getDbConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    dbController.removingHuman();
                    System.out.println("Человек удален");
                    Menu.secondMenuPrint();
                }

                break;
            }
            case 4: {
                try {
                    dbController.getDbConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    dbController.updateTable();
                    System.out.println("Данные изменены");
                    Menu.secondMenuPrint();
                }
                break;
            }
            case 5:
                try {
                    dbController.getDbConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    dbController.databaseSortByBirthday();
                    System.out.println("Таблица отсортиванна");
                    Menu.secondMenuPrint();
                }
                break;
            default:
                System.out.println("???");
        }
    }
}
