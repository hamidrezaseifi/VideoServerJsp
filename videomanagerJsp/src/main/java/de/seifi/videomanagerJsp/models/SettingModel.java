package de.seifi.videomanagerJsp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblsettings")
public class SettingModel {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  int index;
  String name;
  String url;
  int state;

  public SettingModel() {

  }

  public SettingModel(final Long id, final int index, final String name, final String url, final int state) {

    this.id = id;
    this.index = index;
    this.name = name;
    this.url = url;
    this.state = state;
  }

  public Long getId() {

    return this.id;
  }

  public void setId(final Long id) {

    this.id = id;
  }

  public int getIndex() {

    return this.index;
  }

  public void setIndex(final int index) {

    this.index = index;
  }

  public String getName() {

    return this.name;
  }

  public void setName(final String name) {

    this.name = name;
  }

  public String getUrl() {

    return this.url;
  }

  public void setUrl(final String url) {

    this.url = url;
  }

  public int getState() {

    return this.state;
  }

  public void setState(final int state) {

    this.state = state;
  }

}
