package de.seifi.videomanagerJsp.models;

public class FolderModel {

  int id;
  String name;
  String path;
  int state;

  public FolderModel() {

  }

  public FolderModel(final int id, final String name, final String path, final int state) {

    this.id = id;
    this.path = path;
    this.name = name;
    this.state = state;
  }

  public void setId(final int id) {

    this.id = id;
  }

  public void setName(final String name) {

    this.name = name;
  }

  public void setPath(final String path) {

    this.path = path;
  }

  public void setState(final int state) {

    this.state = state;
  }

  public int getId() {

    return this.id;
  }

  public String getPath() {

    return this.path;
  }

  public String getName() {

    return this.name;
  }

  public int getState() {

    return this.state;
  }

}
