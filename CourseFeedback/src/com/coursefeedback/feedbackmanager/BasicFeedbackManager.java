package com.coursefeedback.feedbackmanager;

import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.coursefeedback.feedback.Feedback;

@ManagedBean
public class BasicFeedbackManager implements FeedbackManager {
	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String saveFeedback(Feedback feedback) {
		try {
			this.utx.begin();
			this.em.persist(feedback);
			this.utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "feedbackBar";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Feedback> getAllFeedbacks() {
		Query query = this.em.createQuery("SELECT f FROM Feedback f");
		return (Collection<Feedback>) query.getResultList();
	}
}