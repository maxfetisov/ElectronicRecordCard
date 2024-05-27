package diploma.electronicrecordcard.data.entity;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "institute")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Institute implements Versionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Short id;

    @Column(name = "name")
    String name;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "deleted")
    Boolean deleted;

    @Column(name = "version")
    Long version;

    @OneToMany(mappedBy = "institute")
    List<Group> groups;

    @OneToMany(mappedBy = "institute")
    List<User> users;

}
