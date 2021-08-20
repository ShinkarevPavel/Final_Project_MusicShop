package com.shinkarev.musicshop.entity;

public class User extends Entity {
    private long id;
    private String login;
    private String email;
    private String nickname;
    private String name;
    private String surename;
    private UserStatusType status;
    private UserRoleType role;

    public User() {
    }

    public User(String login, String email, String nickname, UserStatusType status, UserRoleType role) {
        this.login = login;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.role = role;
    }

    public User(String login, String email, String nickname, String name, String surename, UserStatusType status, UserRoleType role) {
        this.login = login;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.surename = surename;
        this.status = status;
        this.role = role;
    }

    public User(long id, String login, String email, String nickname, String name, String surename, UserStatusType status, UserRoleType role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.surename = surename;
        this.status = status;
        this.role = role;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    public UserRoleType getRole() {
        return role;
    }

    public void setRole(UserRoleType role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        if (this.id != user.id) {
            return false;
        }
        if (!this.name.equals(user.getName())) {
            return false;
        }
        if (!this.login.equals(user.getLogin())) {
            return false;
        }
        if (!this.email.equals(user.getEmail())) {
            return false;
        }
        if (!this.status.equals(user.getStatus())) {
            return false;
        }
        if (!this.nickname.equals(user.getNickname())) {
            return false;
        }
        return this.role.equals(user.getRole());
    }

    @Override
    public int hashCode() {
        int result = (int) (this.id ^ (this.id >>> 32));
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.surename != null ? this.surename.hashCode() : 0);
        result = 31 * result + (this.login != null ? this.login.hashCode() : 0);
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.status != null ? this.status.hashCode() : 0);
        result = 31 * result + (this.role != null ? this.role.hashCode() : 0);
        result = 31 * result + (this.nickname != null ? this.nickname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surename='").append(surename).append('\'');
        sb.append(", status=").append(status);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
