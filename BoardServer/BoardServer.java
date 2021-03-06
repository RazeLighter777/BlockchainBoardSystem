package BoardServer;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.InetAddress;
import java.net.*;
import java.io.*;
import com.google.gson.*;

/**
 * This class provides controls for the client program to use.
 *
 * @author jsuess
 */
public class BoardServer {

    //Cache of users.
    private static ArrayList<User> userCache = new ArrayList();

    //List of trusted ip addresses where users and posts can be looked up. You would typically add your friends to this list.
    private static ArrayList<InetAddress> trustedIps = new ArrayList();

    //Cache of all posts.
    private static ArrayList<Post> postCache = new ArrayList();

    /**
     * Looks up a user in the user cache. If the user does not exist in the
     * cache, it will ask to perform a lookup to the nearest node.
     *
     * @param userId The id of the user to lookup.
     * @return Returns null, unimplemented as of yet.
     * @throws NoSuchUserException If a user cannot be found by some means.
     */
    public static User lookupUser(byte[] userId) throws NoSuchUserException {
        for (User u : userCache) {
            if (Arrays.equals(userId, u.getId())) {
                return u;
            }
        }
        throw (new NoSuchUserException("No user found in local cache"));
    }

    /**
     * Looks up a post in the post cache. If the post does not exist in the
     * cache, it will ask to perform a lookup to the nearest node.
     *
     * @param postId The id of the post to look up.
     * @return The post
     * @throws NoSuchPostException If the post cannot be found by some means.
     */
    public static Post lookupPost(byte[] postId) throws NoSuchPostException {
        for (Post p : postCache) {
            if (Arrays.equals(postId, p.getId())) {
                return p;
            }
        }
        throw (new NoSuchPostException("No post found in local cache"));
    }

    /**
     * Sends a JSON document to the given IP addresses
     *
     * @param ips The IP addresses to send the document to.
     * @param doc The actual document.
     */
    public static void sendJSONRequest(ArrayList<InetAddress> ips, Gson doc) {
        for (InetAddress ip : ips) {
            try {
                Socket sock = new Socket(ip, 4096);
                DataOutputStream out = new DataOutputStream(sock.getOutputStream());
                String documentString = doc.toString();
                out.write(documentString.getBytes());
                sock.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        }

    }

    /**
     * Looks up posts that are in a response to a particular post.
     *
     * @param postId The post you wish to find the responses to.
     * @param number The desired number of posts.
     * @return An array containing all relevant posts.
     */
    public static ArrayList<Post> getPostsInResponseTo(byte[] postId, int number) {
        return new ArrayList<>();
    }

    /**
     * Adds a user to the user cache
     *
     * @param w The new user.
     */
    public static void registerNewUser(User w) {
        userCache.add(w);
    }

    /**
     * Adds a post to the cache.
     *
     * @param p The new post.
     */
    public static void registerNewPost(Post p) {
        postCache.add(p);
    }

    public static void startServer() throws IOException {
        ServerSocket listener = new ServerSocket(4096);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    InputStreamReader rd = new InputStreamReader(socket.getInputStream());
                    BufferedReader stream = new BufferedReader(rd);
                    String inputLine;
                    String totalJson = "";
                    while (null != (inputLine = stream.readLine())) {
                        totalJson += inputLine;
                    }
                } finally {

                }
            }
        } finally {
            listener.close();
        }

    }
}
