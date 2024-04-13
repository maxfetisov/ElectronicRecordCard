package diploma.electronicrecordcard.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity
@Table(name = "student_mark")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StudentMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "semester")
    Short semester;

    @Column(name = "completion_date")
    @Temporal(TemporalType.DATE)
    LocalDate completionDate;

    @Column(name = "hours_number")
    Short hoursNumber;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    User teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    User student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;

    @ManyToOne
    @JoinColumn(name = "control_type_id")
    ControlType controlType;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    Mark mark;

}
