package de.seifi.videomanagerJsp.dao;

import java.util.List;

import de.seifi.videomanagerJsp.models.TabModel;

public interface ITabDao {

  List<TabModel> readAll();
}
