package model;

/**
 * Immutable data model for a system user (Java Record).
 * roles: ADMIN, GROOMER, or RECEPTIONIST
 * status: PENDING (default), ACTIVE, or INACTIVE
 */
public record User(int id, String username, String password, String roles, String status) {
}
