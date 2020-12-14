package main.java.controllers;

import main.java.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamController {

    public static boolean createTeam(String team_name)
    {
        boolean valid = false;

        if (AuthenticationController.authLevel == "Coach" || AuthenticationController.authLevel == "Admin")
        {
            Database.connect();

            ResultSet rsAuth = Database.searchQuery("SELECT * FROM TEAMS;");
            try {
                while (rsAuth.next()) {
                    String row_team_name = rsAuth.getString("team_name");
                    if (team_name.equals(row_team_name)) {
                        System.out.println("Team name is already taken");
                        return false;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            String sql = "INSERT INTO TEAMS (team_name, coach) VALUES (?, ?);";

            String[] vars = new String[2];
            vars[0] = team_name;
            vars[1] = AuthenticationController.activeUser;
            if (Database.executeAsyncUpdate(sql, vars))
            {
                sql = "CREATE TABLE ? (ID INT PRIMARY KEY AUTO_INCREMENT, team_member VARCHAR(256));";

                vars = new String[1];
                vars[0] = team_name;

                valid = Database.executeAsyncUpdate(sql, vars);
            }

            Database.close();
        }

        return valid;
    }

    public static boolean addTeamMember(String team_name, String username)
    {
        boolean valid = false;
        if (isCoachOfTeam(team_name) || AuthenticationController.authLevel == "Admin")
        {
            Database.connect();

            ResultSet rsAuth = Database.searchQuery("SELECT * FROM " + team_name + ";");
            try {
                while (rsAuth.next()) {
                    String row_team_member = rsAuth.getString("team_member");
                    if (username.equals(row_team_member)) {
                        System.out.println(username + " already in team.");
                        return false;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            String sql = "INSERT INTO " + team_name + " (team_member) VALUES (?);";

            String[] vars = new String[1];
            vars[0] = username;
            valid = Database.executeAsyncUpdate(sql, vars);

            Database.close();
        }

        return valid;
    }

    public static boolean removeTeamMember(String team_name, String username)
    {
        boolean valid = false;
        if (isCoachOfTeam(team_name) || AuthenticationController.authLevel == "Admin")
        {
            Database.connect();

            String sql = "DELETE FROM " + team_name + " WHERE team_member = ?;";

            String[] vars = new String[1];
            vars[0] = username;
            valid = Database.executeAsyncUpdate(sql, vars);

            Database.close();
        }

        return valid;
    }

    public static boolean deleteTeam(String team_name)
    {
        boolean valid = false;
        if (isCoachOfTeam(team_name) || AuthenticationController.authLevel == "Admin")
        {
            Database.connect();

            String sql = "DROP TABLE ?;";

            String[] vars = new String[1];
            vars[0] = team_name;
            valid = Database.executeAsyncUpdate(sql, vars);

            Database.close();
        }

        return valid;
    }

    public static boolean isCoachOfTeam(String team_name)
    {
        Database.connect();

        ResultSet rsAuth = Database.searchQuery("SELECT * FROM " + team_name + ";");
        try {
            while (rsAuth.next()) {
                String row_team_member = rsAuth.getString("team_member");
                if (AuthenticationController.activeUser.equals(row_team_member)) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
