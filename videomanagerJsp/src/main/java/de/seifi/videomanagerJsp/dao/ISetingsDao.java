package de.seifi.videomanagerJsp.dao;

import java.util.List;

import de.seifi.videomanagerJsp.models.SettingModel;

public interface ISetingsDao {

  List<SettingModel> readAll();
}
