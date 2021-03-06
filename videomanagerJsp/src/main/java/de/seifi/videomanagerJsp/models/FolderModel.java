package de.seifi.videomanagerJsp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblfolders")
public class FolderModel {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "name")
  String name;

  @Column(name = "path")
  String path;

  @Column(name = "state")
  int state;

  public FolderModel() {

  }

  public FolderModel(final Long id, final String name, final String path, final int state) {

    this.id = id;
    this.path = path;
    this.name = name;
    this.state = state;
  }

  public Long getId() {

    return this.id;
  }

  public void setId(final Long id) {

    this.id = id;
  }

  public String getName() {

    return this.name;
  }

  public void setName(final String name) {

    this.name = name;
  }

  public String getPath() {

    return this.path;
  }

  public void setPath(final String path) {

    this.path = path;
  }

  public int getState() {

    return this.state;
  }

  public void setState(final int state) {

    this.state = state;
  }

}
