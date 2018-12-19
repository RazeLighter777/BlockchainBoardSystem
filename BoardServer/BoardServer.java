package BoardServer;

import java.security.PublicKey;

public class BoardServer 
{
    /**
     * Looks up a user in the user cache. If the user does not exist in the cache, it will ask to perform a lookup to the nearest node.
     * @param userId The id of the user to lookup.
     * @return Returns null, unimplemented as of yet.
     * @throws NoSuchUserException If a user cannot be found by some means.
     */
    public static User lookupUser(byte[] userId) throws NoSuchUserException
    {
        return null;
    }
}