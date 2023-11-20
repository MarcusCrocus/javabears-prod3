package producto2_065_BearsJava.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Username is required")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 2, message = "Password should have at least 2 characters")
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User user))
            return false;

        if (getId() != user.getId())
            return false;
        if (!getFirstName().equals(user.getFirstName()))
            return false;
        if (!getLastName().equals(user.getLastName()))
            return false;
        if (!getEmail().equals(user.getEmail()))
            return false;
        if (!getUsername().equals(user.getUsername()))
            return false;
        if (!getPassword().equals(user.getPassword()))
            return false;
        if (!getConfirmPassword().equals(user.getConfirmPassword()))
            return false;
        return getRoles().equals(user.getRoles());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getConfirmPassword().hashCode();
        result = 31 * result + getRoles().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", roles=" + roles +
                '}';
    }

}
