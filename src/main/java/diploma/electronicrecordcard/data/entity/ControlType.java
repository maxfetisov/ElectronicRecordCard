package diploma.electronicrecordcard.data.entity;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "control_type")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ControlType implements Versionable {

    @Id
    @Column(name = "id")
    Short id;

    @Column(name = "name")
    String name;

    @Column(name = "title")
    String title;

    @Column(name = "version")
    Long version;

    @OneToMany
    @JoinColumn(name = "control_type_id")
    List<UserSubjectControlType> userSubjectControlTypes;

    @ManyToMany(mappedBy = "controlTypes")
    List<Mark> marks;

}
