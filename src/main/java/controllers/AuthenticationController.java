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
     **/
    public static boolean Authenticate(String username, char[] password)//, String authLevelOverride
    {
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
//                        if (authLevelOverride.equals(""))
//                        authLevel = rsAuth.getString("authLevel");
                        isAuth = true;
                    }

                    System.out.println("isAuth: " + isAuth);
                    System.out.println("current user: " + activeUser);
                    return true;
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
     **/
    public static boolean newAccount(String username, char[] password)//, String authL, String email
    {
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

        //  leave commented unless debugging for security
//        System.out.println(String.valueOf(password));
//        System.out.println(b64_String(hash_pass));
//        System.out.println(salt);

//        String sql = "INSERT INTO Authentication (username, password, salt, authLevel, email) VALUES ('"
//                + username + "', " + b64_String(hash_pass) + ", " + b64_String(salt) + ", '" + authL + "', '" + email
//                + "');";

//, authLevel, email
        String sql = "INSERT INTO Authentication (username, password, salt) VALUES ('"
                + username + "', " + "?" + ", " + "?" +  "');"; //", '" + authL + "', '" + email +

        Database.executeAsyncUpdate(sql, b64_String(hash_pass), salt);

        Database.close();
        return true;
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
