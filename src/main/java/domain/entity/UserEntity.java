package domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    @NotBlank(message = "Логин пользователя не должно быть пустым")
    @Size(min = 6, max = 20, message = "Имя пользователя должно быть от 6 до 20 знаков")
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, max = 50, message = "Пароль должен быть от 6 до 20 знаков")
    private String password;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "ФИО пользователя не должно быть пустым")
    private String fullUsername;

    @Column(name = "birthDate", nullable = false)
    @NotBlank(message = "Дата рождения пользователя не должно быть пустым")
    private LocalDate birthday;

    @Valid
    @OneToMany(mappedBy = "users")
    private Set<EmailEntity> emails;

    @Valid
    @OneToMany(mappedBy = "users")
    private Set<PhoneEntity> phones;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "balance", nullable = false)
    @Size(min = 1, message = "Начальные накапления не должны быть пустыми")
    private BigDecimal money;

    @Column(name = "creationDateTime", nullable = false)
    private LocalDateTime creationDateTime;

    @Column(name = "lastUpdateTime", nullable = false)
    private LocalDateTime lastUpdateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
