package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "semester")
public class Semester {
    @Id
    private String semester_id; // Renamed from 'code'

    @Column(name = "semester_name")
    private String name;

    @Column(name = "start_Date")
    private LocalDate startDate;

    @Column(name = "end_Date")
    private LocalDate endDate;

	public Semester(String semester_id, String name, LocalDate startDate, LocalDate endDate) {
		super();
		this.semester_id = semester_id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Semester() {
		// TODO Auto-generated constructor stub
	}

	public String getSemester_id() {
		return semester_id;
	}

	public void setSemester_id(String semester_id) {
		this.semester_id = semester_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

  

    
}
