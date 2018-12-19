package BoardServer;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.InetAddress;


/**
 * This class provides controls for the client program to use.
 * @author jsuess
 */
public class BoardServer 
{
        
    //Cache of users.
    private static ArrayList<User> userCache = new ArrayList();
    
    //List of trusted ip addresses where users and posts can be looked up. You would typically add your friends to this list.
    private static ArrayList<InetAddress> trustedIps = new ArrayList();
    
    //Cache of all posts.
    private static ArrayList<Post> postCache = new ArrayList()
    /**
     * Looks up a user in the user cache. If the user does not exist in the cache, it will ask to perform a lookup to the nearest node.
     * @param userId The id of the user to lookup.
     * @return Returns null, unimplemented as of yet.
     * @throws NoSuchUserException If a user cannot be found by some means.
     */
    public static User lookupUser(byte[] userId) throws NoSuchUserException
    {
        for (User u : userCache)
        {
            if (Arrays.equals(userId,u.getId()))
            {
                return u;
            }
        }
        throw (new NoSuchUserException("No user found in local cache"));
    }
    public static Post lookupPost(byte[] postId) throws NoSuchPostException
    {
        for (Post p : postCache)
        {
            if (Arrays.equals(postId,p.getId()));
        }
    }
    /**
     * Adds a user to the user cache
     * @param w The new user.
     */
    public static void registerNewUser(User w)
    {
        userCache.add(w);
    }
}