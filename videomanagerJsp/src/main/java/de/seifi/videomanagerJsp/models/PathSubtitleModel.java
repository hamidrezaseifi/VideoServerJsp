package de.seifi.videomanagerJsp.models;

public class PathSubtitleModel {

  int id;
  String suburl;
  String path;

  public PathSubtitleModel() {

  }

  public PathSubtitleModel(final int id, final String path, final String suburl) {

    this.id = id;
    this.path = path;
    this.suburl = suburl;

  }

  public int getId() {

    return this.id;
  }

  public String getPath() {

    return this.path;
  }

  public String getSubtitleUrl() {

    return this.suburl;
  }

  public String getSuburl() {

    return this.suburl;
  }

  public void setSuburl(final String suburl) {

    this.suburl = suburl;
  }

  public void setId(final int id) {

    this.id = id;
  }

  public void setPath(final String path) {

    this.path = path;
  }

}
