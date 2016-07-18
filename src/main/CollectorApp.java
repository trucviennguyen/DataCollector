/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.CategorizedFacebookType;
import com.restfb.types.Comment;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Post;
import com.restfb.types.Post.Comments;
import com.restfb.types.Post.Likes;
import com.restfb.types.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;

import static util.Constants.accessToken;
import static util.Constants.encoding;
import static util.Constants.fileSeparator;
import static util.Constants.IDsFilename;
import static util.Constants.LOG;
import static util.Constants.INFO_TAIL;
import static util.Constants.POST_TAIL;
import static util.Intermediate.IDLst;
import static util.Intermediate.infoFilename;
import static util.Intermediate.postFilename;
import static util.Parameters.outputFilename;
import util.Parameters;

/**
 *
 * @author tvnguyen
 */
public class CollectorApp {
    
    static private CollectorApp collector;
    
    static FacebookClient facebookClient = null;
    static FacebookClient publicOnlyFacebookClient = null;

    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    
    private String id = null, name = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length < 1) {
            System.out.println("inputFilename outputFilename ...");
            return;
        }

        Parameters.processParameters(args);
        collector = new CollectorApp();
    }
    
    public CollectorApp() {
        // 
        // DefaultFacebookClient is the FacebookClient implementation
        // that ships with RestFB. You can customize it by passing in
        // custom JsonMapper and WebRequestor implementations, or simply
        // write your own FacebookClient instead for maximum control.
        File of = new File(outputFilename);
        if (!of.exists())
            of.mkdirs();

        facebookClient = new DefaultFacebookClient(accessToken);

        // It's also possible to create a client that can only access
        // publicly-visible data - no access token required. 
        // Note that many of the examples below will not work unless you supply an access token! 

        publicOnlyFacebookClient = new DefaultFacebookClient();
        readFacebookIDs();
        for (int i = 0; i < IDLst.size(); i++) {
            String fbID = IDLst.get(i);
            try {
                collectInfo(fbID);
                collectPosts(fbID);
            }
            catch (com.restfb.exception.FacebookGraphException graphEx) {
                // LOG.log(Level.SEVERE, graphEx.getMessage(), graphEx);
            }

        }
    }
    
    private void readFacebookIDs() {
        // 
        IDsFilename = Parameters.inputFilename + fileSeparator + IDsFilename;
        File df = new File(IDsFilename);
        System.out.println("Processing ID list " + df.getName() + ".. ");
        String st = null;

        try {
            // open reader
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(df), "UTF-8"));
            while ( (st = reader.readLine()) != null) {
                // 
                IDLst.add(st.trim());
            }

            reader.close();
        }
        catch (java.io.IOException ioEx) {
            LOG.log(Level.SEVERE, ioEx.getMessage(), ioEx);
        }
    }

    private void collectInfo(String fbID) {

        // For all API calls, you need to tell RestFB how to turn the JSON
        // returned by Facebook into Java objects.  In this case, the data
        // we get back should be mapped to the User and Page types, respectively.
        // You can write your own types too!
        try {
            infoFilename = outputFilename + fileSeparator + fbID + INFO_TAIL;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(infoFilename), encoding));
            User user = facebookClient.fetchObject(fbID, User.class);

            id = user.getId();
            name = user.getName();
            String username = user.getUsername(),
                   firstName = user.getFirstName(),
                   lastName = user.getLastName();
            writer.write("id" + "\t" + id + "\n");
            writer.write("username" + "\t" + username + "\n");
            writer.write("name" + "\t" + name + "\n");
            writer.write("firstName" + "\t" + firstName + "\n");
            writer.write("lastName" + "\t" + lastName + "\n");
            writer.close();
        }
        catch (java.io.FileNotFoundException fEx) {
            LOG.log(Level.SEVERE, fEx.getMessage(), fEx);
        }
        catch (java.io.UnsupportedEncodingException uEx) {
            LOG.log(Level.SEVERE, uEx.getMessage(), uEx);
        }
        catch (java.io.IOException ioEx) {
            LOG.log(Level.SEVERE, ioEx.getMessage(), ioEx);
        }

    }
    
    private void collectPosts(String fbID) {
        // For all API calls, you need to tell RestFB how to turn the JSON
        // returned by Facebook into Java objects.  In this case, the data
        // we get back should be mapped to the User and Page types, respectively.
        // You can write your own types too!
        try {
            postFilename = outputFilename + fileSeparator + fbID + POST_TAIL;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(postFilename), encoding));
            // List<Post> feeds = facebookClient.fetchConnection(fbID + "/feed", Post.class).getData();
            List<Post> posts = facebookClient.fetchConnection(fbID + "/posts", Post.class).getData();

            // Connections support paging and are iterable

            String msg = "";
            writer.write("id" + "\t" + id + "\n");
            writer.write("name" + "\t" + name + "\n");
            writer.write("Posts" + "\n");
            for (Post post : posts) {
                id = post.getId();
                msg = post.getMessage();
                if (msg == null)
                    msg = "";
                writer.write("id" + "\t" + id + "\n");
                writer.write("msg" + "\t" + msg + "\n");

                Likes plk = post.getLikes();
                if (plk != null) {
                    writer.write("\t" + "Likes" + "\n");
                    List<NamedFacebookType> pLikes = plk.getData();
                    for (NamedFacebookType ft : pLikes) {
                        writer.write("\t\t" + "id" + "\t" + ft.getId() + "\n");
                        writer.write("\t\t" + "name" + "\t" + ft.getName() + "\n");
                    }
                }

                Comments pct = post.getComments();
                if (pct != null) {
                    writer.write("\t" + "Comments" + "\n");
                    List<Comment> cts = pct.getData();
                    for (Comment comment : cts) {
                        msg = comment.getMessage();
                        if (msg == null)
                            msg = "";
                        writer.write("\t\t" + "id" + "\t" + comment.getId() + "\n");
                        writer.write("\t\t" + "msg" + "\t" + msg + "\n");
                        CategorizedFacebookType from = comment.getFrom();
                        writer.write("\t\t" + "fromPersonID" + "\t" + from.getId() + "\n");
                        writer.write("\t\t" + "name" + "\t" + from.getName() + "\n");
                    }
                }

                writer.write("\n");
            }
            writer.close();
        }
        catch (java.io.FileNotFoundException fEx) {
            LOG.log(Level.SEVERE, fEx.getMessage(), fEx);
        }
        catch (java.io.UnsupportedEncodingException uEx) {
            LOG.log(Level.SEVERE, uEx.getMessage(), uEx);
        }
        catch (java.io.IOException ioEx) {
            LOG.log(Level.SEVERE, ioEx.getMessage(), ioEx);
        }
    }
}
