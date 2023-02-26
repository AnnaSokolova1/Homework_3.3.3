import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveMain {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("C:/Games/savegames/save1.dat");
        arrayList.add("C:/Games/savegames/save2.dat");
        arrayList.add("C:/Games/savegames/save3.dat");
        GameProgress gameProgress1 = new GameProgress(5, 10, 1, 10);
        GameProgress gameProgress2 = new GameProgress(6, 11, 2, 20);
        GameProgress gameProgress3 = new GameProgress(7, 12, 3, 30);

        saveGame(arrayList.get(0), gameProgress1);
        saveGame(arrayList.get(1), gameProgress2);
        saveGame(arrayList.get(2), gameProgress3);

        zipFiles("C:/Games/savegames/zip.zip", arrayList);
        for (String path : arrayList) {
            File file = new File(path);
            file.delete();
        }
    }

    public static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (int i = 0; i < list.size(); i++) {
                try (FileInputStream fis = new FileInputStream(list.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + i + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}