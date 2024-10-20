import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(300, 10, 41, 3.5);
        GameProgress player2 = new GameProgress(205, 20, 33, 6.5);
        GameProgress player3 = new GameProgress(150, 15, 48, 4.5);
        saveGames("D:\\Games\\savegames\\save1.dat", player1);
        saveGames("D:\\Games\\savegames\\save2.dat", player2);
        saveGames("D:\\Games\\savegames\\save3.dat", player3);
        List<String> stringList = new ArrayList<String>();
        stringList.add("D:\\Games\\savegames\\save1.dat");
        stringList.add("D:\\Games\\savegames\\save2.dat");
        stringList.add("D:\\Games\\savegames\\save3.dat");
        zipFile("D:\\Games\\savegames\\zipSave.zip", stringList);


    }

    private static void saveGames(String text, GameProgress progress) {
        try {
            FileOutputStream outputStream = new FileOutputStream(text);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(progress);

            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void zipFile(String filename, List<String> stringList) {
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(filename));
            for (String saveFile : stringList) {
                File saveFileName = new File(saveFile);
                ZipEntry entry = new ZipEntry(saveFileName.getName());
                FileInputStream fis = new FileInputStream(saveFile);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.putNextEntry(entry);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                File file = new File(saveFile);
                file.delete();
            }
            zout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}