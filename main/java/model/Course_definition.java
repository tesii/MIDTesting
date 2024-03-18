package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity

@Table(name = "course_definition")
public class Course_definition {
	 @Id
	
    @Column(name = "course_definition_id")
	    private String course_definition_id;

	    @Column(name = "course_definition_code", length = 50)
	    private String courseDefinitionCode;

	    @Column(name = "course_definition_description", length = 255)
	    private String courseDefinitionDescription;
	    
	   
	    
	 
		public Course_definition(String course_definition_id, String courseDefinitionCode,
				String courseDefinitionDescription) {
			super();
			this.course_definition_id = course_definition_id;
			this.courseDefinitionCode = courseDefinitionCode;
			this.courseDefinitionDescription = courseDefinitionDescription;
			
		}

		public String getCourse_definition_id() {
			return course_definition_id;
		}

		public void setCourse_definition_id(String course_definition_id) {
			this.course_definition_id = course_definition_id;
		}

		public String getCourseDefinitionCode() {
			return courseDefinitionCode;
		}

		public void setCourseDefinitionCode(String courseDefinitionCode) {
			this.courseDefinitionCode = courseDefinitionCode;
		}

		public String getCourseDefinitionDescription() {
			return courseDefinitionDescription;
		}

		public void setCourseDefinitionDescription(String courseDefinitionDescription) {
			this.courseDefinitionDescription = courseDefinitionDescription;
		}

	


}
