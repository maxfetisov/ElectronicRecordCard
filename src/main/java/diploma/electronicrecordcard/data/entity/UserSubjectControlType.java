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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "user_subject_control_type")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserSubjectControlType implements Versionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "semester")
    Short semester;

    @Column(name = "hours_number")
    Short hoursNumber;

    @Column(name = "note")
    String note;

    @Column(name = "version")
    Long version;

    @ManyToOne()
    User teacher;

    @ManyToOne
    User student;

    @ManyToOne
    Subject subject;

    @ManyToOne
    ControlType controlType;

    @OneToMany
    @JoinColumn(name = "user_subject_control_type_id")
    List<StudentMark> studentMarks;

}
