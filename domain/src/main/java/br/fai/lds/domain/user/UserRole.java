package br.fai.lds.domain.user;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    HEALTH_PROFESSIONAL("HEALTH_PROFESSIONAL");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
