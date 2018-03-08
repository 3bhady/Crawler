import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Crawler implements Runnable {
    //public static Queue<String> q = new LinkedList<String>();
    public static Queue<String> q = new ConcurrentLinkedQueue<String>();
    public static Set visitedLinks = new HashSet();
    private int id;
    private String initalURL;
    public Crawler(int i,String url) {
        id=i;
        initalURL=url;
    }

    public void run() {
        try {
            Crawl(initalURL);
        } catch (Exception e) {
            System.out.println(e);
        }

        //String url = args[0];
        /*String url = "http://eng.cu.edu.eg/";
        System.out.println("Fetching " + url + " ...");

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        //Elements media = doc.select("[src]");
        //Elements imports = doc.select("link[href]");
        //String safe = Jsoup.clean(unsafe, Whitelist.basic());

        //System.out.printf("\nLinks: (%d)", links.size());
        for (Element link : links) {
            //System.out.println(link.attr("abs:href"));
            System.out.printf(" * a: <%s>  (%s)\n", link.attr("abs:href"),trim(link.text(),35) );
        }
        */
    }

    public void Crawl(String url) throws IOException {
        //TODO:should this be in a while loop??
        while(true) {
            try {
                Document doc = Jsoup.connect(url).get();
                //TODO: extract relative links and resolve dns individually (remove abs:)

                Elements links = doc.select("a[href]");
                //TODO:save in a database


                for (Element link : links) {
                    //System.out.println(link.attr("abs:href"));
                    synchronized (visitedLinks) {
                        //InetAddress giriAddress = java.net.InetAddress.getByName("www.girionjava.com");
                        //String address = giriAddress.getHostAddress();
                        String sanitizedURL =(link.attr("abs:href").split("#"))[0];
                        //sanitizedURL=address+"/"+sanitizedURL;
                        //System.out.println(sanitizedURL);
                        //System.exit(1);
                        int prevSize = visitedLinks.size();
                        visitedLinks.add(sanitizedURL);
                        if (prevSize < visitedLinks.size())//this is a new link
                        {
                            //TODO:sanitize the url and remove last # to eliminate duplicates


                            //TODO:save current q in case of stopping the process
                            System.out.println("id:" + id + ", " +sanitizedURL);
                            q.add(sanitizedURL);
                            
                        }
                    }
                    //System.out.printf(" * a: <%s>  (%s)\n", link.attr("abs:href"), trim(link.text(), 35));
                }
                //System.out.println(visitedLinks.size());
            } catch (Exception e) {
                //TODO:do something useful here Exception type:IOException
                System.out.println(e);
            }
            url=q.poll();
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }


}