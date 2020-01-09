package de.seifi.videomanagerJsp.dao;

import java.util.List;

import de.seifi.videomanagerJsp.models.FolderModel;

public interface IFolderDao {

  List<FolderModel> readAll();
}
