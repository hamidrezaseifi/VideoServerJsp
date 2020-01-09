package de.seifi.videomanagerJsp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblpathsubtitle")
public class PathSubtitleModel {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "suburl")
  String suburl;

  @Column(name = "path")
  String path;

  public PathSubtitleModel() {

  }

  public PathSubtitleModel(final Long id, final String path, final String suburl) {

    this.id = id;
    this.path = path;
    this.suburl = suburl;

  }

  public Long getId() {

    return this.id;
  }

  public void setId(final Long id) {

    this.id = id;
  }

  public String getSuburl() {

    return this.suburl;
  }

  public void setSuburl(final String suburl) {

    this.suburl = suburl;
  }

  public String getPath() {

    return this.path;
  }

  public void setPath(final String path) {

    this.path = path;
  }

}
