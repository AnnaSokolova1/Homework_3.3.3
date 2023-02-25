import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class LoadingMain {

    public static void main(String[] args) {
        openZip("C:/Games/savegames/zip.zip", "C:/Games/savegames");
        System.out.println(openProgress("C:/Games/savegames/save1.dat").toString());
    }

    public static void openZip(String zipPath, String filePath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(filePath + "/" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String filePath) {
        GameProgress gameProgress;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            gameProgress = (GameProgress) ois.readObject();
            return gameProgress;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}