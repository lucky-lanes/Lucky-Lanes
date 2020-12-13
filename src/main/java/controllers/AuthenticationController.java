package main.java.controllers;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import main.java.Database;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;

public class AuthenticationController
{
    public static boolean isAuth = false;
    public static int activeUserID = -1;
    public static String activeUser = "";
    public static String authLevel = "None";


    /**
     * Authenticates a user by comparing entered username/password to records in database
     * returns a boolean confirming that the operation was successful
     **/
    public static boolean Authenticate(String username, char[] password)
    {
        // This is a sanity check to make sure that the static variables are always reset before login
        if (isAuth) {
            System.out.println("A user is already authorized, logout was not properly executed.");
            isAuth = false;
        }
        Database.connect();

        ResultSet rsAuth = Database.searchQuery("SELECT * FROM Authentication;");

        try {
            while(rsAuth.next())
            {
                String row_user = rsAuth.getString("username");
                if(username.equals(row_user))
                {
                    String row_passhash = null;
                    String row_salt = null;

                    row_passhash = rsAuth.getString("password");
                    row_salt = rsAuth.getString("salt");

                    byte[] entered_pass_hash = getPasswordHash(password, string_B64(row_salt), 10000);

                      // leave commented unless debugging for security
                    System.out.println(String.valueOf(password));
                    System.out.println(b64_String(entered_pass_hash));
                    System.out.println(row_passhash);
                    System.out.println(row_salt);

                    if(row_passhash.equals(b64_String(entered_pass_hash)))
                    {
                        activeUserID = rsAuth.getInt("ID");
                        activeUser = row_user;
                        authLevel = rsAuth.getString("authLevel");
                        isAuth = true;
                    }

                    System.out.println("isAuth: " + isAuth);
                    System.out.println("current user: " + activeUser);
                    return isAuth;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Database.close();
        }
        return isAuth;
    }

    /**
     * Adds a new account to the database
     * returns a boolean confirming that the operation was successful
     **/
    public static boolean newAccount(String username, char[] password, String authL)//, String authL, String email
    {
        boolean valid = false;
        Database.connect();
        ResultSet rsAuth = Database.searchQuery("SELECT * FROM Authentication;");
        try {
            while (rsAuth.next()) {
                String row_user = rsAuth.getString("username");
                if (username.equals(row_user)) {
                    System.out.println("username is already taken");
                    return false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String salt = generateSalt();
        byte[] hash_pass = getPasswordHash(password, string_B64(salt), 10000);

        //leave commented unless debugging for security
       //System.out.println(String.valueOf(password));
      //System.out.println(b64_String(hash_pass));
     //System.out.println(salt);

        String sql = "INSERT INTO Authentication (username, password, salt, authLevel) VALUES ('"
                + username + "', " + "?, " + "?, '" + authL + "');";

        String[] vars = new String[2];
        vars[0] = b64_String(hash_pass);
        vars[1] = salt;
        valid = Database.executeAsyncUpdate(sql, vars);

        Database.close();
        return valid;
    }

    /**
     * function should be called when moving back to the login/sign up screen.
     * This simply cleans up the global variables to be sure that nothing can go horribly wrong.
     */
    public static void logOut()
    {
        isAuth = false;
        activeUserID = -1;
        activeUser = "";
        authLevel = "None";
    }

    /**
     * Function must be called by an admin, to change a new account to an admin or coach
     * returns a boolean confirming that the operation was successful
     */
    public static boolean changeAuthLevel(String user, String newAuthL)
    {
        boolean valid = false;
        if (authLevel.equals("Admin"))
        {
            Database.connect();
            String sql = "UPDATE Authentication SET authLevel = ? " + "WHERE username = " + user + ";";

            String[] vars = new String[1];
            vars[0] = newAuthL;
            valid = Database.executeAsyncUpdate(sql, vars);
        }
        Database.close();
        return valid;
    }
    
    
    /**
     * above function overloaded for authlevel assignment by username OR id.
     */
    public static boolean changeAuthLevel(int userID, String newAuthL)
    {
        boolean valid = false;
        if (authLevel.equals("Admin"))
        {
            Database.connect();
            String sql = "UPDATE Authentication SET authLevel = ? " + "WHERE ID = " + userID + ";";

            String[] vars = new String[1];
            vars[0] = newAuthL;
            valid = Database.executeAsyncUpdate(sql, vars);
        }
        Database.close();
        return valid;
    }


    /**
     * as admin, delete an account via its userID
     */
    public static boolean deleteAccount(int userID)
    {
        boolean valid = false;
        if (authLevel.equals("Admin"))
        {
            Database.connect();
            String sql = "DELETE FROM Authentication WHERE ID = " + userID + ";";

            Database.executeUpdate(sql);
            valid = true; // might not be representative if executeUpdate fails
        }
        Database.close();
        return valid;
    }

    /**
     * User enters old password to change to a new password
     * returns a boolean confirming that the operation was successful
     */
    public static boolean changePassword(char[] oldPass, char[] newPass)
    {
        boolean valid = false;
        if (Authenticate(activeUser, oldPass))
        {
            Database.connect();
            String salt = generateSalt();
            byte[] hash_pass = getPasswordHash(newPass, string_B64(salt), 10000);

            String sql = "UPDATE Authentication SET password = ?, salt = ? " +
                    "WHERE username = " + activeUser.toString() + ";";

            String[] vars = new String[2];
            vars[0] = b64_String(hash_pass);
            vars[1] = salt;
            valid = Database.executeAsyncUpdate(sql, vars);
        }
        return valid;
    }

    /**
     * helper function to convert a password to a hash along with a given salt
     **/
    private static byte[] getPasswordHash(char[] password, byte[] salt, int iterations)
    {
        try {
            KeySpec spec = new PBEKeySpec(password, salt, iterations, 256);

            SecretKeyFactory alg = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return alg.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println("Password Hashing Failed!");
        return null;
    }

    /**
     * helper function for salt generation
     **/
    private static String generateSalt()
    {
        Random SecRandom = new SecureRandom();
        byte[] salt = new byte[16];
        SecRandom.nextBytes(salt);
        return b64_String(salt);
    }

    /**
     * helper function for string to base64 decoding
     **/
    public static byte[] string_B64(String s) {
        return Base64.getDecoder().decode(s);
    }

    /**
     * helper function for base64 to string encoding
     **/
    public static String b64_String(byte[] s) {
        return Base64.getEncoder().encodeToString(s);
    }
}
