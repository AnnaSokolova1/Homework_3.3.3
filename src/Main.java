import java.io.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        File srcDir = new File("C:/Games/src");
        File resDir = new File("C:/Games/res");
        File tempDir = new File("C:/Games/temp");
        File mainDir = new File(srcDir, "main");
        File tempFile = new File(tempDir, "temp.txt");
        makeDir(srcDir);
        makeDir(resDir);
        makeDir(new File("C:/Games/savegames"));
        makeDir(tempDir);
        makeDir(mainDir);
        makeDir(new File(srcDir, "test"));
        makeFile(new File(mainDir, "Main.java"));
        makeFile(new File(mainDir, "Utils.java"));
        makeDir(new File(resDir, "drawables"));
        makeDir(new File(resDir, "vectors"));
        makeDir(new File(resDir, "icons"));
        makeFile(tempFile);
        String text = sb.toString();
        writeToTxt(text, tempFile);
    }

    public static void makeDir(File file) {
        message(file.mkdir(), "Создан каталог", file);
    }

    public static void makeFile(File file) {
        try {
            message(file.createNewFile(), "Файл", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void message(Boolean isMaked, String type, File file) {
        if (isMaked) {
            sb.append(type + " " + file.getName() + "\n");
        } else {
            sb.append(file.getName() + " не создан \n");
        }
    }

    public static void writeToTxt(String text, File tempFile) {
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}