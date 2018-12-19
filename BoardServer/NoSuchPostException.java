/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardServer;

/**
 *
 * @author jsuess
 */
public class NoSuchPostException extends Exception {

    /**
     * Creates a new instance of <code>NoSuchPostException</code> without detail
     * message.
     */
    public NoSuchPostException() {
    }

    /**
     * Constructs an instance of <code>NoSuchPostException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoSuchPostException(String msg) {
        super(msg);
    }
}
