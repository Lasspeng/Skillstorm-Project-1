package com.skillstorm.project1.dtos;
import java.util.Objects;

public class AccountDto {

    private int id;
    private String username;
    private String company;


    public AccountDto() {
    }

    public AccountDto(int id, String username, String company) {
        this.id = id;
        this.username = username;
        this.company = company;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public AccountDto id(int id) {
        setId(id);
        return this;
    }

    public AccountDto username(String username) {
        setUsername(username);
        return this;
    }

    public AccountDto company(String company) {
        setCompany(company);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AccountDto)) {
            return false;
        }
        AccountDto accountDto = (AccountDto) o;
        return id == accountDto.id && Objects.equals(username, accountDto.username) && Objects.equals(company, accountDto.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, company);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", company='" + getCompany() + "'" +
            "}";
    }
    
}
