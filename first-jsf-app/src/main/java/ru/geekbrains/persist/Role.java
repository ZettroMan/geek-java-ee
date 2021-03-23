package ru.geekbrains.persist;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dto.RoleDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(@NotNull String name) {
        this.name = name;
    }

    public Role(RoleDto r) {
        this.id = r.getId();
        this.name = r.getName();
    }

}
