package BoardServer;

import java.security.PublicKey;

import com.sun.org.apache.bcel.internal.classfile.Signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * All of these users are kept in a distributed hash table. This data is permanently kept in a blockchain.
 */
public class User
{
    /**
     * The username or handle of the user posting.
     */
    private byte[] name = new byte[Constants.NAME_SIZE];
    /**
     * The public key of the user posting.
     */
    private PublicKey publicKey;
    
    /**
     * The hash of this users name and public key.
     */
    private byte[] id = new byte[Constants.HASH_SIZE];
    
    /**
     * The signature of the users name. This is to verify the user belongs 
     */
    private byte[] signature = new byte[Constants.SIGNATURE_SIZE];

    /**
     * 
     * @return The ID of the user
     */
    public byte[] getId()
    {
        return id;
    }
    /**
     * 
     * @return The name of the user.
     */
    public byte[] getName()
    {
        return name;
    }

    /**
     * 
     * @return The public key of the user.
     */
    public PublicKey getPublicKey()
    {
        return publicKey;
    }

    /**
     * Creates a new user from thier public key and signature.
     * @param name Must be NAME_SIZE in length. Refers to the username of the user. This name means nothing.
     * @param pubkey The public key of the user.
     * @param signature_ The signature of the users name.
     */
    public User(byte[] name_, PublicKey pubkey, Signature signature_)
    {
        publicKey = pubkey;
        name = name_;
        //Hash the name and public key.
        try{
            //Hash the users name and public key for thier id they can be refered to by.
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            hasher.update(Post.joinArray(name,publicKey.getEncoded()));
            id = hasher.digest();
            //Verify the users signature
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println(e.toString());
        }
        

    } 

}