import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Spider {
    public static void main(String[] args) throws IOException {

        try {
            File file = new File("seed.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i=1;
            while ((line = bufferedReader.readLine()) != null) {

                (new Thread(new Crawler(i,line))).start();
                (new Thread(new Crawler(i*10,line))).start();
                (new Thread(new Crawler(i*100,line))).start();
                i++;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
