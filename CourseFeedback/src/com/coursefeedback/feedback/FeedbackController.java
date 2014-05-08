package com.coursefeedback.feedback;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;
import org.primefaces.event.SlideEndEvent;

import com.coursefeedback.courseitemmanager.CourseItem;
import com.coursefeedback.feedbackmanager.BasicFeedbackManager;
import com.coursefeedback.studentmanager.StudentManager;

@ManagedBean
public class FeedbackController {
	@ManagedProperty(value = "#{feedbackManager}")
	private BasicFeedbackManager fbm;

	public BasicFeedbackManager getFbm() {
		return fbm;
	}

	public void setFbm(BasicFeedbackManager fbm) {
		this.fbm = fbm;
	}

	@ManagedProperty(value = "#{feedback}")
	private Feedback fb;

	public Feedback getFb() {
		return fb;
	}

	public void setFb(Feedback fb) {
		this.fb = fb;
	}

	@ManagedProperty(value = "#{courseItem}")
	private CourseItem ci;

	public CourseItem getCi() {
		return ci;
	}

	public void setCi(CourseItem ci) {
		this.ci = ci;
	}

	@ManagedProperty(value = "#{studentManager}")
	private StudentManager sm;

	public StudentManager getSm() {
		return sm;
	}

	public void setSm(StudentManager sm) {
		this.sm = sm;
	}

	public String submitFeedback(int courseItemId) {
		fbm.addFeedbackToCourseItem(this.fb, courseItemId);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
				"Feedback Submitted");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "rateCourseItem.xhtml";
	}

	public void onrate(RateEvent rateEvent) {
		System.out.print("RateEvent");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Selection", "You chose:"
						+ ((Integer) rateEvent.getRating()).intValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onSlideEnd(SlideEndEvent event) {
		System.out.print("SlideEndEvent");
		FacesMessage message = new FacesMessage("Selection", "You chose:"
				+ event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}