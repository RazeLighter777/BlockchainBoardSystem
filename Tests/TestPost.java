package Tests;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Arrays;

import BoardServer.Constants;

import java.security.PrivateKey;

public class TestPost {
    public static void main(String[] args)
    {
        try
        {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(512);
            KeyPair kp = kpg.generateKeyPair();
            PublicKey pub = kp.getPublic();
            PrivateKey pem = kp.getPrivate();
            
            byte[] content = new byte[Constants.POST_SIZE];
            content = (new String("Hello world!")).getBytes();
            byte[] name = new byte[Constants.NAME_SIZE];
            name = (new String("bobby")).getBytes();

            //Create a new user.
            BoardServer.User testUser = new BoardServer.User(name, pub, pem);
            //Create a new post.
            BoardServer.Post testPost = new BoardServer.Post(testUser, pem, testUser.getId(), content, true);

            System.out.println(Constants.bytesToHex(testUser.getId()));


        }
        catch (Exception e)
        {
            System.out.println("An exception was thrown: " + e.toString());
        }
    }
}