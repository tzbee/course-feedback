package courseitempagebean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "courseItemPageBean")
public class CourseItemPageBean {
	private String currentCourseItemBeanId;

	public String getCurrentCourseItemBeanId() {
		return currentCourseItemBeanId;
	}

	public void setCurrentCourseItemBeanId(String currentCourseItemBeanId) {
		this.currentCourseItemBeanId = currentCourseItemBeanId;
	}
}
