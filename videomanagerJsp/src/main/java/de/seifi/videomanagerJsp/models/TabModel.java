package de.seifi.videomanagerJsp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbltabs")
public class TabModel {

  @Id
  @Column(name = "settingname")
  String settingname;

  @Column(name = "settingvalue")
  String settingvalue;

  public TabModel() {

  }

  public String getSettingname() {

    return this.settingname;
  }

  public void setSettingname(final String settingname) {

    this.settingname = settingname;
  }

  public String getSettingvalue() {

    return this.settingvalue;
  }

  public void setSettingvalue(final String settingvalue) {

    this.settingvalue = settingvalue;
  }

}
