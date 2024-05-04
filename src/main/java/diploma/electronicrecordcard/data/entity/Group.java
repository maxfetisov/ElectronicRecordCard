package diploma.electronicrecordcard.data.entity;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student_group")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Versionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "admission_date")
    @Temporal(TemporalType.DATE)
    LocalDate admissionDate;

    @Column(name = "deleted")
    Boolean deleted;

    @Column(name = "version")
    Long version;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    Institute institute;

    @OneToMany(mappedBy = "group")
    List<User> users;

}
