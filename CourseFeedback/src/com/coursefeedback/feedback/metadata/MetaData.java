package com.coursefeedback.feedback.metadata;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coursefeedback.feedback.Feedback;

@ManagedBean
@Entity
@Table(name = "metaData")
public class MetaData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dataId")
	private int dataId;

	@Column(name = "metaData")
	private String metaData;

	@Column(name = "value")
	private int value;

	@OneToOne
	private Feedback feedback;

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	@Column(name = "metaData")
	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	@Column(name = "value")
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
