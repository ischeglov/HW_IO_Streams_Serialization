import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Launcher {

    public static void main(String[] args) {
        /*задание 1*/
        StringBuilder builder = new StringBuilder();
        // создаем папку "Games"
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games", builder, "\nДиректория Games создана");

        // создаем директории в папке "Games"
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/srs", builder, "\nДиректория srs создана");
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/res", builder, "\nДиректория res создана");
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames", builder, "\nДиректория savegames создана");
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/temp", builder, "\nДиректория temp создана");

        // в каталоге "src" создаем две директории: "main, test"
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/srs/main", builder, "\nДиректория srs/main создана");
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/srs/test", builder, "\nДиректория srs/test создана");

        // в подкаталоге "main" создаем два файла: Main.java, Utils.java.
        createFile("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/srs/main/Main.java", builder, "\nФайл Main.java был создан");
        createFile("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/srs/main/Utils.java", builder, "\nФайл Utils.java был создан");

        // в каталоге "res" создаем три директории: "drawables, vectors, icons"
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/res/drawables", builder, "\nДиректория res/drawables создана");
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/res/vectors", builder, "\nДиректория res/vectors создана");
        createFolder("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/res/icons", builder, "\nДиректория res/icons создана");

        // в директории "temp" создаем файл "temp.txt"
        createFile("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/temp/temp.txt", builder, "\nФайл temp.txt был создан");

        String text = builder.toString();
        System.out.println(text);
        try (FileWriter writer = new FileWriter("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/temp/temp.txt", false)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /*задание 2*/
        // cохранение сериализованных объектов GameProgress в папку "savegames"
        saveGame("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save.dat", new GameProgress(1,2,3,4));
        saveGame("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save2.dat", new GameProgress(5,6,7,8));
        saveGame("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save3.dat", new GameProgress(9,10,11,12));

        // созданные файлы сохранений из папки "savegames" запаковываем в архив zip
        List<String> listZipFiles = new ArrayList<>();
        listZipFiles.add("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save.dat");
        listZipFiles.add("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save2.dat");
        listZipFiles.add("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save3.dat");

        zipFiles("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/zip.zip", listZipFiles);

        // удаляем файлы, не лежащие в архиве
        for (int i = 0; i < listZipFiles.size(); i++) {
            File fileDelete = new File(listZipFiles.get(i));
            if (fileDelete.delete()) {
                System.out.println("Файл " + fileDelete.getName() + " удален из папки");
            }
        }
        System.out.println();

        /*задание 3*/
        // распаковка из zip архива
        openZip("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/zip.zip", "/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/");

        // считывание и десериализация одного из разархивированных файлов
        System.out.println(openProgress("/Users/imshcheglov/Desktop/JD-51 HW/5. Java Core/3. Потоки ввода-вывода. Работа с файлами. Сериализация/Games/savegames/save2.dat"));
    }

    public static void createFolder(String path, StringBuilder builder, String text) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
            builder.append(text);
        } else {
            System.out.println("Директория существует");
        }
    }

    public static void createFile(String path, StringBuilder builder, String text) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                builder.append(text);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path, true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(gameProgress);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String zipName, List<String> list) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));
        ) {
            {
                for (int i = 0; i < list.size(); i++) {
                    File file = new File(list.get(i));
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    // считываем содержимое файла в массив byte
                    byte[] buffer = new byte[bis.available()];
                    bis.read(buffer);
                    // добавляем содержимое к архиву
                    zos.write(buffer);
                    // закрываем текущую запись для новой записи
                    zos.closeEntry();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openZip(String zipName, String pathFolder) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipName))) {
            ZipEntry zipEntry;
            String name;
            while ((zipEntry = zis.getNextEntry()) != null) {
                name = zipEntry.getName(); // получаем название файла
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathFolder + name))) {
                    for (int i = zis.read(); i != -1; i = zis.read()) {
                        bos.write(i);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static GameProgress openProgress(String pathGame) {
        GameProgress gameProgress = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathGame))) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
