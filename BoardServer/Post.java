package BoardServer;

import java.security.Signature;
import java.security.SignatureException;
import java.util.Date;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.stream.Stream;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.security.MessageDigest;

public class Post
{
    
    /**
     * The content of the post.
     */
    private byte[] content = new byte[Constants.POST_SIZE];
    /**
     * The hash of the user who made the post.
     */
    private byte[] userId = new byte[Constants.HASH_SIZE];
    
    /**
     * PostId of the post this post is in response to.
     */
    private byte[] responseId = new byte[Constants.HASH_SIZE];
    /**
     * Signature of the posts content, responseId, and the userId.
     */
    private byte[] signature = new byte[Constants.SIGNATURE_SIZE];
    /**
     * Hash of this posts content, signature, number, responseId and userId.
     */
    private byte[] postId = new byte[Constants.HASH_SIZE];
    /*
     * True if this post is a root post, meaning in response to no other posts. Requires extra rep to create
     */
    boolean isRootPost = false;

    /**
     * Joins multiple arrays of bytes
     * @param array Multiple parameters of byte arrays to be conjoined
     * @return The conjoined array
     */
    static byte[] joinArray(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }

        final byte[] result = new byte[length];

        int offset = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
    /**
     * 
     * @param postingUser The user object of the posting user.
     * @param userPrivateKey The private key of the posting user so thier post can be verified.
     * @param responseId The Post id of the post this is in response to.
     * @param content The contents of the post.
     */
    public Post(User postingUser, PrivateKey userPrivateKey, byte[] responseId_, byte[] content_, boolean isRootPost_)
    {   
        //Copy the content and the responseId to this class. 
        content = content_;
        //Set the users id.
        userId = postingUser.getId();
        //Is root post?
        isRootPost = isRootPost_;
        //Copy the response id of the post.
        if (!isRootPost)
        {
            responseId = responseId_;
        }
        //Get the signature of this posts content, responseId, and userId.
        try {
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initSign(userPrivateKey);
            rsa.update(joinArray(content, responseId, userId));
            signature = rsa.sign();

            //Get the hash of the post for the postId.
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            hasher.update(joinArray(content, responseId, userId, signature));
            postId = hasher.digest();
            
        } catch (NoSuchAlgorithmException e)
        {
            System.out.println("Error finding SHA256withRSA algorithm" + e.getMessage());
        }
        catch (InvalidKeyException e)
        {
            System.out.println("That key is not valid for some reason." + e.getMessage());
        }
        catch (SignatureException e)
        {
            System.out.println("Signature exception." + e.getMessage());
        }


    }


}