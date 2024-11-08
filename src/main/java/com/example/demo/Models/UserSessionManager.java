package com.example.demo.Models;

public class UserSessionManager {
    private static UserSessionManager instance;
    private UserSession currentSession;

    // Private constructor to prevent instantiation
    private UserSessionManager() {}

    // Get the singleton instance of UserSessionManager
    public static UserSessionManager getInstance() {
        if (instance == null) {
            synchronized (UserSessionManager.class) {
                if (instance == null) {
                    instance = new UserSessionManager();
                }
            }
        }
        return instance;
    }

    // Set the current session when the user logs in
    public void setCurrentSession(UserSession session) {
        this.currentSession = session;
    }

    // Get the current session data
    public UserSession getCurrentSession() {
        return currentSession;
    }

    // Clear the session on logout
    public void clearSession() {
        currentSession = null;
    }
}
